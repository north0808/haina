package com.haina.beluga.dao;

import java.util.Iterator;

import com.haina.beluga.domain.PhoneDistrict;
import com.haina.core.dao.IBaseDao;

public interface IPhoneDistrictDao extends IBaseDao<PhoneDistrict, String> {
	
	public String[] getWeatherCityCodes();
	public Iterator<PhoneDistrict> getPhoneDistrictsByUpdateFlg(int updateFlg);

}
