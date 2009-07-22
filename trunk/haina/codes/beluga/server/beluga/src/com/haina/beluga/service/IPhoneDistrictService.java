package com.haina.beluga.service;

import com.haina.beluga.dao.IPhoneDistrictDao;
import com.haina.beluga.domain.PhoneDistrict;
import com.haina.beluga.webservice.data.hessian.HessianRemoteReturning;
import com.haina.core.service.IBaseSerivce;

public interface IPhoneDistrictService extends IBaseSerivce<IPhoneDistrictDao,PhoneDistrict,String> {

	public HessianRemoteReturning getOrUpdatePhoneDistricts(int updateFlg);
}
