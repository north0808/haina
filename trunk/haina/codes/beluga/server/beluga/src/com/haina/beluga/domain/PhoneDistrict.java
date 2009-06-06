package com.haina.beluga.domain;

import org.springframework.stereotype.Component;

import com.haina.beluga.core.model.VersionalModel;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/**
*
 * @hibernate.class table="PhoneDistrict"
 */
 @Component
public class PhoneDistrict extends VersionalModel {

	private static final long serialVersionUID = -8573934053645649408L;
	private String districtNumber;
	private Long rangeStart;
	private Long rangeEnd;
	private String feeType;
	private String districtCity;
	private String districtProvince;
	private Integer updateFlg;
	private String pingyinCity;
	private String weatherCityCode;
	
	
	 /**
	 * @hibernate.property column="districtNumber" length="20"
	 * @return String
	 */
	public String getDistrictNumber() {
		return districtNumber;
	}
	public void setDistrictNumber(String districtNumber) {
		this.districtNumber = districtNumber;
	}
	/**
	 * @hibernate.property column="rangeStart"
	 * @return Long
	 */
	public Long getRangeStart() {
		return rangeStart;
	}
	public void setRangeStart(Long rangeStart) {
		this.rangeStart = rangeStart;
	}
	/**
	 * @hibernate.property column="rangeEnd"
	 * @return Long
	 */
	public Long getRangeEnd() {
		return rangeEnd;
	}
	public void setRangeEnd(Long rangeEnd) {
		this.rangeEnd = rangeEnd;
	}
	/**
	 * @hibernate.property column="feeType" length="20"
	 * @return String
	 */
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	/**
	 * @hibernate.property column="districtCity" length="20"
	 * @return String
	 */
	public String getDistrictCity() {
		return districtCity;
	}
	public void setDistrictCity(String districtCity) {
		this.districtCity = districtCity;
	}
	/**
	 * @hibernate.property column="districtProvince" length="20"
	 * @return String
	 */
	public String getDistrictProvince() {
		return districtProvince;
	}
	public void setDistrictProvince(String districtProvince) {
		this.districtProvince = districtProvince;
	}
	/**
	 * @hibernate.property column="updateFlg"
	 * @return Integer
	 */
	public Integer getUpdateFlg() {
		return updateFlg;
	}
	public void setUpdateFlg(Integer updateFlg) {
		this.updateFlg = updateFlg;
	}
	/**
	 * @hibernate.property column="pingyinCity" length="20"
	 * @return String
	 */
	public String getPingyinCity() {
		return pingyinCity;
	}
	public void setPingyinCity(String pingyinCity) {
		this.pingyinCity = pingyinCity;
	}
	/**
	 * @hibernate.property column="weatherCityCode" length="20"
	 * @return String
	 */
	public String getWeatherCityCode() {
		return weatherCityCode;
	}
	public void setWeatherCityCode(String weatherCityCode) {
		this.weatherCityCode = weatherCityCode;
	}
	@Override
	public Long getVersion() {
		return version;
	}
	
	/**
	 * @hibernate.id column="ID" generator-class="uuid.hex"  unsaved-value="null"
	 * @return String
	 */
	@Override
	public String getId() {
		return id;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	@Override
	public String toString() {
		return null;
	}
	
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof PhoneDistrict)) {
			return false;
		}
		PhoneDistrict rhs = (PhoneDistrict) object;
		return new EqualsBuilder().append(
				this.id, rhs.id).append(
				this.districtNumber, rhs.districtNumber).append(
				this.districtProvince, rhs.districtProvince).append(
				this.rangeEnd, rhs.rangeEnd).append(this.pingyinCity,
				rhs.pingyinCity).append(this.districtCity, rhs.districtCity)
				.append(this.feeType, rhs.feeType).append(this.weatherCityCode,
						rhs.weatherCityCode).append(this.rangeStart,
						rhs.rangeStart).append(this.updateFlg, rhs.updateFlg).isEquals();
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(1464919849, -139111521)
		.append(this.id).append(this.districtNumber).append(
				this.districtProvince).append(this.rangeEnd).append(
				this.pingyinCity).append(this.districtCity)
				.append(this.feeType).append(this.weatherCityCode).append(
						this.rangeStart).append(this.updateFlg).toHashCode();
	}
	
	
	
}
