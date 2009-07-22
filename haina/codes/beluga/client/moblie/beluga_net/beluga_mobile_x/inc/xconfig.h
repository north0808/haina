#ifndef		__XCONFIG__H__
#define		__XCONFIG__H__

//----------------------- host info ---------------------------------------------------
#define		KHostNamePort			8079
#define		KHostName				_T("yanzisoft.oicp.net")					//服务器ip或域名
//#define		KHostName				_T("192.168.3.105")					//服务器ip或域名
//#define		KHostName				_T("58.24.49.13")					//服务器ip或域名
//#define		KHostName				_T("192.168.0.1")					//服务器ip或域名


//----------------------- public services ---------------------------------------------------
#define		KTestHessianUrl			_T("/myapp/TestHessianServlet")
#define		KTestCN					_T("/beluga/pub?call=testCN")				//中文测试地址
#define		KGetQQStatusUrl			_T("/beluga/pub?call=getQQStatus")			//获取QQ在线状态
#define		KGetOrUpdatePDUrl		_T("/beluga/pub?call=getOrUpdatePD")		//获取归属地数据
#define		KGetLiveWeatherUrl		_T("/beluga/pub?call=getLiveWeather")		//获取当天天气情况
#define		KGet7WeatherUrl			_T("/beluga/pub?call=get7Weatherdatas")		//获取七天的天气数据


//----------------------- private services ---------------------------------------------------
#define		KRegisterUrl			_T("/beluga/pri?call=register")	
#define		KLoginUrl				_T("/beluga/pri?call=login")	
#define		KLogoutByPsssportUrl	_T("/beluga/pri?call=logoutByPsssport")	



//----------------------- other ---------------------------------------------------


#endif		/* __XCONFIG__H__ */
