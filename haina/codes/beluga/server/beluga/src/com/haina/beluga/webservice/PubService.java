package com.haina.beluga.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.haina.beluga.service.IIMService;
import com.haina.beluga.service.IPhoneDistrictService;
import com.haina.beluga.service.IWeatherService;
import com.haina.beluga.webservice.data.hessian.HessianRemoteReturning;


@Component(value="pubService")
public class PubService  implements IPubService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1083499059060975666L;
	@Autowired(required=true)
	private IIMService iMservice;
	@Autowired(required=true)
	private IWeatherService weatherService;
	@Autowired(required=true)
	private IPhoneDistrictService phoneDistrictService;
	@Override
	public HessianRemoteReturning get7Weatherdatas(String cityCode) {
		return weatherService.get7Weatherdatas(cityCode);
	}
	@Override
	public HessianRemoteReturning getLiveWeather(String cityCode) {
		return weatherService.getLiveWeather(cityCode);
	}
	@Override
	public HessianRemoteReturning getQQStatus(int qqCode) {
		return iMservice.getQQStatus(qqCode);
	}
	@Override
	public HessianRemoteReturning getOrUpdatePD(int updateFlg) {
		return phoneDistrictService.getOrUpdatePhoneDistricts(updateFlg);
	}
	

}
