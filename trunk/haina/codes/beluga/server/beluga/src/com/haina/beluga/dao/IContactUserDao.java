package com.haina.beluga.dao;

import java.util.Set;

import com.haina.beluga.core.dao.IBaseDao;
import com.haina.beluga.domain.ContactUser;
import com.haina.beluga.domain.UserProfile;
import com.haina.beluga.domain.UserProfileExt;

/**
 * 联系人用户领域模型类Dao访问接口。<br/>
 * @author huangyongqiang
 * @version 1.0
 * @since 1.0
 * @date 2009-06-29
 */
public interface IContactUserDao extends IBaseDao<ContactUser,String> {
	
	/**
	 * 通过登录名得到用户。<br/>
	 * @param loginName
	 * @return
	 */
	public ContactUser getContactUserByLoginName(String loginName);
	
	/**
	 * 通过登录名得到用户详细信息。<br/>
	 * @param loginName
	 * @return
	 */
	public UserProfile getUserProfileByLoginName(String loginName);
	
	/**
	 * 通过登录名得到用户详细扩展信息。<br/>
	 * @param loginName
	 * @return
	 */
	public Set<UserProfileExt> getUserProfileExtByLoginName(String loginName);
	
	/**
	 * 通过用户Id得到用户详细信息。<br/>
	 * @param id
	 * @return
	 */
	public UserProfile getUserProfileById(String id);
	
	/**
	 * 通过用户Id得到用户详细扩展信息。<br/>
	 * @param id
	 * @return
	 */
	public Set<UserProfileExt> getUserProfileExtById(String id);

}
