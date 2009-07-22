#pragma once

#ifdef BELUGA_DLL_BUILD
	#define BELUGA_API __declspec(dllexport)
#else
	#define BELUGA_API __declspec(dllimport)
#endif


class BELUGA_API beluga_comm_m6API
{
public:
	beluga_comm_m6API(void);
	~beluga_comm_m6API(void);
	void Test_InitNetWork(void);
	int Test_getQQStatus(string qq);
	WeatherDto* Test_getLiveWeather(string cityCode);
	GPtrArray* Test_get7WeatherDatas(string cityCode);
	GPtrArray* Test_getOrUpdatePD(string updatePD);
	void Test_register_login_logout(string loginName, string password, string mobile);
};
