package com.haina.beluga.service;

import java.rmi.RemoteException;

import net.wxbug.api.ApiForecastData;
import net.wxbug.api.UnitType;
import net.wxbug.api.WeatherBugWebServicesLocator;
import net.wxbug.api.WeatherBugWebServicesSoap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.haina.beluga.core.service.BaseSerivce;
import com.haina.beluga.dao.IPhoneDistrictDao;
import com.haina.beluga.dao.IWeatherDao;
import com.haina.beluga.domain.Weather;
import com.haina.beluga.webservice.OUSkeleton;
@Component
public class WeatherService extends BaseSerivce<IWeatherDao,Weather,String> implements IWeatherService {
	@Autowired(required=true)
	private IPhoneDistrictDao phoneDistrictDao;
	private final static String ACode="A3432136345";
	 static Logger logger = Logger.getLogger(OUSkeleton.class.getName());
		public static WeatherBugWebServicesSoap   weatherBugWebServicesSoap ;
		static{
			try{
			WeatherBugWebServicesLocator   weatherBugWebServicesLocator   =   new   WeatherBugWebServicesLocator();  
			logger.info("the Web Service:"+weatherBugWebServicesLocator.getWeatherBugWebServicesSoapAddress());  
	           weatherBugWebServicesSoap  =  weatherBugWebServicesLocator.getWeatherBugWebServicesSoap(); 
			}catch(Exception   e){  
		        System.out.println(e);  
		    }  
		}
	
	



	@Override
	public void loadWeatherDatasByApi() {
		String[] codes = phoneDistrictDao.getWeatherCityCodes();
		logger.info("loadWeatherDatasByApiSize:"+codes.length);
		//先清空，后插入
		int i = 0;
		getBaseDao().delAll();
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
					weather.setWind(afd.getPrediction());
					getBaseDao().create(weather);
				}
//				break;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info(cityCode+":in "+i++);
		}
		logger.info("loadWeatherDatasByApi-complete");
	}

	public void setPhoneDistrictDao(IPhoneDistrictDao phoneDistrictDao) {
		this.phoneDistrictDao = phoneDistrictDao;
	}

	
}
