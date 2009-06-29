package com.haina.beluga.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.haina.beluga.core.service.BaseSerivce;
import com.haina.beluga.dao.IPhoneDistrictDao;
import com.haina.beluga.domain.PhoneDistrict;
import com.haina.beluga.dto.PhoneDistrictDto;
import com.haina.beluga.webservice.Constant;
import com.haina.beluga.webservice.data.hessian.HessianRemoteReturning;
@Component
public class PhoneDistrictService extends BaseSerivce<IPhoneDistrictDao,PhoneDistrict,String> implements IPhoneDistrictService {

	@Override
	public HessianRemoteReturning getOrUpdatePhoneDistricts(int updateFlg) {
		List<PhoneDistrictDto> list = new ArrayList<PhoneDistrictDto>();
		Iterator<PhoneDistrict> iterator =getBaseDao().getPhoneDistrictsByUpdateFlg(updateFlg);
		while(iterator.hasNext()){
			PhoneDistrict w = iterator.next();
			list.add(PhoneDistrictDto.valueof(w));
		}
		HessianRemoteReturning hrr = new HessianRemoteReturning(list);
			hrr.setValue(list);
		return hrr;
		
	}

}
