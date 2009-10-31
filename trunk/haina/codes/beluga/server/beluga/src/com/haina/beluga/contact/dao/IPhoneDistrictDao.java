package com.haina.beluga.contact.dao;

import java.util.Iterator;

import com.haina.beluga.contact.domain.PhoneDistrict;
import com.haina.core.dao.IBaseDao;

public interface IPhoneDistrictDao extends IBaseDao<PhoneDistrict, String> {
	
	public String[] getWeatherCityCodes();
	public Iterator<PhoneDistrict> getPhoneDistrictsByUpdateFlg(int updateFlg);

}