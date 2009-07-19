#include "myinc.h"
#include "HttpInternet.h"
#include "acNetEngine.h"
#include "xconfig.h"
#include "glib/glib.h"

#include "WeatherDto.h"

using namespace std;

int main(int argc, TCHAR* argv[])
{
	/************************************************************************/
	/*     获取QQ在线状态示例                  
	CacNetEngine* acNetEngine = new CacNetEngine(KHostName,KHostNamePort);

	string qq = "95467703";
	int ret = acNetEngine->getQQStatus(qq);
	switch(ret)
	{
	case 10000:
		cout << qq << "\tonline" << endl;
		break;
	case 10001:
		cout << qq << "\toffline" << endl;
		break;
	default:
		cout << "error" << endl;
	}

	delete acNetEngine;
	*/
	/************************************************************************/
		

	/************************************************************************/
	/*		获取当天天气信息示例                    
	CacNetEngine* acNetEngine = new CacNetEngine(KHostName,KHostNamePort);

	WeatherDto weatherDto = acNetEngine->getLiveWeather("W56985");
	cout << "Date:\t" << weatherDto.getDate() << endl;
	cout << "High:\t" << weatherDto.getHigh() << endl;
	cout << "Low:\t" << weatherDto.getLow() << endl;
	cout << "Icon:\t" << weatherDto.getIcon() << endl;
	cout << "Issuetime:\t" << weatherDto.getIssuetime() << endl;
	cout << "Temperature:\t" << weatherDto.getTemperature() << endl;
	cout << "CityCode:\t" << weatherDto.getWeatherCityCode() << endl;
	cout << "WeatherType:\t" << weatherDto.getWeatherType() << endl;
	cout << "Wind:\t" << weatherDto.getWind() << endl;

	delete acNetEngine;
	*/
	/************************************************************************/

	
	/************************************************************************/
	/*		获取7天天气预报数据信息示例      */            
	CacNetEngine* acNetEngine = new CacNetEngine(KHostName,KHostNamePort);

	GPtrArray* weather7_list = acNetEngine->get7WeatherDatas("W56985");
	for(int i = 0; i < weather7_list->len; i++)
	{
		WeatherDto* pWeather = (WeatherDto*)g_ptr_array_index(weather7_list,i);
		cout << "========== " << i << " ==========" << endl;
		cout << "Date:\t" << pWeather->getDate() << endl;
		cout << "High:\t" << pWeather->getHigh() << endl;
		cout << "Low:\t" << pWeather->getLow() << endl;
		cout << "Icon:\t" << pWeather->getIcon() << endl;
		cout << "Issuetime:\t" << pWeather->getIssuetime() << endl;
		cout << "Temperature:\t" << pWeather->getTemperature() << endl;
		cout << "CityCode:\t" << pWeather->getWeatherCityCode() << endl;
		cout << "WeatherType:\t" << pWeather->getWeatherType() << endl;
		cout << "Wind:\t" << pWeather->getWind() << endl;
		cout << endl;
	}
	freeGPtrArray(weather7_list);
	delete acNetEngine;
	  
	/************************************************************************/


	/************************************************************************/
	/*		获取号码归属地数据示例                  
	CacNetEngine* acNetEngine = new CacNetEngine(KHostName,KHostNamePort);

	GPtrArray* pd_list = acNetEngine->getOrUpdatePD("1");
	for(int i = 0; i < pd_list->len; i++)
	{
	 	PhoneDistrictDto* phoneDistrict = (PhoneDistrictDto*)g_ptr_array_index(pd_list,i);
	 	cout << "========== " << i << " ==========" << endl;
	 	cout << "DistrictCity:\t" << phoneDistrict->getDistrictCity() << endl;
		cout << "DistrictNumber:\t" << phoneDistrict->getDistrictNumber() << endl;
		cout << "DistrictProvince:\t" << phoneDistrict->getDistrictProvince() << endl;
		cout << "WeatherCityCode:\t" << phoneDistrict->getWeatherCityCode() << endl;
// 		cout << "Range:\t" << phoneDistrict->getRange() << endl;
		cout << "FeeType:\t" << phoneDistrict->getFeeType() << endl;
		cout << "UpdateFlg:\t" << phoneDistrict->getUpdateFlg() << endl;
	 	cout << endl;
	}
	freeGPtrArray(pd_list);
	delete acNetEngine;
	*/ 
	/************************************************************************/


	return 0;
}