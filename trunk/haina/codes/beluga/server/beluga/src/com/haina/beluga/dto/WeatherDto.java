package com.haina.beluga.dto;

import com.haina.beluga.core.dto.IDto;
import com.haina.beluga.core.util.BeanUtil;
import com.haina.beluga.domain.Weather;

public class WeatherDto implements IDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4673967747869811297L;
	
	private String date;/*天气日期*/ 
	private String weatherCityCode; 
	private String weatherType; 
	private int high; 
	private int low;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWeatherCityCode() {
		return weatherCityCode;
	}
	public void setWeatherCityCode(String weatherCityCode) {
		this.weatherCityCode = weatherCityCode;
	}
	public String getWeatherType() {
		return weatherType;
	}
	public void setWeatherType(String weatherType) {
		this.weatherType = weatherType;
	}
	public int getHigh() {
		return high;
	}
	public void setHigh(int high) {
		this.high = high;
	}
	public int getLow() {
		return low;
	}
	public void setLow(int low) {
		this.low = low;
	} 
	
	public static WeatherDto valueof(Weather w){
		WeatherDto dto = new WeatherDto();
		BeanUtil.copyPropertie(w, dto);
		return dto;
	}
	

}
