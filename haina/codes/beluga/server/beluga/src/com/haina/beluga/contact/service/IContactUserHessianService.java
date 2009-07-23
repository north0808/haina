package com.haina.beluga.contact.service;

import java.util.Date;
import java.util.List;

import com.haina.beluga.contact.dao.IContactUserDao;
import com.haina.beluga.contact.domain.ContactUser;
import com.haina.beluga.webservice.data.hessian.HessianRemoteReturning;
import com.haina.core.service.IBaseSerivce;

/**
 * 联系人用户hessian协议业务处理接口。<br/>
 * 
 * @author huangyongqiang
 * @version 1.0
 * @since 1.0
 * @date 2009-07-03
 */
public interface IContactUserHessianService extends IBaseSerivce<IContactUserDao,ContactUser,String> {

	/**
	 * 添加联系人用户。<br/>
	 * @param loginName 登录名称。
	 * @param password 密码
	 * @param mobile 手机号码
	 * @param userStatus 用户状态
	 * @param userIp 用户使用的IP地址
	 * @param time 添加的时间
	 * @return
	 */
	public HessianRemoteReturning addContactUser(String loginName, String password, String mobile,Integer userStatus
			,String userIp, Date time);
	
	/**
	 * 设置联系人用户有效。<br/>
	 * @param contactUser 用户对象
	 * @return
	 */
	public HessianRemoteReturning editContactUserToValid(ContactUser contactUser);
	
	/**
	 * 设置联系人用户有效。<br/>
	 * @param loginName 登录名
	 * @param password 密码
	 * @param mobile 手机号码
	 * @param userStatus 用户状态
	 * @param userIp 用户IP地址
	 * @return
	 */
	public HessianRemoteReturning editContactUserToValid(String loginName, String password,
			String mobile,Integer userStatus,String userIp);
	
	/**
	 * 通过Id取得联系人用户信息。<br/>
	 * @param userId
	 * @return
	 */
	public HessianRemoteReturning getContactUserById(String userId);
	
	/**
	 * 通过登录名称取得联系人用户信息。<br/>
	 * @param loginName
	 * @return
	 */
	public HessianRemoteReturning getContactUserByLoginName(String loginName);
	
	/**
	 * 取得一定范围内的联系人用户。<br/>
	 * @param first 起始位置
	 * @param maxSize 最大数量
	 * @return
	 */
	public HessianRemoteReturning getContactUser(int first, int maxSize);
	
	/**
	 * 取得一定范围内的有效联系人用户。<br/>
	 * @param first 起始位置
	 * @param maxSize 最大数量
	 * @return
	 */
	public HessianRemoteReturning getValidContactUser(int first, int maxSize);
	
	/**
	 * 取得一定范围内的无效联系人用户。<br/>
	 * @param first 起始位置
	 * @param maxSize 最大数量
	 * @return
	 */
	public HessianRemoteReturning getInvalidContactUser(int first, int maxSize);
	
	/**
	 * 设置用户为离线状态。<br/>
	 * @param contactUser
	 * @return
	 */
	public HessianRemoteReturning editContactUserToOffline(ContactUser contactUser);
	
	/**
	 * 设置用户为离线状态。<br/>
	 * @param loginName
	 * @return
	 */
	public HessianRemoteReturning editContactUserToOffline(String loginName);
	
	/**
	 * 设置用户为在线状态。<br/>
	 * @param loginName
	 * @param password
	 * @param userLoginIp
	 * @param onlineTime
	 * @return
	 */
	public HessianRemoteReturning editContactUserToOnline(String loginName, String password, String userLoginIp,Date onlineTime);
	
	/**
	 * 用户登录次数增加一次。<br/>
	 * @param contactUser
	 * @return
	 */
	public HessianRemoteReturning addContactUserLoginNumber(ContactUser contactUser);
	
	/**
	 * 修改登录名称即修改电子邮件。<br/>
	 * @param loginName 现在登录名
	 * @param newLoginName 新的登录名
	 * @return
	 */
	public HessianRemoteReturning editLoginName(String loginName, String newLoginName);
	
	/**
	 * 修改手机号码。<br/>
	 * @param loginName 登录名
	 * @param newMobile 新的手机号码
	 * @return
	 */
	public HessianRemoteReturning editMobile(String loginName, String neoMobile);
	
	/**
	 * 修改密码。<br/>
	 * @param loginName 登录名
	 * @param oldPassword 旧密码
	 * @param neoPassword 新的密码
	 * @return
	 */
	public HessianRemoteReturning editPassword(String loginName, String oldPassword, String neoPassword);
	
	/**
	 * 设置多个用户为离线状态。<br/>
	 * @param loginNames
	 * @return
	 */
	public HessianRemoteReturning editContactUserToOffline(List<String> loginNames);
	
	
}
