package com.haina.beluga.webservice;

import java.io.Serializable;

import com.haina.beluga.webservice.data.hessian.HessianRemoteReturning;

/**
 * 个人服务Api接口。<br/>
 * @author huangyongqiang
 * @version 1.0
 * @since 1.0
 * @date 2009-06-29
 */

public interface IPriService extends Serializable {
	
	/**
	 * 联系人用户登录。<br/>
	 * @param loginName 用户的登录名
	 * @param password 密码
	 */
	public HessianRemoteReturning login(String loginName, String password);
	
	/**
	 * 联系人用户注册。<br/>
	 * 注册成功后的用户自动登录。
	 * @param loginName 用户的登录名
	 * @param password 密码
	 * @param mobile 移动电话号码
	 */
	public HessianRemoteReturning register(String loginName, String password, String mobile);
	
	/**
	 * 联系人用户通过护照退出。<br/>
	 * @param passport 登录护照
	 */
	public HessianRemoteReturning logoutByPsssport(String passport);
	
	/**
	 * 联系人用户通过登录名退出。<br/>
	 * @param loginName 登录名
	 */
	public HessianRemoteReturning logoutByLoginName(String loginName);
	
	/**
	 * 修改密码。<br/>
	 * 
	 * @param passport 登录护照
	 * @param oldPassword 旧密码
	 * @param neoPassword 新密码
	 */
	public HessianRemoteReturning editPassword(String passport, String oldPassword, String neoPassword);

	/**
	 * 修改手机号码。<br/>
	 * 
	 * @param passport 登录护照
	 * @param neoMobile 新手机号码
	 */
	public HessianRemoteReturning editMobile(String passport, String neoMobile);
	
	/**
	 * 修改登录名（同时修改电子邮件）。<br/>
	 * @param passport 登录护照
	 * @param neoEmail 新的登录名
	 * @return 操作结果
	 */
	public HessianRemoteReturning editLoginName(String passport, String neoEmail);
}
