package com.haina.beluga.service;

import org.springframework.stereotype.Component;

import com.haina.beluga.core.service.BaseSerivce;
import com.haina.beluga.dao.IPhoneDistrictDao;
import com.haina.beluga.domain.PhoneDistrict;
@Component
public class PhoneDistrictService extends BaseSerivce<IPhoneDistrictDao,PhoneDistrict,String> implements IPhoneDistrictService {

}
