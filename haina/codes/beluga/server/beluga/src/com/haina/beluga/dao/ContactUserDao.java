package com.haina.beluga.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import org.springframework.stereotype.Repository;

import com.haina.beluga.core.dao.BaseDao;
import com.haina.beluga.core.util.DESUtil;
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
	public ContactUser getValidUserByLoginName(String loginName) {
		ContactUser contactUser=null;
		if(!StringUtils.isNull(loginName)) {
			DetachedCriteria detachedCriteria=DetachedCriteria.forClass(ContactUser.class);
			detachedCriteria.add(Restrictions.and(
					Restrictions.eq("loginName", loginName), Restrictions.eq("validFlag", Boolean.TRUE)));
			List<ContactUser> list=this.getUserByHibernateCriteria(detachedCriteria);
//			this.getHibernateTemplate().findByNamedParam(
//					"from ContactUser u where u.loginName = :loginName and u.validFlag = :validFlag", 
//					new String[]{"loginName","validFlag"}, new Object[]{loginName,Boolean.TRUE});
			if(list!=null && list.size()>0) {
				contactUser=list.get(0);
			}
		}
		return contactUser;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ContactUser getValidUserByMobile(String mobile) {
		ContactUser contactUser=null;
		if(!StringUtils.isNull(mobile)) {
			DetachedCriteria detachedCriteria=DetachedCriteria.forClass(ContactUser.class);
			detachedCriteria.add(Restrictions.and(
					Restrictions.eq("mobile", mobile), Restrictions.eq("validFlag", Boolean.TRUE)));
			List<ContactUser> list=this.getUserByHibernateCriteria(detachedCriteria);
//			this.getHibernateTemplate().findByNamedParam(
//					"from ContactUser u where u.mobile = :mobile and u.validFlag = :validFlag", 
//					new String[]{"mobile","validFlag"}, new Object[]{mobile,Boolean.TRUE});
			if(list!=null && list.size()>0) {
				contactUser=list.get(0);
			}
		}
		return contactUser;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ContactUser getValidUserByPwdAndLoginName(String password,String loginName) {
		ContactUser contactUser=null;
		if(!StringUtils.isNull(loginName) && !StringUtils.isNull(password)) {
			ContactUser exampleUser=new ContactUser();
			exampleUser.setLoginName(loginName);
			exampleUser.setPassword(password);
			exampleUser.setValidFlag(Boolean.TRUE);
			List<ContactUser> list=this.getUserByExample(exampleUser);
//			this.getHibernateTemplate().findByNamedParam(
//					"from ContactUser u where u.loginName = :loginName and u.password = :password and u.validFlag = :validFlag", 
//					new String[]{"loginName","password","validFlag"}, new Object[]{loginName,password,Boolean.TRUE});
			if(list!=null && list.size()>0) {
				contactUser=list.get(0);
			}
		}
		return contactUser;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ContactUser getInvalidUserByPwdAndLoginName(String password,String loginName) {
		ContactUser contactUser=null;
		if(!StringUtils.isNull(loginName) && !StringUtils.isNull(password)) {
			ContactUser exampleUser=new ContactUser();
			exampleUser.setLoginName(loginName);
			exampleUser.setPassword(password);
			exampleUser.setValidFlag(Boolean.FALSE);
			List<ContactUser> list=this.getUserByExample(exampleUser);
//			this.getHibernateTemplate().findByNamedParam(
//					"from ContactUser u where u.loginName = :loginName and u.password = :password and u.validFlag = :validFlag", 
//					new String[]{"loginName","password","validFlag"}, new Object[]{loginName,password,Boolean.FALSE});
			if(list!=null && list.size()>0) {
				contactUser=list.get(0);
			}
		}
		return contactUser;
	}
	
	@Override
	public void deleteToInvalid(ContactUser contactUser) {
		if(contactUser!=null && !contactUser.isNew() 
				&& contactUser.getValidFlag()) {
			contactUser.setValidFlag(Boolean.FALSE);
			this.update(contactUser);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ContactUser getInvalidUserByLoginName(String loginName) {
		ContactUser contactUser=null;
		if(!StringUtils.isNull(loginName)) {
			DetachedCriteria detachedCriteria=DetachedCriteria.forClass(ContactUser.class);
			detachedCriteria.add(Restrictions.and(
					Restrictions.eq("loginName", loginName), Restrictions.eq("validFlag", Boolean.TRUE)));
			List<ContactUser> list=this.getUserByHibernateCriteria(detachedCriteria);
//			this.getHibernateTemplate().findByNamedParam(
//					"from ContactUser u where u.loginName = :loginName and u.validFlag = :validFlag", 
//					new String[]{"loginName","validFlag"}, new Object[]{loginName,Boolean.FALSE});
			if(list!=null && list.size()>0) {
				contactUser=list.get(0);
			}
		}
		return contactUser;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContactUser> getUserByMobileOrLoginName(String mobile, String loginName) {
		List<ContactUser> list=null;
		if(!StringUtils.isNull(mobile) &&  !StringUtils.isNull(loginName)) {
			DetachedCriteria detachedCriteria=DetachedCriteria.forClass(ContactUser.class);
			detachedCriteria.add(Restrictions.or(
					Restrictions.eq("mobile", mobile), Restrictions.eq("loginName", loginName)));
			list=this.getUserByHibernateCriteria(detachedCriteria);
//			this.getHibernateTemplate().findByNamedParam(
//					"from ContactUser u where u.loginName = :loginName or u.mobile = :mobile", 
//					new String[]{"loginName","mobile"}, new Object[]{loginName,mobile});
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContactUser> getInvalidUserByMobileOrLoginName(String mobile, String loginName) {
		List<ContactUser> list=null;
		if(!StringUtils.isNull(mobile) &&  !StringUtils.isNull(loginName)) {
			DetachedCriteria detachedCriteria=DetachedCriteria.forClass(ContactUser.class);
			detachedCriteria.add(Restrictions.and(Restrictions.eq("validFlag", Boolean.FALSE), Restrictions.or(
					Restrictions.eq("mobile", mobile), Restrictions.eq("loginName", loginName))));
			list=this.getUserByHibernateCriteria(detachedCriteria);
		}
		return list;
	}
	
	@Override
	public String create(ContactUser o) {
		return super.create(encryptPassword(o));
	}

	@Override
	public ContactUser getBaseModel() {
		ContactUser contactUser=super.getBaseModel();
		if(contactUser!=null && !StringUtils.isNull(contactUser.getPassword())) {
			contactUser.setPassword(DESUtil.decrypt(contactUser.getPassword().trim()));
		}
		return contactUser;
	}

	@Override
	public List<ContactUser> getModelByPage(ContactUser exampleEntity,
			int begin, int count) {
		List<ContactUser> list=super.getModelByPage(this.encryptPassword(exampleEntity), begin, count);
		list=this.decryptPassword(list);
		return list;
	}

	@Override
	public List<ContactUser> getModels(boolean useCache) {
		List<ContactUser> list=super.getModels(useCache);
		list=this.decryptPassword(list);
		return list;
	}

	@Override
	public ContactUser load(String id) {
		ContactUser contactUser=super.load(id);
		return this.decryptPassword(contactUser);
	}

	@Override
	public ContactUser read(String id) {
		ContactUser contactUser=super.read(id);
		return this.decryptPassword(contactUser);
	}

	@Override
	public void saveOrUpdate(ContactUser newInstance) {
		super.saveOrUpdate(encryptPassword(newInstance));
	}

	@Override
	public void setBaseModel(ContactUser t) {
		super.setBaseModel(encryptPassword(t));
	}

	@Override
	public void update(ContactUser o) {
		super.update(encryptPassword(o));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ContactUser> getUserByHibernateCriteria(DetachedCriteria criteria,int begin, int count) {
		int first=begin;
		int size=count;
		if(first<0) {
			first=1;
		}
		if(size<0) {
			size=1;
		}
		List<ContactUser> list=null;
		if(criteria!=null) {
			list=this.getModelByHibernateCriteria(criteria, begin, count);
			list=this.decryptPassword(list);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ContactUser> getUserByHibernateCriteria(DetachedCriteria criteria) {
		List<ContactUser> list=null;
		if(criteria!=null) {
			list=this.getModelByHibernateCriteria(criteria);
			list=this.decryptPassword(list);
		}
		return list;
	}
	
	private ContactUser encryptPassword(ContactUser contactUser) {
		ContactUser user=contactUser;
		if(user!=null && !StringUtils.isNull(user.getPassword())) {
			user.setPassword(DESUtil.encrypt(user.getPassword().trim()));
		}
		return user;
	}
	
	private ContactUser decryptPassword(ContactUser contactUser) {
		ContactUser user=contactUser;
		if(user!=null && !StringUtils.isNull(user.getPassword())) {
			user.setPassword(DESUtil.decrypt(user.getPassword().trim()));
		}
		return user;
	}
	
	private List<ContactUser> decryptPassword(List<ContactUser> contactUsers) {
		List<ContactUser> list=contactUsers;
		if(list!=null && list.size()>0) {
			for(int i=0;i<list.size();i++) {
				list.set(i, this.decryptPassword(list.get(i)));
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContactUser> getUserByExample(ContactUser contactUser) {
		List<ContactUser> list=this.getHibernateTemplate().findByExample(this.encryptPassword(contactUser));
		return this.decryptPassword(list);
	}
}
