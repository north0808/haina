package com.haina.beluga.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.haina.beluga.core.dao.BaseDao;
import com.haina.beluga.domain.PhoneDistrict;
@Component
public class PhoneDistrictDao extends BaseDao<PhoneDistrict,String> implements IPhoneDistrictDao {

	@Override
	public String[] getWeatherCityCodes() {
		 Session session = getSession();
		  Query query = session.createQuery("select distinct p.weatherCityCode from PhoneDistrict p");  
//         query.setFirstResult(startIndex).setMaxResults(rowCount);   
         query.setCacheable(true);
         query.setCacheRegion("com.haina.beluga.domain.PhoneDistrict");
         return (String[]) query.list().toArray(new String[]{}); 
	}


}
