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
	private Map<String,LoginPassport> passportPool=new ConcurrentHashMap<String,LoginPassport>();
	
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
	public LoginPassport addPassport(ContactUser contactUser) {
		LoginPassport loginPassport=null;
		if(null!=contactUser && contactUser.isOnline()) {
			loginPassport=new LoginPassport();
			loginPassport.setEmail(contactUser.getLoginName());
			loginPassport.setLoginExpiry(loginExpiry);
			Long time=contactUser.getLastLoginTime().getTime();
			loginPassport.setLoginTime(time);
			loginPassport.setPassport(generatePassport());
			loginPassport.setPassportTime(time);
			loginPassport.setPassportExpiry(passportExpiry);
			passportPool.put(contactUser.getLoginName(), loginPassport);
		}
		return loginPassport;
	}

	@Override
	public LoginPassport updatePassport(String loginName) {
		LoginPassport loginPassport=null;
		if(null!=loginName && passportPool.containsKey(loginName)) {
			loginPassport=passportPool.get(loginName);
			Long now=(new Date()).getTime();
			if(isExpiredPassport(loginPassport)) {
				loginPassport.setPassport(generatePassport());
				loginPassport.setPassportTime(now);
			}
		}
		return loginPassport;
	}
	
	@Override
	public LoginPassport getPassport(String loginName) {
		LoginPassport loginPassport=null;
		if(loginName!=null && passportPool.containsKey(loginName)) {
			loginPassport=passportPool.get(loginName);
		}
		return loginPassport;
	}

	@Override
	public boolean isExpiredPassport(String loginName) {
		if(isExpiredLogin(loginName)) {
			return true;
		}
		LoginPassport loginPassport=passportPool.get(loginName);
		return isExpiredPassport(loginPassport);
	}
	
	@Override
	public boolean isExpiredPassport(LoginPassport loginPassport) {
		if(isExpiredLogin(loginPassport)) {
			return true;
		}
		Long now=(new Date()).getTime();
		return loginPassport.getPassportTime()+loginPassport.getPassportExpiry()<now-passportExpiryTimeOff;
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
		LoginPassport loginPassport=passportPool.get(loginName);
		return isExpiredLogin(loginPassport);
	}
	
	public boolean isExpiredLogin(LoginPassport loginPassport) {
		if(loginPassport==null) {
			return true;
		} else {
			Long now=(new Date()).getTime();
			if(!passportPool.containsValue(loginPassport)) {
				return true;
			}
			return loginPassport.getLoginTime()+loginPassport.getLoginExpiry()<now-loginExpiryTimeOff;
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
							LoginPassport loginPassport=passportPool.get(key);
							if(isExpiredLogin(loginPassport)) {
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
