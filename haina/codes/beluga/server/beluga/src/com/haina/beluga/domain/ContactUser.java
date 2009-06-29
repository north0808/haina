package com.haina.beluga.domain;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.haina.beluga.core.model.VersionalModel;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 联系人用户表的领域模型类。<br/>
 * @author huangyongqiang
 * @version 1.0
 * @since 1.0
 * @date 2009-06-26
 * @hibernate.class table="ContactUser"
 * @hibernate.cache usage="read-write"
 */

/**
 * @author Administrator
 *
 */
@Component
public class ContactUser extends VersionalModel {

	private static final long serialVersionUID = 6223217962960596222L;

	private String logingName;
	
	private Date registerTime;
	
	private String mobile;
	
	private Date lastLoginTime;
	
	private String lastLoginIp;
	
	private String remark;
	
	/**
	 * @hibernate.id column="id"  unsaved-value="null" type = "java.lang.String" length="32"
	 * @hibernate.generator class="uuid.hex"
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * @hibernate.property column="logingName" length="64" not-null="true" type = "java.lang.String" unique = "true"
	 */
	public String getLogingName() {
		return logingName;
	}

	public void setLogingName(String logingName) {
		this.logingName = logingName;
	}

	/**
	 * @hibernate.property column="registerTime" not-null="true"  type = "java.util.Date"
	 */
	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	/**
	 * @hibernate.property column="mobile" length="64" not-null="true" type = "java.lang.String" unique = "true"
	 */
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @hibernate.property column="lastLoginTime" type = "java.util.Date"
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * @hibernate.property column="lastLoginIp" length="64" type = "java.lang.String"
	 */
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	/**
	 * @hibernate.version column="version" type = "java.lang.Long"
	 */
	@Override
	public Long getVersion() {
		return version;
	}

	
	/**
	 * 数据库手动修改记录。
	 * @hibernate.property column="remark" length="2000" type = "java.lang.String"
	 */
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ContactUser)) {
			return false;
		}
		ContactUser rhs = (ContactUser) object;
		return new EqualsBuilder().append(
				this.lastLoginIp, rhs.lastLoginIp).append(this.lastLoginTime,
				rhs.lastLoginTime).append(this.registerTime, rhs.registerTime)
				.append(this.logingName, rhs.logingName).append(this.mobile, rhs.mobile).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(1134122519, -1554226803).append(this.lastLoginIp).append(
				this.lastLoginTime).append(this.registerTime).append(
				this.logingName).append(this.mobile)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("logingName", this.logingName).append("mobile", this.mobile).append(
						"lastLoginIp", this.lastLoginIp).append(
						"lastLoginTime", this.lastLoginTime).append(
						"registerTime", this.registerTime).append("id",
						this.getId()).toString();
	}
	
}
