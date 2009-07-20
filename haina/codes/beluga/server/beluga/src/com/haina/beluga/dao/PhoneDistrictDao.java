package com.haina.beluga.dao;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.haina.beluga.core.dao.BaseDao;
import com.haina.beluga.domain.PhoneDistrict;
@Component
@SuppressWarnings("unchecked")
public class PhoneDistrictDao extends BaseDao<PhoneDistrict,String> implements IPhoneDistrictDao {

	@Override
	public String[] getWeatherCityCodes() {
		 String hql ="select distinct p.weatherCityCode from PhoneDistrict p";  
		 List<String> list = (List<String>) getResultByHQLAndParam(hql);
         return list.toArray(new String[]{}); 
	}

	@Override
	public Iterator<PhoneDistrict> getPhoneDistrictsByUpdateFlg(int updateFlg) {
		String hql = "from PhoneDistrict where updateFlg > ?";
		return (Iterator<PhoneDistrict>) getIteratorByHQLAndParam(hql,updateFlg);
	}


}
