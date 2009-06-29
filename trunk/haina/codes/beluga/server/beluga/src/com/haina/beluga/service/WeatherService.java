package com.haina.beluga.service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.wxbug.api.ApiForecastData;
import net.wxbug.api.LiveWeatherData;
import net.wxbug.api.UnitType;
import net.wxbug.api.WeatherBugWebServicesLocator;
import net.wxbug.api.WeatherBugWebServicesSoap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.haina.beluga.core.service.BaseSerivce;
import com.haina.beluga.core.util.MfTime;
import com.haina.beluga.dao.IPhoneDistrictDao;
import com.haina.beluga.dao.IWeatherDao;
import com.haina.beluga.domain.Weather;
import com.haina.beluga.dto.WeatherDto;
import com.haina.beluga.webservice.Constant;
import com.haina.beluga.webservice.data.hessian.HessianRemoteReturning;
@Component
public class WeatherService extends BaseSerivce<IWeatherDao,Weather,String> implements IWeatherService,InitializingBean {
	@Autowired(required=true)
	private IPhoneDistrictDao phoneDistrictDao;
	private final static String ACode="A3432136345";
	 static Logger logger = Logger.getLogger(WeatherService.class.getName());
	 private static WeatherBugWebServicesSoap   weatherBugWebServicesSoap ;
		static{
			try{
			WeatherBugWebServicesLocator   weatherBugWebServicesLocator   =   new   WeatherBugWebServicesLocator();  
			logger.info("the Web Service:"+weatherBugWebServicesLocator.getWeatherBugWebServicesSoapAddress());  
	           weatherBugWebServicesSoap  =  weatherBugWebServicesLocator.getWeatherBugWebServicesSoap(); 
			}catch(Exception   e){  
				logger.error(e);  
		    }  
		}
	
	



	@Override
	public void loadWeatherDatasByApi() {
		long t1 = System.currentTimeMillis();
		String[] codes = phoneDistrictDao.getWeatherCityCodes();
		List<Weather> weatherList = new ArrayList<Weather>();
		logger.info("loadWeatherDatasByApiSize:"+codes.length);
		int i = 0;
		for(String cityCode:codes){
			try {
				ApiForecastData[] forecastDatas = weatherBugWebServicesSoap.getForecastByCityCode(cityCode, UnitType.Metric, ACode);
				for(ApiForecastData afd:forecastDatas){
					Weather weather = new Weather();
					weather.setDate(afd.getTitle());
					weather.setHigh(Integer.valueOf(afd.getTempHigh()));
					weather.setLow(Integer.valueOf(afd.getTempLow()));
					weather.setWeatherCityCode(cityCode);
					weather.setWeatherType(afd.getDescription());
					weather.setWind(getWindByStr(afd.getPrediction()));
					weather.setIcon(getIcon(afd.getImage()));
					weather.setNight(afd.isIsNight());
					weather.setIssuetime(MfTime.toNow());
					weather.setVersion(new Long(1));
					weatherList.add(weather);
//					getBaseDao().create(weather);
				}
//				break;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info(cityCode+":in "+i++);
		}
		getBaseDao().saveAll(weatherList);
		//所有更新后的天气加载到缓存
		findAll(true);
		long t2 = System.currentTimeMillis();
		logger.info("loadWeatherDatasByApi-complete:"+(t2-t1));
	}

	@Override
	public HessianRemoteReturning getLiveWeather(String cityCode) {
		HessianRemoteReturning hrr = new HessianRemoteReturning();
		try {
			LiveWeatherData livedata = weatherBugWebServicesSoap.getLiveWeatherByCityCode(cityCode, UnitType.Metric, ACode);
			WeatherDto dto = new WeatherDto();
			dto.setWeatherType(livedata.getCurrDesc());
//			dto.setLow(Integer.valueOf(livedata.getTemperatureLow()));
//			dto.setHigh(Integer.valueOf(livedata.getTemperatureHigh()));
			dto.setTemperature(livedata.getTemperature());
			dto.setIcon(livedata.getCurrIcon());
			hrr.setValue(dto);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			hrr.setStatusCode(Constant.NO_LIVEDATA);
		}
		return hrr;
	}

	@Override
	public HessianRemoteReturning get7Weatherdatas(String cityCode) {
		List<WeatherDto> list = new ArrayList<WeatherDto>();
		Iterator<Weather> iterator = getBaseDao().get7WeatherDatas(cityCode);
		while(iterator.hasNext()){
			Weather w = iterator.next();
			list.add(WeatherDto.valueof(w));
		}
		HessianRemoteReturning hrr = new HessianRemoteReturning(list);
		if(list.size()==0)
			hrr.setStatusCode(Constant.NO_7WEATHERDATA);
		return hrr;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		findAll(true);
		
	}

	public void setPhoneDistrictDao(IPhoneDistrictDao phoneDistrictDao) {
		this.phoneDistrictDao = phoneDistrictDao;
	}
	private String getWindByStr(String str){
		return str.split("Winds")[1].split(".  ")[0];
	}
	private String getIcon(String str){
		return str.split("/")[6];
	}
	
}