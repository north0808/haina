package com.haina.beluga.service;

import com.haina.beluga.dao.IWeatherDao;
import com.haina.beluga.domain.Weather;
import com.haina.beluga.webservice.data.hessian.HessianRemoteReturning;
import com.haina.core.service.IBaseSerivce;

public interface IWeatherService extends IBaseSerivce<IWeatherDao,Weather,String> {

	public void loadWeatherDatasByApi();
	public HessianRemoteReturning getLiveWeather(String cityCode);
	public HessianRemoteReturning get7Weatherdatas(String cityCode);
	void loadLiveDatasByApi();
}
