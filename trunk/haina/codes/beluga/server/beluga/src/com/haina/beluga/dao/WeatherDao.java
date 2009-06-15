package com.haina.beluga.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.haina.beluga.core.dao.BaseDao;
import com.haina.beluga.domain.Weather;
@Component
public class WeatherDao extends BaseDao<Weather,String> implements IWeatherDao {

	@Override
	public void delAll() {
//		 Session session = getSession();
	     List<Weather> weathers =  getModels(true);
	     for(Weather w:weathers){
	    	 delete(w);
	     }
//	     session.flush();
	      
	}

	
}
