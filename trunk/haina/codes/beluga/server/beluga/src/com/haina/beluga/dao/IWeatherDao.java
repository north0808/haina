package com.haina.beluga.dao;

import java.util.Iterator;

import com.haina.beluga.core.dao.IBaseDao;
import com.haina.beluga.domain.Weather;

public interface IWeatherDao extends IBaseDao<Weather, String>{
	/**
	 * 清空过期天气。
	 */
	public void delAll();
	public Iterator<Weather> get7WeatherDatas(String cityCode);

}
