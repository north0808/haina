package com.haina.beluga.service;

import java.util.List;

import com.haina.beluga.core.service.IBaseSerivce;
import com.haina.beluga.dao.IWeatherDao;
import com.haina.beluga.domain.Weather;
import com.haina.beluga.dto.WeatherDto;

public interface IWeatherService extends IBaseSerivce<IWeatherDao,Weather,String> {

	public void loadWeatherDatasByApi();
	public WeatherDto getLiveWeather(String cityCode);
	public List<WeatherDto> get7Weatherdatas(String cityCode);
}
