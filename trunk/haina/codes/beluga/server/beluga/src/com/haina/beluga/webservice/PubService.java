package com.haina.beluga.webservice;

import com.haina.beluga.service.IIMService;
import com.haina.beluga.service.IMService;
import com.haina.beluga.service.IPhoneDistrictService;
import com.haina.beluga.service.IWeatherService;
import com.haina.beluga.service.PhoneDistrictService;
import com.haina.beluga.service.WeatherService;
import com.haina.beluga.webservice.data.hessian.HessianRemoteReturning;



public class PubService  implements IPubService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1083499059060975666L;
//	@Autowired(required=true)
	private IIMService iMservice = new IMService();
//	@Autowired(required=true)
	private IWeatherService weatherService = new WeatherService();
//	@Autowired(required=true)
	private IPhoneDistrictService phoneDistrictService = new PhoneDistrictService();
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
	
	

}
