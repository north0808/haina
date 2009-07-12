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
	public LoginPassport addPassport(ContactUser contactUser);
	
	/**
	 * 更新护照。<br/>
	 * @param passport 护照
	 * @return
	 */
	public LoginPassport updatePassport(String passport);
	
	/**
	 * 取得登录护照对象。<br/>
	 * @param passport 护照
	 * @return
	 */
	public LoginPassport getPassport(String passport);
	
	/**
	 * 是否过期的护照。<br/>
	 * @param passport 护照
	 * @return
	 */
	public boolean isExpiredPassport(String passport);
	
	/**
	 * 是否过期的护照。<br/>
	 * @param loginPassport 护照对象
	 * @return
	 */
	public boolean isExpiredPassport(LoginPassport loginPassport); 
	
	/**
	 * 删除单个护照。<br/>
	 * @param passport 护照
	 * @return
	 */
	public boolean removePassport(String passport);
	
	/**
	 * 删除所有护照。<br/>
	 * @return
	 */
	public boolean removeAllPassport();
	
	/**
	 * 是否过期的登录。<br/>
	 * @param passport 登录名
	 * @return
	 */
	public boolean isExpiredLogin(String passport);
	
	/**
	 * 使登录失效。<br/>
	 * @param passport
	 * @return
	 */
	public boolean expireLogin(String passport);
}
