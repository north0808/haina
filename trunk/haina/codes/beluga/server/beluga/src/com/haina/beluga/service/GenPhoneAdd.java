package com.haina.beluga.service;
import java.rmi.RemoteException;
import java.util.StringTokenizer;

import javax.xml.rpc.holders.BooleanHolder;
import javax.xml.rpc.holders.StringHolder;

import net.wxbug.api.ApiLocationData;
import net.wxbug.api.WeatherBugWebServicesLocator;
import net.wxbug.api.WeatherBugWebServicesSoap;

import com.haina.beluga.domain.PhoneDistrict;
import com.showji.api.Locating.MobileLocator;
import com.showji.api.Locating.MobileSoap;






public class GenPhoneAdd {
	public final static String ACode="A3432136345";
	public static WeatherBugWebServicesSoap   weatherBugWebServicesSoap ;
	public static MobileSoap moblieSoap;
	public static DefaultCnToSpell spell = new DefaultCnToSpell();
	static{
		try{
		WeatherBugWebServicesLocator   weatherBugWebServicesLocator   =   new   WeatherBugWebServicesLocator();  
        System.out.println("the Web Service:"+weatherBugWebServicesLocator.getWeatherBugWebServicesSoapAddress());  
           weatherBugWebServicesSoap  =  weatherBugWebServicesLocator.getWeatherBugWebServicesSoap(); 
           MobileLocator mobileLocator = new MobileLocator();
           System.out.println("the Web Service:"+mobileLocator.getMobileSoapAddress());  
           moblieSoap = mobileLocator.getMobileSoap();
		}catch(Exception   e){  
	        System.out.println(e);  
	        }  
	}
	public static String getWeather_city_code(String searchString){
		
		
        ApiLocationData[] apiLocationData = null;
		try {
			apiLocationData = weatherBugWebServicesSoap.getLocationList(searchString, ACode);
		} catch (RemoteException e) {
			e.printStackTrace();
		} 
        if(apiLocationData==null ||apiLocationData.length ==0){
        	System.out.println("error:"+searchString+":no Weather_city_code!");
        	return null;
        }
       for(int i = 0 ; i < apiLocationData.length; i ++ ){
    	   if(apiLocationData[i].getCity().endsWith(searchString)&&apiLocationData[i].getCountry().equals("China")){
    		   return apiLocationData[i].getCityCode();
    	   }
       }
       System.out.println("error:"+searchString+":没有城市天气代码!");
       return null;
         
	}
	public static PhoneAdrModel getStrByNet(Integer phoneCode){
		 try{
			 StringHolder province =  new StringHolder();
			 StringHolder city =  new StringHolder();
			 StringHolder areaCode =  new StringHolder();
			 StringHolder postCode =  new StringHolder();
			 StringHolder corp =  new StringHolder();
			 StringHolder card =  new StringHolder();
			 moblieSoap.query(String.valueOf(phoneCode), new BooleanHolder(true), province, city, areaCode, postCode, corp, card);
			 PhoneAdrModel pm = new PhoneAdrModel();
			 pm.setProvince(province.value);
			 pm.setCity(city.value);
			 pm.setAreaCode(areaCode.value);
			 pm.setCorp(corp.value);
			 pm.setCard(card.value);
			 return pm;
		 }catch (Exception e) {
		}
			  return null;
	}
	
	public static String getBigUp(String strSource){
	    String  pe;  
	    char ch[];       
	   StringTokenizer st=new StringTokenizer(strSource); 
	    while(st.hasMoreTokens())
	       {
	        pe=st.nextToken();    
	        ch= pe.toCharArray();
	        if(ch[0]>='a'&&ch[0]<='z')
	        {ch[0]=(char)(ch[0]-32);
	        } 
	      return new String(ch);
	       }
	    return null;
	}
	public static void main(String[] arg) throws Exception {
		String temp_AreaCode = null;
		boolean isSave= false;
		int temp_start = 0;
		for(int i=1300000; i < 1310000; i ++ ){
			PhoneAdrModel pm = getStrByNet(i);
			
			if(pm==null||pm.getCity().equals("")){
				System.out.println("error:"+i+"没找到记录");
				if(!isSave){
					PhoneDistrict pd = new PhoneDistrict();
					String priv =pm.getProvince();
					String city_py = getBigUp(spell.cnToSpell(pm.getCity()));
					String city = pm.getCity();
					String fee = pm.getCorp()+pm.getCard();
					String weather_city_code = getWeather_city_code(city_py);
					pd.setDistrictCity(city);
					pd.setDistrictNumber(pm.getAreaCode());
					pd.setDistrictProvince(priv);
					pd.setFeeType(fee);
					pd.setRangeStart(new Long(temp_start));
					pd.setRangeEnd(new Long(i-1));
					pd.setPingyinCity(city_py);
					pd.setUpdateFlg(1);
					pd.setWeatherCityCode(weather_city_code);
					//save
				   }
					isSave=false;
					temp_start = 0;
					temp_AreaCode  = null;
					continue;
				
			}
			if(temp_AreaCode == null){
				temp_AreaCode  = pm.getAreaCode();
				temp_start = i;
				
			}else if(!temp_AreaCode.equals(pm.getAreaCode())){
				System.out.println(i);
				PhoneDistrict pd = new PhoneDistrict();
				String priv =pm.getProvince();
				String city_py = getBigUp(spell.cnToSpell(pm.getCity()));
				String city = pm.getCity();
				String fee = pm.getCorp()+pm.getCard();
				String weather_city_code = getWeather_city_code(city_py);
				pd.setDistrictCity(city);
				pd.setDistrictNumber(pm.getAreaCode());
				pd.setDistrictProvince(priv);
				pd.setFeeType(fee);
				pd.setRangeStart(new Long(temp_start));
				pd.setRangeEnd(new Long(i-1));
				pd.setPingyinCity(city_py);
				pd.setUpdateFlg(1);
				pd.setWeatherCityCode(weather_city_code);
				//save
				isSave=true;
				temp_start = i;
				temp_AreaCode  = pm.getAreaCode();
			}
			
			
			
		}
		//System.out.println(getWeather_city_code("Sihong"));
		//System.out.println(getStrByNet(138513669));
		
	}
	
}
