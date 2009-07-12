package com.haina.beluga.service;

import org.springframework.beans.factory.InitializingBean;

import com.haina.beluga.domain.ContactUser;

/**
 * 用户验证护照业务处理接口。<br/>
 * @author huangyongqiang
 * @version 1.0
 * @since 1.0
 * @data 2009-07-11
 *
 */
public interface IPassportService extends InitializingBean {

	/**
	 * 添加护照。<br/>
	 * @param contactUser 联系人用户
	 * @return
	 */
	public ContactUserToken addPassport(ContactUser contactUser);
	
	/**
	 * 更新护照。<br/>
	 * @param loginName 登录名
	 * @return
	 */
	public ContactUserToken updatePassport(String loginName);
	
	/**
	 * 取得护照。<br/>
	 * @param loginName 登录名
	 * @return
	 */
	public ContactUserToken getPassport(String loginName);
	
	/**
	 * 是否过期的护照。<br/>
	 * @param loginName 登录名
	 * @return
	 */
	public boolean isExpiredPassport(String loginName);
	
	/**
	 * 是否过期的护照。<br/>
	 * @param passportDto 护照对象
	 * @return
	 */
	public boolean isExpiredPassport(ContactUserToken contactUserToken); 
	
	/**
	 * 删除单个护照。<br/>
	 * @param loginName 登录名
	 * @return
	 */
	public boolean removePassport(String loginName);
	
	/**
	 * 删除所有护照。<br/>
	 * @return
	 */
	public boolean removeAllPassport();
	
	/**
	 * 是否过期的登录。<br/>
	 * @param loginName 登录名
	 * @return
	 */
	public boolean isExpiredLogin(String loginName);
	
	/**
	 * 使登录失效。<br/>
	 * @param loginName
	 * @return
	 */
	public boolean expireLogin(String loginName);
}
