#include "acNetEngine.h"

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


/*
vector<WeatherDto> CacNetEngine::get7WeatherDatas(string aCityCode)
{
	return ;
}
vector<PhoneDistrictDto> CacNetEngine::getOrUpdatePD(int aFlag)
{
	return ;
}
*/




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
//		remoteRet.setValue(&(jsonValue["value"]));
 		remoteRet.setValue(jsonValue["value"]);
	}
	return remoteRet;
}