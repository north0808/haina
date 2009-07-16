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
	int NETWORK_ERROR=1001;
	
	/**
	 * 登录名或密码无效。<br/>
	 */
	int LOGINNAME_PASSWORD_INVALID=1002;
	
	/**
	 * 手机号码无效。<br/>
	 */
	int MOBILE_INVALID=1003;
	
	/**
	 * 用户已存在。<br/>
	 */
	int CONTACT_USER_EXISTENT=1004;
	
	/**
	 * 注册成功，但暂时不能生成登录护照。<br/>
	 */
	int REGISTER_SUCCESS_PASSPORT_FAILD=1005;
	
	/**
	 * 登录失败，暂时不能生成登录护照。<br/>
	 */
	int LOGIN_PASSPORT_FAILD=1006;
	
	/**
	 * 用户不存在或用户未激活。<br/>
	 */
	int INVALID_CONTACT_USER=1007;
	
	/**
	 * 登录名或密码无效。<br/>
	 */
	int LOGINNAME_OR_PASSWORD_INVALID=1008;
	
	/**
	 * 登录护照无效或已过期。<br/>
	 */
	int INVALID_LOGIN_PASSPORT=1009;
	
	/**
	 * 无效的电子邮件地址。<br/>
	 */
	int INVALID_EMAIL=1010;
	
	/**
	 * 无效的电子邮件地址。<br/>
	 */
	int INVALID_MOBILE=1011;
	
	/**
	 * 无效的密码。<br/>
	 */
	int INVALID_PASSWORD=1012;
	
	/**
	 * 错误的旧密码。<br/>
	 */
	int WRONG_OLD_PASSWORD=1013;
	
	/**
	 * 登录名或手机号码已存在。<br/>
	 */
	int LOGINNAME_OR_MOBILE_EXISTENT=1014;
}
