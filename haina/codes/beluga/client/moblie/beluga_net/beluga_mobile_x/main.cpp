#include "myinc.h"
#include "HttpInternet.h"
#include "acNetEngine.h"
#include "WeatherDto.h"
#include "xconfig.h"
#include "glib/glib.h"

using namespace std;


void Test_getQQStatus(CacNetEngine* acNetEngine);
void Test_getLiveWeather(CacNetEngine* acNetEngine);
void Test_get7WeatherDatas(CacNetEngine* acNetEngine);
void Test_getOrUpdatePD(CacNetEngine* acNetEngine);
void Test_register_login_logout(CacNetEngine* acNetEngine);


int main(int argc, TCHAR* argv[])
{
	/************************************************************************/ 
	CacNetEngine* acNetEngine = new CacNetEngine(KHostName,KHostNamePort);

	Test_getQQStatus(acNetEngine);				//获取QQ在线状态示例
//	Test_getLiveWeather(acNetEngine);			//获取当天天气信息示例
//	Test_get7WeatherDatas(acNetEngine);			//获取7天天气预报数据信息示例
//	Test_getOrUpdatePD(acNetEngine);			//获取号码归属地数据示例
//	Test_register_login_logout(acNetEngine);	//注册登录退出测试示例

	delete acNetEngine;
	/************************************************************************/
		

	
	return 0;
}

void Test_getQQStatus(CacNetEngine* acNetEngine)
{
	cout << "Test_getQQStatus" << endl;
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
		cout << ">>> error:\t" << acNetEngine->getErrCode() << endl;
	}
}

void Test_getLiveWeather(CacNetEngine* acNetEngine)
{
	cout << "Test_getLiveWeather" << endl;
	WeatherDto* weatherDto = acNetEngine->getLiveWeather("W56985");
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

void Test_get7WeatherDatas(CacNetEngine* acNetEngine)
{
	cout << "Test_get7WeatherDatas" << endl;
	GPtrArray* weather7_list = acNetEngine->get7WeatherDatas("W56985");
	if(weather7_list == NULL)
	{
		cout << ">>> error:\t" << acNetEngine->getErrCode() << endl;
		return;
	}

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

void Test_getOrUpdatePD(CacNetEngine* acNetEngine)
{
	cout << "Test_getOrUpdatePD" << endl;
	GPtrArray* pd_list = acNetEngine->getOrUpdatePD("1");
	if(pd_list == NULL)
	{
		cout << ">>> error:\t" << acNetEngine->getErrCode() << endl;
		return;
	}

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

void Test_register_login_logout(CacNetEngine* acNetEngine)
{
	//test register
	string reg_passport = acNetEngine->registerx("abcdefg","123456","13888888888");
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
	string login_passport = acNetEngine->login("abcdefg","123456");
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


/************************************************************************/
/*
Json::Value root;
Json::StyledWriter writer;
root["Date"] = weatherDto.getDate();
root["High"] = weatherDto.getHigh();
root["Low"] = weatherDto.getLow();
root["Icon"] = weatherDto.getIcon();
string outputJson = writer.write(root);
printf("JSON: %s\n", outputJson.c_str());
*/
/************************************************************************/