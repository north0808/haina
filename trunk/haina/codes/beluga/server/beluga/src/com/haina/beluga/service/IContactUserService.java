package com.haina.beluga.service;

import java.util.List;

import com.haina.beluga.core.service.IBaseSerivce;
import com.haina.beluga.dao.IContactUserDao;
import com.haina.beluga.domain.ContactUser;

/**
 * 联系人用户业务处理接口。<br/>
 * 
 * @author huangyongqiang
 * @version 1.0
 * @since 1.0
 * @date 2009-07-03
 */
public interface IContactUserService extends IBaseSerivce<IContactUserDao,ContactUser,String> {

	/**
	 * 添加联系人用户。<br/>
	 * @param loginName 登录名称。
	 * @param password 密码
	 * @param mobile 手机号码
	 * @return
	 */
	public ContactUser addContactUser(String loginName, String password, String mobile);
	
	/**
	 * 通过Id取得联系人用户信息。<br/>
	 * @param userId
	 * @return
	 */
	public ContactUser getContactUserById(String userId);
	
	/**
	 * 通过登录名称取得联系人用户信息。<br/>
	 * @param loginName
	 * @return
	 */
	public ContactUser getContactUserByLoginName(String loginName);
	
	/**
	 * 取得一定范围内的联系人用户。<br/>
	 * @param first 起始位置
	 * @param maxSize 最大数量
	 * @return
	 */
	public List<ContactUser> getContactUser(int first, int maxSize);
	
	/**
	 * 取得一定范围内的有效联系人用户。<br/>
	 * @param first 起始位置
	 * @param maxSize 最大数量
	 * @return
	 */
	public List<ContactUser> getValidContactUser(int first, int maxSize);
	
	/**
	 * 取得一定范围内的无效联系人用户。<br/>
	 * @param first 起始位置
	 * @param maxSize 最大数量
	 * @return
	 */
	public List<ContactUser> getInvalidContactUser(int first, int maxSize);
	
	
}