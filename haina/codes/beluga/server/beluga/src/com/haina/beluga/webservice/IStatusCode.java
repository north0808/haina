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
	Integer SUCCESS=1000;
	
	/**
	 * 未知错误。<br/>
	 */
	Integer UNKNOW_ERROR=SUCCESS+1;
	
	/**
	 * 网络错误。<br/>
	 */
	Integer NETWORK_ERROR=UNKNOW_ERROR+1;
	
	/**
	 * 登录名或密码无效。<br/>
	 */
	Integer LOGINNAME_PASSWORD_INVALID=NETWORK_ERROR+1;
	
	/**
	 * 手机号码无效。<br/>
	 */
	Integer MOBILE_INVALID=LOGINNAME_PASSWORD_INVALID+1;
	
	/**
	 * 用户已存在。<br/>
	 */
	Integer CONTACT_USER_EXISTENT=MOBILE_INVALID+1;
	
	/**
	 * 注册成功，但暂时不能生成登录护照。<br/>
	 */
	Integer REGISTER_SUCCESS_PASSPORT_FAILD=CONTACT_USER_EXISTENT+1;
	
	/**
	 * 登录失败，暂时不能生成登录护照。<br/>
	 */
	Integer LOGIN_PASSPORT_FAILD=REGISTER_SUCCESS_PASSPORT_FAILD+1;
	
	/**
	 * 用户不存在或用户未激活。<br/>
	 */
	Integer INVALID_CONTACT_USER=LOGIN_PASSPORT_FAILD+1;
	
	/**
	 * 登录名无效。<br/>
	 */
	Integer LOGINNAME_INVALID=INVALID_CONTACT_USER+1;
}
