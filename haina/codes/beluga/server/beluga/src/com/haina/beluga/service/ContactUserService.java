package com.haina.beluga.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.haina.beluga.core.service.BaseSerivce;
import com.haina.beluga.core.util.StringUtils;
import com.haina.beluga.dao.IContactUserDao;
import com.haina.beluga.domain.ContactUser;
import com.haina.beluga.domain.UserProfile;
import com.haina.beluga.domain.UserProfileExt;
import com.haina.beluga.domain.enumerate.SexEnum;

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

//	private IContactUserDao contactUserDao=this.getBaseDao();
	
	@Override
	public ContactUser addContactUser(String loginName, String password,
			String mobile,Integer userStatus,String userIp) {
		if(StringUtils.isNull(loginName) || StringUtils.isNull(mobile)) {
			return null;
		}
		ContactUser contactUser=getBaseDao().getContactUserByLoginName(loginName);
		if(null==contactUser) {
			/*新用户。*/
			contactUser=getBaseDao().getContactUserByMobile(mobile);
			if(null!=contactUser && contactUser.getValidFlag()) {
				return null;
			}
			Date now=new Date();
			contactUser=new ContactUser();
			contactUser.setLoginName(loginName);
			contactUser.setPassword(password);
			contactUser.setMobile(mobile);
			contactUser.setRegisterTime(now);
			contactUser.setLastLoginTime(now);
			contactUser.setValidFlag(Boolean.TRUE);
			contactUser.setUserStatus(userStatus);
			if(userStatus!=null && userStatus.equals(ContactUser.USER_STATUS_ONLINE)) {
				contactUser.setLastLoginIp(userIp);
			}
			UserProfile userProfile=new UserProfile();
			userProfile.setTelPref(mobile);
			//TODO:setSex.
			userProfile.setSex(SexEnum.male);
			
			contactUser.setUserProfile(userProfile);
			userProfile.setContactUser(contactUser);
			getBaseDao().create(contactUser);
			return contactUser;
		} else {
			contactUser=this.editContactUserToValid(loginName, password, mobile, userStatus, userIp);
			return contactUser;
		}
	}

	@Override
	public ContactUser editContactUserToValid(ContactUser contactUser) {
		if(null==contactUser) {
			return null;
		}
		if(contactUser.isNew()) {
			return null;
		}
		ContactUser tempContactUser=getBaseDao().read(contactUser.getId());
		if(!tempContactUser.getPassword().equals(contactUser.getPassword())) {
			return null;
		}
		if(!contactUser.getValidFlag()) {
			contactUser.setValidFlag(Boolean.TRUE);
			getBaseDao().update(contactUser);
		}
		return contactUser;
	}
	
	@Override
	public ContactUser editContactUserToValid(String loginName, String password,
			String mobile,Integer userStatus,String userIp) {
		if(StringUtils.isNull(loginName) || StringUtils.isNull(password) ||
				StringUtils.isNull(mobile)) {
			return null;
		}
		ContactUser contactUser=getBaseDao().getContactUserByLoginName(loginName);
		if(null==contactUser || !contactUser.getPassword().equals(password)) {
			return null;
		}
		if(contactUser.getValidFlag()) {
			return contactUser;
		}
		contactUser.setMobile(mobile);
		contactUser.setValidFlag(Boolean.TRUE);
		contactUser.setUserStatus(userStatus);
		if(userStatus!=null && userStatus.equals(ContactUser.USER_STATUS_ONLINE)) {
			contactUser.setLastLoginIp(userIp);
			contactUser.setLastLoginTime(new Date());
		}
		contactUser.getUserProfile().setTelPref(mobile);
		getBaseDao().update(contactUser);
		return contactUser;
	}
	
	@Override
	public List<ContactUser> getContactUser(int first, int maxSize) {
		return getBaseDao().getModelByPage(null, first, maxSize);
	}

	@Override
	public ContactUser getContactUserById(String userId) {
		return getBaseDao().read(userId);
	}

	@Override
	public ContactUser getContactUserByLoginName(String loginName) {
		return getBaseDao().getContactUserByLoginName(loginName);
	}

	@Override
	public List<ContactUser> getInvalidContactUser(int first, int maxSize) {
		ContactUser contactUser=new ContactUser();
		contactUser.setValidFlag(Boolean.FALSE);
		return getBaseDao().getModelByPage(contactUser, first, maxSize);
	}

	@Override
	public UserProfile getUserProfileById(String id) {
		ContactUser contactUser=getBaseDao().read(id);
		return contactUser!=null ? contactUser.getUserProfile() : null;
	}

	@Override
	public UserProfile getUserProfileByLoginName(String loginName) {
		ContactUser contactUser=getBaseDao().getContactUserByLoginName(loginName);
		return contactUser!=null ? contactUser.getUserProfile() : null;
	}

	@Override
	public Set<UserProfileExt> getUserProfileExtById(String id) {
		ContactUser contactUser=getBaseDao().read(id);
		return contactUser!=null ? contactUser.getUserProfileExts() : null;
	}

	@Override
	public Set<UserProfileExt> getUserProfileExtByLoginName(String loginName) {
		ContactUser contactUser=getBaseDao().getContactUserByLoginName(loginName);
		return contactUser!=null ? contactUser.getUserProfileExts() : null;
	}

	@Override
	public List<ContactUser> getValidContactUser(int first, int maxSize) {
		ContactUser contactUser=new ContactUser();
		contactUser.setValidFlag(Boolean.TRUE);
		return getBaseDao().getModelByPage(contactUser, first, maxSize);
	}

	@Override
	public ContactUser editContactUserToOffline(ContactUser contactUser) {
		ContactUser user=contactUser;
		if(null!=user && user.isOnline()) {
			user.setUserStatus(ContactUser.USER_STATUS_OFFLINE);
		}
		this.getBaseDao().update(user);
		return user;
	}

	@Override
	public ContactUser editContactUserToOnline(String loginName, String password,
			String userLoginIp) {
		if(StringUtils.isNull(loginName) || StringUtils.isNull(password)) {
			return null;
		}
		ContactUser contactUser=getBaseDao().getContactUserByLoginName(loginName);
		if(null==contactUser) {
			return null;
		}
		if(!contactUser.getValidFlag()) {
			return contactUser;
		}
		if(contactUser.getUserStatus().equals(ContactUser.USER_STATUS_OFFLINE)) {
			contactUser.setUserStatus(ContactUser.USER_STATUS_ONLINE);
			contactUser.setLastLoginIp(userLoginIp);
			contactUser.setLastLoginTime(new Date());
			getBaseDao().update(contactUser);
		}
		return contactUser;
	}

	@Override
	public ContactUser editContactUserToOffline(String loginName) {
		if(StringUtils.isNull(loginName)) {
			return null;
		}
		ContactUser contactUser=getBaseDao().getContactUserByLoginName(loginName);
		if(null==contactUser) {
			return null;
		}
		if(!contactUser.getValidFlag()) {
			return contactUser;
		}
		if(contactUser.getUserStatus().equals(ContactUser.USER_STATUS_ONLINE)) {
			contactUser.setUserStatus(ContactUser.USER_STATUS_OFFLINE);
			getBaseDao().update(contactUser);
		}
		return contactUser;
	}
	
	@Override
	public ContactUser addContactUserLoginNumber(ContactUser contactUser) {
		ContactUser user=contactUser;
		if(null!=user && user.isOnline()) {
			user.setLoginNumber(user.getLoginNumber()!=null ? user.getLoginNumber()+1 : 1);
			this.getBaseDao().update(user);
		}
		return user;
	}

	@Override
	public ContactUser editLoginName(String loginName, String newLoginName) {
		if(StringUtils.isNull(loginName) || StringUtils.isNull(newLoginName)) {
			return null;
		}
		ContactUser contactUser=getBaseDao().getContactUserByLoginName(loginName);
		if(null==contactUser) {
			return null;
		}
		if(!contactUser.getValidFlag()) {
			return contactUser;
		}
		contactUser.setLoginName(newLoginName);
		contactUser.getUserProfile().setEmailPref(newLoginName);
		getBaseDao().update(contactUser);
		return contactUser;
	}

	@Override
	public ContactUser editMobile(String loginName, String neoMobile) {
		if(StringUtils.isNull(loginName) || StringUtils.isNull(neoMobile)) {
			return null;
		}
		ContactUser contactUser=getBaseDao().getContactUserByLoginName(loginName);
		if(null==contactUser) {
			return null;
		}
		if(!contactUser.getValidFlag()) {
			return contactUser;
		}
		contactUser.setMobile(neoMobile);
		contactUser.getUserProfile().setTelPref(neoMobile);
		getBaseDao().update(contactUser);
		return contactUser;
	}

	@Override
	public ContactUser editPassword(String loginName, String neoPassword) {
		if(StringUtils.isNull(loginName) || StringUtils.isNull(neoPassword)) {
			return null;
		}
		ContactUser contactUser=getBaseDao().getContactUserByLoginName(loginName);
		if(null==contactUser) {
			return null;
		}
		if(!contactUser.getValidFlag()) {
			return contactUser;
		}
		contactUser.setPassword(neoPassword);
		getBaseDao().update(contactUser);
		return contactUser;
	}

}
