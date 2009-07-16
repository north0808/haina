#include "myinc.h"
#include "HttpInternet.h"
#include "acNetEngine.h"
#include "xconfig.h"

#include "WeatherDto.h"

using namespace std;

int main(int argc, TCHAR* argv[])
{
	/************************************************************************/
// 	string pdata = "UserName=abcdefg&UserPassword=123456";
// 	CHttpInternet httpInternet;
// 	if(httpInternet.SetHostName(_T("www.sf.org.cn")))
// 	{
// 		string postRet = httpInternet.SyncPostData(_T("/User/User_ChkLogin.asp"),pdata);
// 		if(!postRet.empty())
// 		{
// 			cout << postRet << endl << endl;
// 		}
// 		else
// 		{
// 			cout << "<<< Post data failed!" << endl;
// 		}
// 	}
// 	else
// 	{
// 		cout << "<<< CHttpInternet SetHostName failed!" << endl;
// 	}
	/************************************************************************/

	


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

	




	return 0;
}