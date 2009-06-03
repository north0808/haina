package com.haina.beluga.domain;

import org.springframework.stereotype.Component;

import com.haina.beluga.core.model.VersionalModel;
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
	private int updateFlg;
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
	 * @return int
	 */
	public int getUpdateFlg() {
		return updateFlg;
	}
	public void setUpdateFlg(int updateFlg) {
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
		// TODO Auto-generated method stub
		return version;
	}
	@Override
	public boolean equals(Object object) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @hibernate.id column="ID" generator-class="uuid.hex"  unsaved-value="null"
	 * @return String
	 */
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	
	
}
