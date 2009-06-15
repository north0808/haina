package com.haina.beluga.webservice;

import java.io.Serializable;
import java.util.List;

import com.haina.beluga.dto.WeatherDto;
/**
 * 公共服务api.
 * @author Administrator
 *
 */
public interface IPubService extends Serializable {
	/**
	 * weather
	 */
	public WeatherDto getLiveWeather(String cityCode);
	public List<WeatherDto> get7Weatherdatas(String cityCode);
	/**
	 * IM
	 */
	public int getQQStatus(int qqCode);
	

}
