#include "acNetEngine.h"
#include "ChineseCodeLib.h"

#include "xconfig.h"

using namespace hessian;



CacNetEngine::CacNetEngine()
{

}

CacNetEngine::CacNetEngine(LPCTSTR aHostName,int aHttp_Port)
{
	setNetHost(aHostName,aHttp_Port);
}

CacNetEngine::~CacNetEngine()
{

}

/************************************************************************/
// 函数：setNetHost
// 功能：设置服务器主机域名或IP地址
// 参数：aHostName：服务器域名或IP
// 参数：aHttp_Port：访问端口，默认为80端口
// 返回：无返回
/************************************************************************/
void CacNetEngine::setNetHost(LPCTSTR aHostName,int aHttp_Port)
{
	iHttpNet.SetHostName(aHostName,aHttp_Port);
}


/************************************************************************/
// 函数：getQQStatus
// 功能：获取QQ在线状态
// 参数：QQ号码
// 返回：在线(10000) 离线(10001) 出错(-1)
/************************************************************************/
int CacNetEngine::getQQStatus(string aQQId)
{
	iSendHes.clear();
 	ihes_output.write_string(iSendHes,aQQId);
 	string retData = iHttpNet.SyncPostData(KGetQQStatusUrl,iSendHes);

	Json::Value jsonValue;
	string retstring = getHessianString(retData);
	HessianRemoteReturning hes_return = parse_json(retstring,jsonValue);

	if(hes_return.getStatusCode() != 0)
		return -1;

	int online = hes_return.getValue().asInt();
	return online;
}


/************************************************************************/
// 函数：getLiveWeather
// 功能：获取当天天气的数据
// 参数：归属地表中的城市ID
// 返回：天气数据（WeatherDto）
/************************************************************************/
WeatherDto CacNetEngine::getLiveWeather(string aCityCode)
{
	WeatherDto weatherDto;

	iSendHes.clear();
	ihes_output.write_string(iSendHes,aCityCode);
	string retData = iHttpNet.SyncPostData(KGetLiveWeatherUrl,iSendHes);

	Json::Value jsonValue;
	string retstring = getHessianString(retData);
	HessianRemoteReturning hes_return = parse_json(retstring,jsonValue);

	if(hes_return.getStatusCode() != 0)
	{

	}
	else
	{
		Json::Value jVal = hes_return.getValue();
		weatherDto.setDate(jVal["date"].asString());
		weatherDto.setHigh(jVal["high"].asInt());
		weatherDto.setIcon(jVal["icon"].asString());
		weatherDto.setIssuetime(jVal["issuetime"].asString());
		weatherDto.setLow(jVal["low"].asInt());
		weatherDto.setTemperature(jVal["temperature"].asString());
		weatherDto.setWeatherCityCode(jVal["weatherCityCode"].asString());
		weatherDto.setWeatherType(jVal["weatherType"].asString());
		weatherDto.setWind(jVal["wind"].asString());
	}

	return weatherDto;
}


/************************************************************************/
// 函数：get7WeatherDatas
// 功能：获取未来7天的天气数据
// 参数：归属地表中的城市ID
// 返回：天气数据数组（GPtrArray）
/************************************************************************/
GPtrArray* CacNetEngine::get7WeatherDatas(string aCityCode)
{
	GPtrArray* weather7_list = g_ptr_array_new();

	iSendHes.clear();
	ihes_output.write_string(iSendHes,aCityCode);
	string retData = iHttpNet.SyncPostData(KGet7WeatherUrl,iSendHes);

	Json::Value jsonValue;
	string retstring = getHessianString(retData);
	HessianRemoteReturning hes_return = parse_json(retstring,jsonValue);

	if(hes_return.getStatusCode() != 0)
	{
		
	}
	else
	{
		Json::Value jVal = hes_return.getValue();
		if(jVal.isArray())
		{
			int valSize = jVal.size();
			for (int i = 0;i < valSize;i++)
			{
				WeatherDto* weatherDto = new WeatherDto();
				weatherDto->setDate(jVal[i]["date"].asString());
				weatherDto->setHigh(jVal[i]["high"].asInt());
				weatherDto->setIcon(jVal[i]["icon"].asString());
				weatherDto->setIssuetime(jVal[i]["issuetime"].asString());
				weatherDto->setLow(jVal[i]["low"].asInt());
				weatherDto->setTemperature(jVal[i]["temperature"].asString());
				weatherDto->setWeatherCityCode(jVal[i]["weatherCityCode"].asString());
				weatherDto->setWeatherType(jVal[i]["weatherType"].asString());
				weatherDto->setWind(jVal[i]["wind"].asString());

				g_ptr_array_add(weather7_list,weatherDto);
			}
		}
	}
	
	return weather7_list;
}


/************************************************************************/
// 函数：getOrUpdatePD
// 功能：获取归属地数据
// 参数：标识位（0：全部数据 非0：增量数据）
// 返回：归属地数据数组（GPtrArray）
/************************************************************************/
GPtrArray* CacNetEngine::getOrUpdatePD(string aFlag)
{
	GPtrArray* pd_list = g_ptr_array_new();

	iSendHes.clear();
	ihes_output.write_string(iSendHes,aFlag);
	string retData = iHttpNet.SyncPostData(KGetOrUpdatePDUrl,iSendHes);

	Json::Value jsonValue;
	string retstring = getHessianString(retData);
	string retstring8;
	CChineseCodeLib::UTF_8ToGB2312(retstring8,(char*)retstring.c_str(),retstring.length());

	HessianRemoteReturning hes_return = parse_json(retstring8,jsonValue);

	if(hes_return.getStatusCode() != 0)
	{

	}
	else
	{
		Json::Value jVal = hes_return.getValue();
		if(jVal.isArray())
		{
			int valSize = jVal.size();
			for (int i = 0;i < valSize;i++)
			{
				PhoneDistrictDto* phoneDistrict = new PhoneDistrictDto();
				phoneDistrict->setDistrictCity(jVal[i]["districtCity"].asString());
				phoneDistrict->setDistrictNumber(jVal[i]["districtNumber"].asString());
				phoneDistrict->setDistrictProvince(jVal[i]["districtProvince"].asString());
				phoneDistrict->setFeeType(jVal[i]["feeType"].asString());
				phoneDistrict->setWeatherCityCode(jVal[i]["weatherCityCode"].asString());
				phoneDistrict->setRange(jVal[i]["range"].asString());
				phoneDistrict->setUpdateFlg(jVal[i]["updateFlg"].asInt());

				g_ptr_array_add(pd_list,phoneDistrict);
			}
		}
	}

	return pd_list;
}


/************************************************************************/
// 函数：getHessianString
// 功能：转换Hessian格式的数据为明文字符串(Json格式)
// 参数：Hessian格式数据
// 返回：明文字符串(Json格式)
/************************************************************************/
string CacNetEngine::getHessianString(string aHessianStr)
{
	auto_ptr<input_stream> i_stream(new string_input_stream(aHessianStr));
	hessian_input hes_in(i_stream);
	return hes_in.read_string();
}


/************************************************************************/
// 函数：parse_json
// 功能：解析Json格式字符串并转成HessianRemoteRutrning对象
// 参数：aJson_string：Json格式字符串	
// 参数：jsonValue：解析后的Json数据
// 返回：明文字符串(Json格式)
/************************************************************************/
HessianRemoteReturning CacNetEngine::parse_json(string& aJson_string,Json::Value& jsonValue)
{
	HessianRemoteReturning remoteRet;

	Json::Reader	reader;
	if(reader.parse(aJson_string,jsonValue))
	{
 		remoteRet.setStatusCode(jsonValue["statusCode"].asInt());
 		remoteRet.setStatusText(jsonValue["statusText"].asString());
 		remoteRet.setOperationCode(jsonValue["operationCode"].asInt());
 		remoteRet.setValue(jsonValue["value"]);
	}
	return remoteRet;
}