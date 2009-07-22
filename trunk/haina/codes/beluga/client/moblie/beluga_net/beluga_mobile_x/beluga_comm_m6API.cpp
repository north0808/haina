#include "myinc.h"
#include "HttpInternet.h"
#include "acNetEngine.h"
#include "WeatherDto.h"
#include "xconfig.h"
#include "glib/glib.h"
using namespace std;
#include "beluga_comm_m6API.h"

static CacNetEngine* acNetEngine=NULL;
beluga_comm_m6API::beluga_comm_m6API(void)
{
}

beluga_comm_m6API::~beluga_comm_m6API(void)
{
}
void beluga_comm_m6API::Test_InitNetWork(void)
{
	if (!acNetEngine)
	{
		acNetEngine= new CacNetEngine(KHostName,KHostNamePort);
	}
}
int beluga_comm_m6API::Test_getQQStatus(string qq)
{
	cout << "Test_getQQStatus" << endl;
	string qqStatus ;
	int ret = acNetEngine->getQQStatus(qq);
	return ret;
	switch(ret)
	{
	case 10000:
		cout << qq << "\tonline" << endl;
		qqStatus="online";
		break;
	case 10001:
		cout << qq << "\toffline" << endl;
		qqStatus="offline";
		break;
	default:
		cout << ">>> error:\t" << acNetEngine->getErrCode() << endl;
		qqStatus="error :"+acNetEngine->getErrCode();
		break;
	}
	return 0;
}

WeatherDto* beluga_comm_m6API::Test_getLiveWeather(string cityCode)
{
	cout << "Test_getLiveWeather" << endl;
	WeatherDto* weatherDto = acNetEngine->getLiveWeather(cityCode/*"W56985"*/);
	return weatherDto;
	cout << "Date:\t" << weatherDto->getDate() << endl;
	cout << "High:\t" << weatherDto->getHigh() << endl;
	cout << "Low:\t" << weatherDto->getLow() << endl;
	cout << "Icon:\t" << weatherDto->getIcon() << endl;
	cout << "Issuetime:\t" << weatherDto->getIssuetime() << endl;
	cout << "Temperature:\t" << weatherDto->getTemperature() << endl;
	cout << "CityCode:\t" << weatherDto->getWeatherCityCode() << endl;
	cout << "WeatherType:\t" << weatherDto->getWeatherType() << endl;
	cout << "Wind:\t" << weatherDto->getWind() << endl;
	delete weatherDto;
	weatherDto = NULL;
}

GPtrArray* beluga_comm_m6API::Test_get7WeatherDatas(string cityCode)
{
	cout << "Test_get7WeatherDatas" << endl;
	GPtrArray* weather7_list = acNetEngine->get7WeatherDatas(cityCode);
	if(weather7_list == NULL)
	{
		cout << ">>> error:\t" << acNetEngine->getErrCode() << endl;
		return NULL;
	}
	return weather7_list;
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
}

GPtrArray* beluga_comm_m6API::Test_getOrUpdatePD(string updatePD)
{
	cout << "Test_getOrUpdatePD" << endl;
	GPtrArray* pd_list = acNetEngine->getOrUpdatePD(updatePD/*"1"*/);
	if(pd_list == NULL)
	{
		cout << ">>> error:\t" << acNetEngine->getErrCode() << endl;
		return NULL;
	}
	return pd_list;

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
}
string beluga_comm_m6API::Test_register(string loginName, string password, string mobile)
{
	return acNetEngine->registerx(loginName,password,mobile);
	//if(!reg_passport.empty())
	//{
	//	cout << "reg_passport:\t" << reg_passport << endl;
	//}
	//else
	//{
	//	cout << "register failed!" << endl;
	//	cout << ">>> error:\t" << acNetEngine->getErrCode() << endl; 
	//}
}
string beluga_comm_m6API::Test_login(string loginName, string password)
{
	return acNetEngine->login(loginName,password);
}
bool beluga_comm_m6API::Test_logout(string passport)
{
	return acNetEngine->logoutByPsssport(passport);
}
int beluga_comm_m6API::getErrCode(void)
{
	return acNetEngine->getErrCode();
}
void beluga_comm_m6API::Test_register_login_logout(string loginName, string password, string mobile)
{
	//test register
	string reg_passport = acNetEngine->registerx(loginName,password,mobile);
	if(!reg_passport.empty())
	{
		cout << "reg_passport:\t" << reg_passport << endl;
	}
	else
	{
		cout << "register failed!" << endl;
		cout << ">>> error:\t" << acNetEngine->getErrCode() << endl; 
	}

	//test login
	string login_passport = acNetEngine->login(loginName,password);
	if(!login_passport.empty())
	{
		cout << "login_passport:\t" << login_passport << endl;
	}
	else
	{
		cout << "login failed!" << endl;
		cout << ">>> error:\t" << acNetEngine->getErrCode() << endl;
	}

	//test logout by passport
	bool logout_ret = acNetEngine->logoutByPsssport(login_passport);
	if(logout_ret)
	{
		cout << "logout succ" << endl;
	}
	else
	{
		cout << "logout failed!" << endl;
		cout << ">>> error:\t" << acNetEngine->getErrCode() << endl;
	}
}

