#ifndef	__ACNETENGINE_H__
#define	__ACNETENGINE_H__

#include <string>
#include <vector>
#include <WTypes.h>
#include "HttpInternet.h"
#include "PhoneDistrictDto.h"
#include "WeatherDto.h"

#include "glib/glib.h"


#include "json/json.h"

#include "hessian_output.h"
#include "hessian_input.h"
#include "string_input_stream.h"
#include "HessianRemoteReturning.h"

using namespace std;

class CacNetEngine
{
public:
	CacNetEngine();
	CacNetEngine(LPCTSTR aHostName,int aHttp_Port=INTERNET_DEFAULT_HTTP_PORT);
	~CacNetEngine();
public:
	void	setNetHost(LPCTSTR aHostName,int aHttp_Port=INTERNET_DEFAULT_HTTP_PORT);

public:
	int			getQQStatus(string aQQId);
	WeatherDto	getLiveWeather(string aCityCode);
	GPtrArray*	get7WeatherDatas(string aCityCode);
	GPtrArray*	getOrUpdatePD(string aFlag);
	
private:
	string getHessianString(string aHessianStr);
	HessianRemoteReturning	parse_json(string& aJson_string,Json::Value& jsonValue);

private:
	hessian::hessian_output	ihes_output;
//	hessian::hessian_input	ihes_input;


	string			iSendHes;
	CHttpInternet	iHttpNet;
};

#endif	/* __ACNETENGINE_H__ */