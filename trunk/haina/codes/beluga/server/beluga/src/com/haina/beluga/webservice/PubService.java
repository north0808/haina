package com.haina.beluga.webservice;

import org.springframework.beans.factory.annotation.Autowired;

import com.haina.beluga.service.IMService;
import com.haina.beluga.service.IPhoneDistrictService;
import com.haina.beluga.service.IWeatherService;




public class PubService  implements IPubService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1083499059060975666L;
	@Autowired(required=true)
	private IMService iMservice;
	@Autowired(required=true)
	private IWeatherService weatherService;
	@Autowired(required=true)
	private IPhoneDistrictService phoneDistrictService;
	

}
