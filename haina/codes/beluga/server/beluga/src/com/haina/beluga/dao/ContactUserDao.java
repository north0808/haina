package com.haina.beluga.dao;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.haina.beluga.core.dao.BaseDao;
import com.haina.beluga.core.util.StringUtils;
import com.haina.beluga.domain.ContactUser;
import com.haina.beluga.domain.UserProfile;
import com.haina.beluga.domain.UserProfileExt;

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
					"from ContactUser u where u.logingName = : logingName", "logingName", loginName);
			if(list!=null && list.size()>0) {
				contactUser=list.get(0);
			}
		}
		return contactUser;
	}

	@Override
	public UserProfile getUserProfileById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile getUserProfileByLoginName(String loginName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<UserProfileExt> getUserProfileExtById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<UserProfileExt> getUserProfileExtByLoginName(String loginName) {
		// TODO Auto-generated method stub
		return null;
	}
}
