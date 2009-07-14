package com.haina.beluga.dao;

import java.util.List;

import com.haina.beluga.core.dao.BaseDao;
import com.haina.beluga.core.util.StringUtils;
import com.haina.beluga.domain.ContactUser;

/**
 * 联系人用户领域模型类Dao访问接口实现类。<br/>
 * @author huangyongqiang
 * @version 1.0
 * @since 1.0
 * @date 2009-07-03
 */

//@Repository(value="contactUserDao")
public class ContactUserDao extends BaseDao<ContactUser,String> implements IContactUserDao {

	@SuppressWarnings("unchecked")
	@Override
	public ContactUser getContactUserByLoginName(String loginName) {
		ContactUser contactUser=null;
		if(!StringUtils.isNull(loginName)) {
			List<ContactUser> list=this.getHibernateTemplate().findByNamedParam(
					"from ContactUser u where u.loginName = :loginName and u.validFlag = :validFlag", 
					new String[]{"loginName","validFlag"}, new Object[]{loginName,Boolean.TRUE});
			if(list!=null && list.size()>0) {
				contactUser=list.get(0);
			}
		}
		return contactUser;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ContactUser getContactUserByMobile(String mobile) {
		ContactUser contactUser=null;
		if(!StringUtils.isNull(mobile)) {
			List<ContactUser> list=this.getHibernateTemplate().findByNamedParam(
					"from ContactUser u where u.mobile = :mobile and u.validFlag = :validFlag", 
					new String[]{"mobile","validFlag"}, new Object[]{mobile,Boolean.TRUE});
			if(list!=null && list.size()>0) {
				contactUser=list.get(0);
			}
		}
		return contactUser;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ContactUser getContactUserByLoginNameAndPwd(String loginName,
			String password) {
		ContactUser contactUser=null;
		if(!StringUtils.isNull(loginName) && !StringUtils.isNull(password)) {
			List<ContactUser> list=this.getHibernateTemplate().findByNamedParam(
					"from ContactUser u where u.loginName = :loginName and u.password = :password and u.validFlag = :validFlag", 
					new String[]{"loginName","password","validFlag"}, new Object[]{loginName,password,Boolean.TRUE});
			if(list!=null && list.size()>0) {
				contactUser=list.get(0);
			}
		}
		return contactUser;
	}
	
	public void deleteToInvalid(ContactUser contactUser) {
		if(contactUser!=null && !contactUser.isNew() 
				&& contactUser.getValidFlag()) {
			contactUser.setValidFlag(Boolean.FALSE);
			getHibernateTemplate().update(contactUser);
		}
	}
}
