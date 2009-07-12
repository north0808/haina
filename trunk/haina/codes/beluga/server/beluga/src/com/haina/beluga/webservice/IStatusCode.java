package com.haina.beluga.webservice;

/**
 * 状态码常量接口。<br/>
 * @author huangyongqiang
 * @version 1.0
 * @since 1.0
 * @date 2009-06-21
 */
public interface IStatusCode {

	/**
	 * 成功。<br/>
	 */
	int SUCCESS=0;
	
	/**
	 * 未知错误。<br/>
	 */
	int UNKNOW_ERROR=1000;
	
	/**
	 * 网络错误。<br/>
	 */
	int NETWORK_ERROR=UNKNOW_ERROR+1;
	
	/**
	 * 登录名或密码无效。<br/>
	 */
	int LOGINNAME_PASSWORD_INVALID=NETWORK_ERROR+1;
	
	/**
	 * 手机号码无效。<br/>
	 */
	int MOBILE_INVALID=LOGINNAME_PASSWORD_INVALID+1;
	
	/**
	 * 用户已存在。<br/>
	 */
	int CONTACT_USER_EXISTENT=MOBILE_INVALID+1;
	
	/**
	 * 注册成功，但暂时不能生成登录护照。<br/>
	 */
	int REGISTER_SUCCESS_PASSPORT_FAILD=CONTACT_USER_EXISTENT+1;
	
	/**
	 * 登录失败，暂时不能生成登录护照。<br/>
	 */
	int LOGIN_PASSPORT_FAILD=REGISTER_SUCCESS_PASSPORT_FAILD+1;
	
	/**
	 * 用户不存在或用户未激活。<br/>
	 */
	int INVALID_CONTACT_USER=LOGIN_PASSPORT_FAILD+1;
	
	/**
	 * 登录名无效。<br/>
	 */
	int LOGINNAME_INVALID=INVALID_CONTACT_USER+1;
	
	/**
	 * 登录护照无效或已过期。<br/>
	 */
	int INVALID_LOGIN_PASSPORT=LOGINNAME_INVALID+1;
	
	/**
	 * 无效的电子邮件地址。<br/>
	 */
	int INVALID_EMAIL=INVALID_LOGIN_PASSPORT+1;
	
	/**
	 * 无效的电子邮件地址。<br/>
	 */
	int INVALID_MOBILE=INVALID_EMAIL+1;
	
	/**
	 * 无效的密码。<br/>
	 */
	int INVALID_PASSWORD=INVALID_MOBILE+1;
}
