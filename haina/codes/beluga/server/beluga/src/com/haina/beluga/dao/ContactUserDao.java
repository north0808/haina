package com.haina.beluga.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

//import org.springframework.stereotype.Repository;

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

@Repository(value="contactUserDao")
public class ContactUserDao extends BaseDao<ContactUser,String> implements IContactUserDao {

	@SuppressWarnings("unchecked")
	@Override
	public ContactUser getContactUserByLoginName(String loginName) {
		ContactUser contactUser=null;
		if(!StringUtils.isNull(loginName)) {
			List<ContactUser> list=this.getHibernateTemplate().findByNamedParam(
					"from ContactUser u where u.loginName = :loginName", "loginName", loginName);
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
					"from ContactUser u where u.mobile = :mobile", "mobile", mobile);
			if(list!=null && list.size()>0) {
				contactUser=list.get(0);
			}
		}
		return contactUser;
	}
}
