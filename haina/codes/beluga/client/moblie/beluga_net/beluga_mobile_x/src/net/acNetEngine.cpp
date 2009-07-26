#include "acNetEngine.h"
#include "ChineseCodeLib.h"

#include "xconfig.h"

using namespace hessian;



CacNetEngine::CacNetEngine():iSendHes("")
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
// ������setNetHost
// ���ܣ����÷���������������IP��ַ
// ������aHostName��������������IP
// ������aHttp_Port�����ʶ˿ڣ�Ĭ��Ϊ80�˿�
// ���أ��޷���
/************************************************************************/
bool CacNetEngine::setNetHost(LPCTSTR aHostName,int aHttp_Port)
{
	return iHttpNet.SetHostName(aHostName,aHttp_Port);
}


/************************************************************************/
// ������getQQStatus
// ���ܣ���ȡQQ����״̬
// ������QQ����
// ���أ�����(10000) ����(10001) ����(-1)  �����ʧ�ܿ���getErrCode��ȡ������Ϣ��
/************************************************************************/
int CacNetEngine::getQQStatus(string aQQId)
{
	iSendHes.clear();
 	ihes_output.write_string(iSendHes,aQQId);
 	string retData = iHttpNet.SyncPostData(KGetQQStatusUrl,iSendHes);

	Json::Value jsonValue;
	string retstring = getHessianString(retData);
	HessianRemoteReturning hes_return = parse_json(retstring,jsonValue);
	iErrCode = hes_return.getStatusCode();

	if(iErrCode != 0)
		return -1;

	int online = hes_return.getValue().asInt();
	return online;
}


/************************************************************************/
// ������getLiveWeather
// ���ܣ���ȡ��������������
// �����������ر��еĳ���ID
// ���أ��ɹ������������ݣ�WeatherDto��ʧ�ܷ���NULL�����ʧ�ܿ���getErrCode��ȡ������Ϣ��
/************************************************************************/
WeatherDto* CacNetEngine::getLiveWeather(string aCityCode)
{
	WeatherDto* weatherDto = new WeatherDto();

	iSendHes.clear();
	ihes_output.write_string(iSendHes,aCityCode);
	string retData = iHttpNet.SyncPostData(KGetLiveWeatherUrl,iSendHes);

	Json::Value jsonValue;
	string retstring = getHessianString(retData);
	HessianRemoteReturning hes_return = parse_json(retstring,jsonValue);
	iErrCode = hes_return.getStatusCode();

	if(iErrCode != 0)
	{
		return NULL;
	}
	else
	{
		Json::Value jVal = hes_return.getValue();
		weatherDto->setDate(jVal["date"].asString());
		weatherDto->setHigh(jVal["high"].asInt());
		weatherDto->setIcon(jVal["icon"].asString());
		weatherDto->setIssuetime(jVal["issuetime"].asString());
		weatherDto->setLow(jVal["low"].asInt());
		weatherDto->setTemperature(jVal["temperature"].asString());
		weatherDto->setWeatherCityCode(jVal["weatherCityCode"].asString());
		weatherDto->setWeatherType(jVal["weatherType"].asString());
		weatherDto->setWind(jVal["wind"].asString());
	}
	return weatherDto;
}


/************************************************************************/
// ������get7WeatherDatas
// ���ܣ���ȡδ��7�����������
// �����������ر��еĳ���ID
// ���أ������������飨GPtrArray��ʧ�ܷ���NULL�����ʧ�ܿ���getErrCode��ȡ������Ϣ��
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
	iErrCode = hes_return.getStatusCode();

	if(iErrCode != 0)
	{
		g_ptr_array_free(weather7_list,true);
		return NULL;
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
// ������getOrUpdatePD
// ���ܣ���ȡ����������
// ��������ʶλ��0��ȫ������ ��0���������ݣ�
// ���أ��������������飨GPtrArray�� ʧ�ܷ���NULL	�����ʧ�ܿ���getErrCode��ȡ������Ϣ��
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
	iErrCode = hes_return.getStatusCode();
	
	if(iErrCode != 0)
	{
		g_ptr_array_free(pd_list,true);
		return NULL;
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
// ������registerx
// ���ܣ�ע���˺�
// ������loginName����¼�û���	password����¼����		mobile���ֻ�����
// ���أ�����ɹ����ط����������Passport��ʧ�ܷ����ַ��������ʧ�ܿ���getErrCode��ȡ������Ϣ��
// ��ע��loginName���Ȳ��ܳ���60��password���Ȳ��ܳ���16
/************************************************************************/
string CacNetEngine::registerx(string loginName, string password, string mobile)
{
	if (!iSendHes.empty())
	{
		iSendHes.clear();
	}
	ihes_output.write_string(iSendHes,loginName);
	ihes_output.write_string(iSendHes,password);
	ihes_output.write_string(iSendHes,mobile);
	string retData = iHttpNet.SyncPostData(KRegisterUrl,iSendHes);

	Json::Value jsonValue;
	string retstring = getHessianString(retData);
	HessianRemoteReturning hes_return = parse_json(retstring,jsonValue);

	iErrCode = hes_return.getStatusCode();
	return iErrCode == 0?hes_return.getValue().asString():"";
}


/************************************************************************/
// ������login
// ���ܣ���¼ϵͳ
// ������loginName����¼�û���	password����¼����
// ���أ�����ɹ����ط����������Passport��ʧ�ܷ����ַ��������ʧ�ܿ���getErrCode��ȡ������Ϣ��
/************************************************************************/
string CacNetEngine::login(string loginName, string password)
{
	iSendHes.clear();
	ihes_output.write_string(iSendHes,loginName);
	ihes_output.write_string(iSendHes,password);
	string retData = iHttpNet.SyncPostData(KLoginUrl,iSendHes);

	Json::Value jsonValue;
	string retstring = getHessianString(retData);
	HessianRemoteReturning hes_return = parse_json(retstring,jsonValue);

	iErrCode = hes_return.getStatusCode();
	return iErrCode == 0?hes_return.getValue().asString():"";
}


/************************************************************************/
// ������logoutByPsssport
// ���ܣ���passport��Ϊ�����˳�ϵͳ
// ��������¼��ȡ��passport
// ���أ��˳��Ƿ�ɹ������ʧ�ܿ���getErrCode��ȡ������Ϣ��
/************************************************************************/
bool CacNetEngine::logoutByPsssport(string passport)
{
	iSendHes.clear();
	ihes_output.write_string(iSendHes,passport);
	string retData = iHttpNet.SyncPostData(KLogoutByPsssportUrl,iSendHes);

	Json::Value jsonValue;
	string retstring = getHessianString(retData);
	HessianRemoteReturning hes_return = parse_json(retstring,jsonValue);

	iErrCode = hes_return.getStatusCode();
	return iErrCode == 0?true:false;
}












int CacNetEngine::getErrCode()
{
	return iErrCode;
}

/************************************************************************/
// ������getHessianString
// ���ܣ�ת��Hessian��ʽ������Ϊ�����ַ���(Json��ʽ)
// ������Hessian��ʽ����
// ���أ������ַ���(Json��ʽ)
/************************************************************************/
string CacNetEngine::getHessianString(string aHessianStr)
{
	auto_ptr<input_stream> i_stream(new string_input_stream(aHessianStr));
	hessian_input hes_in(i_stream);
	return hes_in.read_string();
}


/************************************************************************/
// ������parse_json
// ���ܣ�����Json��ʽ�ַ�����ת��HessianRemoteRutrning����
// ������aJson_string��Json��ʽ�ַ���	
// ������jsonValue���������Json����
// ���أ������ַ���(Json��ʽ)
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







/************************************************************************/
/* 
10002	QQ��������
10003	�������
10004	�����쳣û������

1002	��¼����������Ч
1007	�û������ڻ��û�δ����
1008	��¼������Ч���ѹ���
1009	��Ч�ĵ�¼����
1010	��Ч���ֻ�����
1013	��¼���ƻ��ֻ������Ѵ���
*/
/************************************************************************/