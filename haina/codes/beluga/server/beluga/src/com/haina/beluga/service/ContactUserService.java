package com.haina.beluga.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haina.beluga.core.service.BaseSerivce;
import com.haina.beluga.core.util.StringUtils;
import com.haina.beluga.dao.IContactUserDao;
import com.haina.beluga.domain.ContactUser;
import com.haina.beluga.domain.UserProfile;
import com.haina.beluga.domain.UserProfileExt;

/**
 * 联系人用户业务处理接口实现类。<br/>
 * 
 * @author huangyongqiang
 * @version 1.0
 * @since 1.0
 * @date 2009-07-05
 */

@Service(value="contactUserService")
public class ContactUserService extends BaseSerivce<IContactUserDao,ContactUser,String> implements
		IContactUserService {

	@Autowired(required=true)
	private IContactUserDao contactUserDao;
	
	@Override
	public ContactUser addContactUser(String loginName, String password,
			String mobile) {
		if(StringUtils.isNull(loginName) || StringUtils.isNull(mobile)) {
			return null;
		}
		ContactUser contactUser=contactUserDao.getContactUserByLoginName(loginName);
		if(null!=contactUser) {
			return contactUser;
		} 
		contactUser=contactUserDao.getContactUserByMobile(mobile);
		if(null!=contactUser) {
			return contactUser;
		} 
		contactUser=new ContactUser();
		contactUser.setLoginName(loginName);
		contactUser.setMobile(mobile);
		contactUser.setRegisterTime(new Date());
		contactUser.setValidFlag(Boolean.TRUE);
		
		UserProfile userProfile=new UserProfile();
		userProfile.setTelPref(mobile);
		
		contactUser.setUserProfile(userProfile);
		userProfile.setContactUser(contactUser);
		
		contactUserDao.saveOrUpdate(contactUser);
		return contactUser;
	}

	@Override
	public List<ContactUser> getContactUser(int first, int maxSize) {
		return contactUserDao.getModelByPage(null, first, maxSize);
	}

	@Override
	public ContactUser getContactUserById(String userId) {
		return contactUserDao.read(userId);
	}

	@Override
	public ContactUser getContactUserByLoginName(String loginName) {
		return contactUserDao.getContactUserByLoginName(loginName);
	}

	@Override
	public List<ContactUser> getInvalidContactUser(int first, int maxSize) {
		ContactUser contactUser=new ContactUser();
		contactUser.setValidFlag(Boolean.FALSE);
		return contactUserDao.getModelByPage(contactUser, first, maxSize);
	}

	@Override
	public UserProfile getUserProfileById(String id) {
		ContactUser contactUser=contactUserDao.read(id);
		return contactUser!=null ? contactUser.getUserProfile() : null;
	}

	@Override
	public UserProfile getUserProfileByLoginName(String loginName) {
		ContactUser contactUser=contactUserDao.getContactUserByLoginName(loginName);
		return contactUser!=null ? contactUser.getUserProfile() : null;
	}

	@Override
	public Set<UserProfileExt> getUserProfileExtById(String id) {
		ContactUser contactUser=contactUserDao.read(id);
		return contactUser!=null ? contactUser.getUserProfileExts() : null;
	}

	@Override
	public Set<UserProfileExt> getUserProfileExtByLoginName(String loginName) {
		ContactUser contactUser=contactUserDao.getContactUserByLoginName(loginName);
		return contactUser!=null ? contactUser.getUserProfileExts() : null;
	}

	@Override
	public List<ContactUser> getValidContactUser(int first, int maxSize) {
		ContactUser contactUser=new ContactUser();
		contactUser.setValidFlag(Boolean.TRUE);
		return contactUserDao.getModelByPage(contactUser, first, maxSize);
	}

}
