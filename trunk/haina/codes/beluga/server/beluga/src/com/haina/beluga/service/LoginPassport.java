package com.haina.beluga.service;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 联系人用户登录护照。<br/>
 * @author huangyongqiang
 * @version 1.0
 * @since 1.0
 * @data 2009-07-11
 */
public class LoginPassport implements Serializable {

	private static final long serialVersionUID = 808574421082242925L;
	
	/*电子邮件即登录名。*/
	private String email;
	
	/*手机号码。*/
	//private String mobile;
	
	/*登录时间点对应的从1970年1月1日0点算起的毫秒数，比如"456799999999999"。*/
	private Long loginTime;
	
	/*登录超期时间毫秒数。即在loginTime+loginExpiry之后，登录超期。必须是passportExpiry正数倍。
	 *比如"100000000000"。*/
	private Long loginExpiry;
	
	/*保持登录心跳的护照。*/
	private String passport;
	
	/*产生护照的时间点对应的从1970年1月1日0点算起的毫秒数，比如"45679000000000"。*/
	private Long passportTime;
	
	/*护照超期时间毫秒数。即在用户登录有效期内，每隔passportExpiry毫秒，passport更新一次。比如"100000000000"。*/
	private Long passportExpiry;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}

	public Long getLoginExpiry() {
		return loginExpiry;
	}

	public void setLoginExpiry(Long loginExpiry) {
		this.loginExpiry = loginExpiry;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public Long getPassportExpiry() {
		return passportExpiry;
	}

	public void setPassportExpiry(Long passportExpiry) {
		this.passportExpiry = passportExpiry;
	}

	public Long getPassportTime() {
		return passportTime;
	}

	public void setPassportTime(Long passportTime) {
		this.passportTime = passportTime;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof LoginPassport)) {
			return false;
		}
		LoginPassport rhs = (LoginPassport) object;
		return new EqualsBuilder().appendSuper(super.equals(object)).append(
				this.loginTime, rhs.loginTime).append(this.passportTime,
				rhs.passportTime).append(this.passportExpiry,
				rhs.passportExpiry).append(this.email, rhs.email).append(
				this.loginExpiry, rhs.loginExpiry).append(this.passport,
				rhs.passport).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1898689585, 1572956259).appendSuper(
				super.hashCode()).append(this.loginTime).append(
				this.passportTime).append(this.passportExpiry).append(
				this.email).append(this.loginExpiry).append(this.passport)
				.toHashCode();
	}

	
}
