package com.haina.beluga.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.haina.beluga.core.util.StringUtils;
import com.haina.beluga.domain.ContactUser;
import com.haina.beluga.service.IContactUserService;
import com.haina.beluga.service.ILocaleMessageService;
import com.haina.beluga.webservice.data.hessian.HessianRemoteReturning;

/**
 * 个人服务Api接口实现类。<br/>
 * @author huangyongqiang
 * @version 1.0
 * @since 1.0
 * @date 2009-06-29
 */

@Component(value="priService")
public class PriService implements IPriService {
	
	private static final long serialVersionUID = 4943053100272442973L;
	
	@Autowired(required=true)
	private AuthenticationService authenticationService;
	
	@Autowired(required=true)
	private IContactUserService contactUserService;
	
	@Autowired(required=true)
	private ILocaleMessageService localeMessageService;

	@Override
	public HessianRemoteReturning editLoginName(String email, String neoEmail) {
		HessianRemoteReturning ret = new HessianRemoteReturning();
		
		return ret;
	}

	@Override
	public HessianRemoteReturning editMobile(String email, String oldMobile, String neoMobile) {
		HessianRemoteReturning ret = new HessianRemoteReturning();
		
		return ret;
	}

	@Override
	public HessianRemoteReturning editPwd(String email, String oldPwd, String neoPwd) {
		HessianRemoteReturning ret = new HessianRemoteReturning();
		
		return ret;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false,
			isolation=Isolation.READ_COMMITTED,rollbackFor = Exception.class)
	public HessianRemoteReturning login(String email, String pwd, String srcAppCode,
			String destAppCode, String userLoginIp) {
		HessianRemoteReturning ret = new HessianRemoteReturning();
		
		return ret;
	}

	@Override
	public HessianRemoteReturning logout(String email) {
		HessianRemoteReturning ret = new HessianRemoteReturning();
		
		return ret;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false,
			isolation=Isolation.READ_COMMITTED,rollbackFor = Exception.class)
	public HessianRemoteReturning register(String email, String password, String mobile,
			String description, String registerIp,String lang) {
		HessianRemoteReturning ret = new HessianRemoteReturning();
		//$1 验证合法性
		if(StringUtils.isNull(email) || StringUtils.isNull(password)) {
			ret.setStatusCode(IStatusCode.FAILURE);
			ret.setStatusText(localeMessageService
					.getI18NMessage("com.haina.shield.message.passportuser.register.failure.email.and.passpord"));
			return ret;
		}
		if(StringUtils.isNull(mobile)) {
			ret.setStatusCode(IStatusCode.FAILURE);
			ret.setStatusText(localeMessageService
					.getI18NMessage("com.haina.shield.message.passportuser.register.failure.mobile"));
			return ret;
		}
		ContactUser contactUser=contactUserService.addContactUser(email, password, mobile);
		if(null!=contactUser && !contactUser.getMobile().equals(mobile.trim())) {
			ret.setStatusCode(IStatusCode.FAILURE);
			ret.setStatusText(localeMessageService
					.getI18NMessage("com.haina.shield.message.passportuser.register.failure.existent.email.or.mobile"));
			return ret;
		}
		
		//$2 向认证中心注册
		ret.setStatusCode(IStatusCode.SUCCESS);
		return ret;
	}

}
