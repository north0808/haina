package com.haina.beluga.service;

import java.lang.Thread.State;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.haina.beluga.core.util.DESUtil;
import com.haina.beluga.core.util.StringUtils;
import com.haina.beluga.domain.ContactUser;
import com.haina.beluga.dto.PassportDto;

/**
 * 用户验证护照业务处理接口实现类。<br/>
 * @author huangyongqiang
 * @version 1.0
 * @since 1.0
 * @data 2009-07-11
 *
 */
public class PassportService implements IPassportService {
	
	private static final Log LOG=LogFactory.getLog(PassportService.class);

	/*用户护照的存储池。*/
	private Map<String,PassportDto> passportPool=new ConcurrentHashMap<String,PassportDto>();
	
	/*登录超期时间。默认604800000毫秒，即一周。*/
	private Long loginExpiry;
	
	/*登录超期时间偏移，考虑到网络传输时延。默认10000毫秒，即十秒。*/
	private Long loginExpiryTimeOff;
	
	/*护照超期时间。默认1800000毫秒，即半小时。*/
	private Long passportExpiry;
	
	/*护照超期时间偏移，考虑到网络传输时延。默认30000毫秒，即半分钟。*/
	private Long passportExpiryTimeOff;
	
	/*监控执行周期。默认604800000毫秒，即一周。*/
	private Long monitoringCycle=604800000l;
	
	/*监控执行标志。*/
	private boolean monitoringFlag=true;
	
	private PassportMonitor passportMonitor=new PassportMonitor();
	
	private Thread passportMonitorThread=new Thread(passportMonitor);

	public PassportService() {
		startMonitoring();
	}
	
	
	@Override
	public PassportDto addPassport(ContactUser contactUser) {
		PassportDto passportDto=null;
		if(null!=contactUser && contactUser.isOnline()) {
			passportDto=new PassportDto();
			passportDto.setEmail(contactUser.getLoginName());
			passportDto.setLoginExpiry(loginExpiry);
			Long time=contactUser.getLastLoginTime().getTime();
			passportDto.setLoginTime(time);
			passportDto.setPassport(generatePassport());
			passportDto.setPassportTime(time);
			passportDto.setPassportExpiry(passportExpiry);
			passportPool.put(contactUser.getLoginName(), passportDto);
		}
		return passportDto;
	}

	@Override
	public PassportDto updatePassport(String loginName) {
		PassportDto passportDto=null;
		if(null!=loginName && passportPool.containsKey(loginName)) {
			passportDto=passportPool.get(loginName);
			Long now=(new Date()).getTime();
			if(isExpiredPassport(passportDto)) {
				passportDto.setPassport(generatePassport());
				passportDto.setPassportTime(now);
			}
		}
		return passportDto;
	}
	
	@Override
	public PassportDto getPassport(String loginName) {
		PassportDto passportDto=null;
		if(loginName!=null && passportPool.containsKey(loginName)) {
			passportDto=passportPool.get(loginName);
		}
		return passportDto;
	}

	@Override
	public boolean isExpiredPassport(String loginName) {
		if(isExpiredLogin(loginName)) {
			return true;
		}
		PassportDto passportDto=passportPool.get(loginName);
		return isExpiredPassport(passportDto);
	}
	
	@Override
	public boolean isExpiredPassport(PassportDto passportDto) {
		if(isExpiredLogin(passportDto)) {
			return true;
		}
		Long now=(new Date()).getTime();
		return passportDto.getPassportTime()+passportDto.getPassportExpiry()<now-passportExpiryTimeOff;
	}

	@Override
	public boolean removeAllPassport() {
		return false;
	}

	@Override
	public boolean removePassport(String loginName) {
		return false;
	}

	@Override
	public boolean isExpiredLogin(String loginName) {
		if(loginName==null || !passportPool.containsKey(loginName)) {
			return true;
		}
		PassportDto passportDto=passportPool.get(loginName);
		return isExpiredLogin(passportDto);
	}
	
	public boolean isExpiredLogin(PassportDto passportDto) {
		if(passportDto==null) {
			return true;
		} else {
			Long now=(new Date()).getTime();
			if(!passportPool.containsValue(passportDto)) {
				return true;
			}
			return passportDto.getLoginTime()+passportDto.getLoginExpiry()<now-loginExpiryTimeOff;
		}
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		startMonitoring();
	}

	public void setLoginExpiry(Long loginExpiry) {
		this.loginExpiry = loginExpiry;
	}

	public void setPassportExpiry(Long passportExpiry) {
		this.passportExpiry = passportExpiry;
	}

	public void setPassportExpiryTimeOff(Long passportExpiryTimeOff) {
		this.passportExpiryTimeOff = passportExpiryTimeOff;
	}

	public void setLoginExpiryTimeOff(Long loginExpiryTimeOff) {
		this.loginExpiryTimeOff = loginExpiryTimeOff;
	}
	
	public void setMonitoringCycle(Long monitoringCycle) {
		this.monitoringCycle = monitoringCycle;
	}
	
	private String generatePassport() {
		return DESUtil.encrypt(UUID.randomUUID().toString().replace("-", ""));
	}
	
	private void startMonitoring() {
		if(passportMonitorThread.getState().equals(State.NEW)) {
			passportMonitorThread.start();
			LOG.info(">>>>>>>>>>>>>>>>>>>>>PassportMonitor started>>>>>>>>>>>>>>>>>>>");
		}
	}
	
	/**
	 * 用户验证护照监视器。<br/>
	 * @author huangyongqiang
	 * @version 1.0
	 * @since 1.0
	 * @data 2009-07-11
	 *
	 */
	private class PassportMonitor implements Runnable {

		@SuppressWarnings("static-access")
		@Override
		public void run() {
			try {
				while(monitoringFlag) {
					LOG.info(">>>>>>>>>>>>>>>>>>PassportMonitor start clear expired login user>>>>>>>>>>>>>>>>>>>>>");
					if(passportPool.size()>0) {
						Iterator<String> keys=passportPool.keySet().iterator();
						while(keys.hasNext()) {
							String key=keys.next();
							PassportDto passportDto=passportPool.get(key);
							if(isExpiredLogin(passportDto)) {
								expireLogin(key);
							}
						}
					}
					Thread.currentThread().sleep(monitoringCycle);
				}
			} catch (Exception e) {
				LOG.error(e);
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public boolean expireLogin(String loginName) {
		if(StringUtils.isNull(loginName) || !passportPool.containsKey(loginName)) {
			return false;
		}
		passportPool.remove(loginName);
		return true;
	}
}
