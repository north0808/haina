package com.haina.beluga.service;

import com.haina.beluga.core.service.IBaseSerivce;
import com.haina.beluga.dao.IWeatherDao;
import com.haina.beluga.domain.Weather;

public interface IWeatherService extends IBaseSerivce<IWeatherDao,Weather,String> {

	public void loadWeatherDatasByApi();
}
