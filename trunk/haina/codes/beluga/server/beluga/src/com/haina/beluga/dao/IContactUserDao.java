package com.haina.beluga.dao;

import com.haina.beluga.core.dao.IBaseDao;
import com.haina.beluga.domain.ContactUser;

/**
 * 联系人用户领域模型类Dao访问能接口。<br/>
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

}
