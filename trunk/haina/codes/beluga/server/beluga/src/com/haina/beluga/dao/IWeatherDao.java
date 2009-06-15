package com.haina.beluga.dao;

import com.haina.beluga.core.dao.IBaseDao;
import com.haina.beluga.domain.Weather;

public interface IWeatherDao extends IBaseDao<Weather, String>{
	/**
	 * 请客过期天气。
	 */
	public void delAll();

}
