package com.haina.beluga.domain;

import com.haina.beluga.core.model.VersionalModel;

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
	
	
	
	public String getDistrictNumber() {
		return districtNumber;
	}
	public void setDistrictNumber(String districtNumber) {
		this.districtNumber = districtNumber;
	}
	public Long getRangeStart() {
		return rangeStart;
	}
	public void setRangeStart(Long rangeStart) {
		this.rangeStart = rangeStart;
	}
	public Long getRangeEnd() {
		return rangeEnd;
	}
	public void setRangeEnd(Long rangeEnd) {
		this.rangeEnd = rangeEnd;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getDistrictCity() {
		return districtCity;
	}
	public void setDistrictCity(String districtCity) {
		this.districtCity = districtCity;
	}
	public String getDistrictProvince() {
		return districtProvince;
	}
	public void setDistrictProvince(String districtProvince) {
		this.districtProvince = districtProvince;
	}
	public int getUpdateFlg() {
		return updateFlg;
	}
	public void setUpdateFlg(int updateFlg) {
		this.updateFlg = updateFlg;
	}
	public String getPingyinCity() {
		return pingyinCity;
	}
	public void setPingyinCity(String pingyinCity) {
		this.pingyinCity = pingyinCity;
	}
	public String getWeatherCityCode() {
		return weatherCityCode;
	}
	public void setWeatherCityCode(String weatherCityCode) {
		this.weatherCityCode = weatherCityCode;
	}
	@Override
	public Long getVersion() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean equals(Object object) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
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
