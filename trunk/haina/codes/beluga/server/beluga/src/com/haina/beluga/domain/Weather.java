package com.haina.beluga.domain;

import org.springframework.stereotype.Component;

import com.haina.beluga.core.model.VersionalModel;
/**
*
 * @hibernate.class table="Weather"
 * @hibernate.cache usage="read-write"
 */
 @Component
public class Weather extends VersionalModel{

	
	private static final long serialVersionUID = -326286128859607526L;
	private String date;/*天气日期*/ 
	private String weatherCityCode; 
	private String weatherType; 
	private boolean isNight;
	private int high; 
	private int low; 
	private String wind;
	private String icon;
	private String issuetime;/*发布时间*/

	
	 /**
	 * @hibernate.property column="date" length="20"
	 * @return String
	 */
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
	/**
	 * @hibernate.property column="weatherType" length="50"
	 * @return String
	 */
	public String getWeatherType() {
		return weatherType;
	}

	public void setWeatherType(String weatherType) {
		this.weatherType = weatherType;
	}
	/**
	 * @hibernate.property column="high"
	 * @return int
	 */
	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}
	/**
	 * @hibernate.property column="low"
	 * @return int
	 */
	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}
	 /**
	 * @hibernate.property column="wind" length="30"
	 * @return String
	 */
	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}
	 /**
	 * @hibernate.property column="icon" length="30"
	 * @return String
	 */
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * @hibernate.property column="isNight" 
	 * @return boolean
	 */
	public boolean isNight() {
		return isNight;
	}

	public void setNight(boolean isNight) {
		this.isNight = isNight;
	}
	/**
	 * @hibernate.property column="issuetime" length="10"
	 * @return String
	 */
	public String getIssuetime() {
		return issuetime;
	}

	public void setIssuetime(String issuetime) {
		this.issuetime = issuetime;
	}

	/**
	 * @hibernate.property column="VERSION"
	 * @return Long
	 */
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

}
