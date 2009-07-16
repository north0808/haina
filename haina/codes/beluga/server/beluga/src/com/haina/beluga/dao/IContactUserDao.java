package com.haina.beluga.dao;


import com.haina.beluga.core.dao.IBaseDao;
import com.haina.beluga.domain.ContactUser;

/**
 * 联系人用户领域模型类Dao访问接口。<br/>
 * @author huangyongqiang
 * @version 1.0
 * @since 1.0
 * @date 2009-06-29
 */
public interface IContactUserDao extends IBaseDao<ContactUser,String> {
	
	/**
	 * 通过登录名得到有效用户。<br/>
	 * @param loginName
	 * @return
	 */
	public ContactUser getValidUserByLoginName(String loginName);
	
	/**
	 * 通过登录名得到无效用户。<br/>
	 * @param loginName
	 * @return
	 */
	public ContactUser getInvalidUserByLoginName(String loginName);
	
	/**
	 * 通过手机号码得到有效用户。<br/>
	 * @param mobile
	 * @return
	 */
	public ContactUser getValidUserByMobile(String mobile);
	
	/**
	 * 通过登录名和密码得到有效用户。<br/>
	 * @param loginName
	 * @return
	 */
	public ContactUser getValidUserByLoginNameAndPwd(String loginName,String password);
	
	/**
	 * 通过登录名和密码得到无效用户。<br/>
	 * @param loginName
	 * @return
	 */
	public ContactUser getInvalidUserByLoginNameAndPwd(String loginName,String password);
	
	/**
	 * 设置用户为无效，即逻辑删除。<br/>
	 * @param contactUser
	 */
	public void deleteToInvalid(ContactUser contactUser);
}
