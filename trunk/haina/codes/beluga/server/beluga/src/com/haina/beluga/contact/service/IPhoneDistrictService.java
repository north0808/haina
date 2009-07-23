package com.haina.beluga.contact.service;

import com.haina.beluga.contact.dao.IPhoneDistrictDao;
import com.haina.beluga.contact.domain.PhoneDistrict;
import com.haina.beluga.webservice.data.hessian.HessianRemoteReturning;
import com.haina.core.service.IBaseSerivce;

public interface IPhoneDistrictService extends IBaseSerivce<IPhoneDistrictDao,PhoneDistrict,String> {

	public HessianRemoteReturning getOrUpdatePhoneDistricts(int updateFlg);
}
