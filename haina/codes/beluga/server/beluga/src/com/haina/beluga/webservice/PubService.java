package com.haina.beluga.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.haina.beluga.dto.WeatherDto;
import com.haina.beluga.service.IMService;
import com.haina.beluga.service.IPhoneDistrictService;
import com.haina.beluga.service.IWeatherService;




public class PubService  implements IPubService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1083499059060975666L;
	@Autowired(required=true)
	private IMService iMservice;
	@Autowired(required=true)
	private IWeatherService weatherService;
	@Autowired(required=true)
	private IPhoneDistrictService phoneDistrictService;
	@Override
	public List<WeatherDto> get7Weatherdatas(String cityCode) {
		return weatherService.get7Weatherdatas(cityCode);
	}
	@Override
	public WeatherDto getLiveWeather(String cityCode) {
		return weatherService.getLiveWeather(cityCode);
	}
	@Override
	public int getQQStatus(int qqCode) {
		return iMservice.getQQStatus(qqCode);
	}
	
	

}
