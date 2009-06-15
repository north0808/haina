package com.haina.beluga.dao;

import com.haina.beluga.core.dao.IBaseDao;
import com.haina.beluga.domain.PhoneDistrict;

public interface IPhoneDistrictDao extends IBaseDao<PhoneDistrict, String> {
	
	public String[] getWeatherCityCodes();

}
