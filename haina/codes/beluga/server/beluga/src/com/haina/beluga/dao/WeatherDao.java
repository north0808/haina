package com.haina.beluga.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.haina.beluga.core.dao.BaseDao;
import com.haina.beluga.domain.Weather;
@Component
public class WeatherDao extends BaseDao<Weather,String> implements IWeatherDao {

	@Override
	public void delAll() {
	     List<Weather> weathers =  getModels(true);
	     for(Weather w:weathers){
	    	 delete(w);
	     }
	}

	@Override
	public Iterator<Weather> get7WeatherDatas(String cityCode) {
		Session session = getSession();
		Query query = session.createQuery("from Weather where weatherCityCode =:cityCode");
		query.setString("cityCode",cityCode);
// unuse
//		query.setCacheable(true);
//		query.setCacheRegion("com.haina.beluga.domain.Weather");
		return query.iterate();
	}

	
}
