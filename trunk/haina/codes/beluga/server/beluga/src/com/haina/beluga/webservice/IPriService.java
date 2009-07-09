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
	 * @param email 用户的登录名
	 * @param pwd 密码
	 * @param srcAppCode 来源认证应用代码
	 * @param destAppCode 目标认证应用代码
	 * @param userLoginIp 用户登录的IP地址
	 */
	public HessianRemoteReturning login(String email, String pwd,String srcAppCode, String destAppCode, String userLoginIp);
	
	/**
	 * 联系人用户注册。<br/>
	 * 注册成功后的用户自动登录。
	 * @param email 用户的登录名
	 * @param password 密码
	 * @param mobile 移动电话号码
	 * @param description 描述
	 * @param registerIp 注册用户的IP地址
	 * @param lang 语言环境
	 */
	public HessianRemoteReturning register(String email, String password, String mobile, String description,
			String registerIp,String lang);
	
	/**
	 * 联系人用户退出。<br/>
	 * @param email 用户的登录名
	 */
	public HessianRemoteReturning logout(String email);
	
	/**
	 * 修改密码。<br/>
	 * 
	 * @param email 用户的登录名
	 * @param oldPwd 旧密码
	 * @param neoPwd 新密码
	 */
	public HessianRemoteReturning editPwd(String email, String oldPwd, String neoPwd);

	/**
	 * 修改手机号码。<br/>
	 * 
	 * @param email 用户的登录名
	 * @param oldMobile 旧手机号码
	 * @param neoMobile 新手机号码
	 */
	public HessianRemoteReturning editMobile(String email, String oldMobile, String neoMobile);
	
	/**
	 * 修改登录名（同时修改电子邮件）。<br/>
	 * @param email 用户的登录名
	 * @param neoEmail 新的登录名
	 * @return 操作结果
	 */
	public HessianRemoteReturning editLoginName(String email, String neoEmail);

}
