package com.haina.beluga.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.haina.beluga.core.util.StringUtils;
import com.haina.beluga.domain.ContactUser;
import com.haina.beluga.service.LoginPassport;
import com.haina.beluga.service.IContactUserService;
import com.haina.beluga.service.IPassportService;
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
	
	//@Autowired(required=true)
	//private AuthenticationService authenticationService;
	
	@Autowired(required=true)
	private IContactUserService contactUserService;
	
	//@Autowired(required=true)
	//private ILocaleMessageService localeMessageService;
	
	@Autowired(required=true)
	private IPassportService passportService;

	@Override
	public HessianRemoteReturning editLoginName(String email, String neoEmail) {
		HessianRemoteReturning ret = new HessianRemoteReturning();
		
		return ret;
	}

	@Override
	public HessianRemoteReturning editMobile(String loginName, String oldMobile, String neoMobile) {
		HessianRemoteReturning ret = new HessianRemoteReturning();
		
		return ret;
	}

	@Override
	public HessianRemoteReturning editPwd(String loginName, String oldPwd, String neoPwd) {
		HessianRemoteReturning ret = new HessianRemoteReturning();
		
		return ret;
	}

	@Override
	public HessianRemoteReturning login(String loginName, String password) {
		HessianRemoteReturning ret = new HessianRemoteReturning();
		//$1 验证合法性
		if(StringUtils.isNull(loginName) || StringUtils.isNull(password)) {
			ret.setStatusCode(IStatusCode.LOGINNAME_PASSWORD_INVALID);
			return ret;
		}
		//$2 设置用户在线状态
		ContactUser contactUser=contactUserService.editContactUserToOnline(loginName, password, null);
		if(null==contactUser || !contactUser.getValidFlag()) {
			ret.setStatusCode(IStatusCode.INVALID_CONTACT_USER);
			return ret;
		}
		//$3 生成护照
		LoginPassport loginPassport=passportService.addPassport(contactUser);
		if(null==loginPassport) {
			ret.setStatusCode(IStatusCode.LOGIN_PASSPORT_FAILD);
			contactUserService.editContactUserToOffline(contactUser);
		} else {
			ret.setStatusCode(IStatusCode.SUCCESS);
			ret.setValue(loginPassport);
		}
		return ret;
	}

	@Override
	public HessianRemoteReturning logout(String loginName) {
		HessianRemoteReturning ret = new HessianRemoteReturning();
		//$1 验证合法性
		if(StringUtils.isNull(loginName)) {
			ret.setStatusCode(IStatusCode.LOGINNAME_INVALID);
			return ret;
		}
		//$2 设置用户离线状态
		ContactUser contactUser=contactUserService.editContactUserToOffline(loginName);
		if(null==contactUser || !contactUser.getValidFlag()) {
			ret.setStatusCode(IStatusCode.INVALID_CONTACT_USER);
			return ret;
		}
		//$3 登录失效，清除护照
		passportService.expireLogin(loginName);
		ret.setStatusCode(IStatusCode.SUCCESS);
		return ret;
	}

	@Override
	public HessianRemoteReturning register(String loginName, String password, String mobile) {
		HessianRemoteReturning ret = new HessianRemoteReturning();
		//$1 验证合法性
		if(StringUtils.isNull(loginName) || StringUtils.isNull(password)) {
			ret.setStatusCode(IStatusCode.LOGINNAME_PASSWORD_INVALID);
//			ret.setStatusText(localeMessageService
//					.getI18NMessage("com.haina.shield.message.passportuser.register.failure.email.and.passpord"));
			return ret;
		}
		if(StringUtils.isNull(mobile)) {
			ret.setStatusCode(IStatusCode.MOBILE_INVALID);
//			ret.setStatusText(localeMessageService
//					.getI18NMessage("com.haina.shield.message.passportuser.register.failure.mobile"));
			return ret;
		}
		ContactUser contactUser=contactUserService.addContactUser(
				loginName, password, mobile, ContactUser.USER_STATUS_ONLINE,null);
		if(null!=contactUser && !contactUser.getMobile().equals(mobile.trim())) {
			ret.setStatusCode(IStatusCode.CONTACT_USER_EXISTENT);
//			ret.setStatusText(localeMessageService
//					.getI18NMessage("com.haina.shield.message.passportuser.register.failure.existent.email.or.mobile"));
			return ret;
		}
		
		//$2 向认证中心注册
		//TODO暂时不实现。
		
		//$3 生成护照
		LoginPassport loginPassport=passportService.addPassport(contactUser);
		if(null==loginPassport) {
			ret.setStatusCode(IStatusCode.REGISTER_SUCCESS_PASSPORT_FAILD);
			contactUserService.editContactUserToOffline(contactUser);
		} else {
			ret.setStatusCode(IStatusCode.SUCCESS);
			ret.setValue(loginPassport);
		}
		return ret;
	}

}
