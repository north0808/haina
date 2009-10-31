/**
* @author north0808@gmail.com
* @version 1.0
*/
#include "beluganettest.h"
//user add
#include <hessian/HttpConnection.h>//hessian
#include <pubService/IPubServiceProxy.h>//hessian
#include <dto/WeatherDto.h>//hessian
#include <json/json.h>//json
#include <windows.h>//cursor
#include <contact/CContactDb.h>//db
#include <sstream>

using namespace std;
using namespace hessian;
using namespace pubService;
using namespace dto;

string URL("http://61.129.70.8:8080/beluga/pub");

BelugaNetTest::BelugaNetTest(QWidget *parent, Qt::WFlags flags)
: QMainWindow(parent, flags)
{
	ui.setupUi(this);
	QObject::connect(ui.pushButtonQQSubmit,SIGNAL(clicked()),this,SLOT(pushButtonQQSubmitClick()));
	QObject::connect(ui.pushButtonCitySubmit,SIGNAL(clicked()),this,SLOT(pushButtonCitySubmitClick()));
	QObject::connect(ui.pushButtonWeatherSubmit,SIGNAL(clicked()),this,SLOT(pushButtonWeatherSubmitClick()));
}

BelugaNetTest::~BelugaNetTest()
{

}

void BelugaNetTest::pushButtonQQSubmitClick()
{
	SetCursor(LoadCursor(NULL,IDC_WAIT)); //�趨���æµ״̬
	ui.labelQQStatus->setText("checking...");
	string qq(ui.lineEditQQ->text().toAscii());
	HttpConnection connection(URL);
	IPubServiceProxy *service = new IPubServiceProxy(connection);
	try
	{
		QApplication::processEvents();
		//��hessian�д��京�����ĵ��ַ���ʱҪ��utf8���뷽ʽ����
		HessianRemoteReturning ret = service->getQQStatus(qq);//Ĭ��30�볬ʱ
		/*
		* ״̬������
		* 1.�ɹ� statusCode=0
		* 2.QQ�������� statusCode = 10002
		* 3.������� statusCode= 10003 
		* 
		* valueֵΪ��
		* 1.���� value=10000
		* 2.������ value=10001
		*/
		if(ret.statusCode == 0&&ret.value.compare("10000")==0)
		{
			ui.labelQQStatus->setText("online");
		}
		else
		{
			ui.labelQQStatus->setText("offline or error");
		}
	}
	catch(...)
	{
		//30�볬ʱ����������
		ui.labelQQStatus->setText("problem");
	}
	SetCursor(NULL);//�ָ����״̬	
}


void BelugaNetTest::pushButtonWeatherSubmitClick()
{
	SetCursor(LoadCursor(NULL,IDC_WAIT)); //�趨���æµ״̬
	ui.plainTextEditCity->setPlainText("checking...");
	string weatherCityCode(ui.lineEditWeatherCityCode->text().toUtf8());
	string url(URL);
	HttpConnection connection(url);
	IPubServiceProxy *service = new IPubServiceProxy(connection);
	try
	{
		QApplication::processEvents();
		//��hessian�д��京�����ĵ��ַ���ʱҪ��utf8���뷽ʽ����
		HessianRemoteReturning ret = service->getLiveWeather(weatherCityCode);//Ĭ��30�볬ʱ
		/**
		* ״̬������
		* 1.�ɹ� statusCode=0
		* 2.�����쳣û������ statusCode = 10004
		* 
		* valueֵΪ��
		* WeatherDto����.
		* 
		*/
		if(ret.statusCode == 0)
		{
			QString str = QString::fromUtf8(ret.value.c_str());
			Json::Reader reader;
			Json::Value jsonValue;
			WeatherDto weatherDto;
			if(reader.parse(string(str.toAscii()),jsonValue))
			{
				/*
				��˳���ȡ
				date:��������
				high:�������
				icon:����ͼƬURI
				issuetime:����ʱ��
				low:�������
				temperature:ʵʱ�¶�
				weatherCityCode:���д���
				weatherType:��������
				wind:����
				*/
				weatherDto.setDate(jsonValue["date"].asString());
				weatherDto.setHigh(jsonValue["high"].asInt());
				weatherDto.setIcon(jsonValue["icon"].asString());
				weatherDto.setIssuetime(jsonValue["issuetime"].asString());
				weatherDto.setLow(jsonValue["low"].asInt());
				weatherDto.setTemperature(jsonValue["temperature"].asString());
				weatherDto.setWeatherCityCode(jsonValue["weatherCityCode"].asString());
				weatherDto.setWeatherType(jsonValue["weatherType"].asString());
				weatherDto.setWind(jsonValue["wind"].asString());
			}
			char msg[256] = {0};
			sprintf(msg,"Date:%s\nHigh:%d\nIcon:%s\nIssuetime:%s\nLow:%d\nTemperature:%s\nWeatherCityCode:%s\nWeatherType:%s\nWind:%s"
				, weatherDto.getDate().c_str(), weatherDto.getHigh(), weatherDto.getIcon().c_str(), weatherDto.getIssuetime().c_str(), weatherDto.getLow(), weatherDto.getTemperature().c_str(), weatherDto.getWeatherCityCode().c_str(), weatherDto.getWeatherType().c_str(), weatherDto.getWind().c_str());
			ui.plainTextEditCity->setPlainText(QString(msg));
		}
		else
		{
			ui.plainTextEditCity->setPlainText("failure");
		}
	}
	catch(...)
	{
		//30�볬ʱ����������
		ui.plainTextEditCity->setPlainText("problem");
	}
	SetCursor(NULL);//�ָ����״̬	
}

void BelugaNetTest::pushButtonCitySubmitClick()
{
	SetCursor(LoadCursor(NULL,IDC_WAIT)); //�趨���æµ״̬
	ui.plainTextEditCity->setPlainText("checking...");
	HttpConnection connection(URL);
	IPubServiceProxy *service = new IPubServiceProxy(connection);
	try
	{
		QApplication::processEvents();
		//��ȡ�������ݿ������Ϣ�ܼ�¼����
		CContactDb *contactDb = new CContactDb();
		if (NULL == contactDb)
		{
			printf("Create contact db instance error.\n");
			return;
		}
		contactDb->InitEntityDb("\\Program Files\\Beluga\\beluga.db");
		guint16 updateFlag(0);
		try
		{
			contactDb->GetMaxPhoneDistrictUpdateFlag(&updateFlag);
			//updateFlag = 0;
		}
		catch(...){}
		if(updateFlag<0)
		{
			SetCursor(NULL);//�ָ����״̬	
			ui.plainTextEditCity->setPlainText("local db read error");
			return;
		}
		//��hessian�д��京�����ĵ��ַ���ʱҪ��utf8���뷽ʽ����
		HessianRemoteReturning ret = service->getOrUpdatePDCount(updateFlag);
		int count(0);
		/**
		* ״̬������
		* 1.�ɹ� statusCode=0
		* 
		* valueֵΪ��
		* List���ϣ����϶���ΪPhoneDistrictDto.
		* 
		*/
		if(ret.statusCode == 0)
		{
			QString str = QString::fromUtf8(ret.value.c_str());
			count = str.toInt();
		}
		else
		{
			ui.plainTextEditCity->setPlainText("failure");
			return;
		}
		guint len = 1;
		for(guint i(0);i<count;i+=len)
		{
			ret = service->getOrUpdatePD(updateFlag, i, len);//Ĭ��30�볬ʱ
			QString str = QString::fromUtf8(ret.value.c_str());
			Json::Reader reader;
			Json::Value jsonValue;
			WeatherDto weatherDto;
			if(reader.parse(string(str.toAscii()),jsonValue))
			{
				char msg[50]={0};
				sprintf(msg,"add:%d/%d",i+1,count);
				ui.plainTextEditCity->setPlainText(msg);
				QApplication::processEvents();
				if(jsonValue.isArray())
				{
					int size = jsonValue.size();
					if(size<=0)
					{
						ui.plainTextEditCity->setPlainText("server response null");
					}
					for (int i = 0;i < size;i++)
					{
						Json::Value jsonValueTmp = jsonValue[i];
						stPhoneDistrict phoneDistrict;
						/*
						��˳���ȡ
						districtCity:����
						districtNumber:����
						districtProvince:ʡ��
						feeType:�ʷ�����
						range:�ֻ�ǰ7λ��Χ
						updateFlg:���±�־
						weatherCityCode:��������
						*/
						strcpy(phoneDistrict.districtName, jsonValueTmp["districtCity"].asString().c_str());
						strcpy(phoneDistrict.districtNumber, jsonValueTmp["districtNumber"].asString().c_str());
						strcpy(phoneDistrict.ownerState, jsonValueTmp["districtProvince"].asString().c_str());
						strcpy(phoneDistrict.feeType, jsonValueTmp["feeType"].asString().c_str());
						phoneDistrict.phoneRange = g_string_new(jsonValueTmp["range"].asString().c_str());
						phoneDistrict.updateFlag = jsonValueTmp["updateFlg"].asInt();
						strcpy(phoneDistrict.weatherCode, jsonValueTmp["weatherCityCode"].asString().c_str());

						contactDb->SavePhoneDistrict(&phoneDistrict);				
					}
				}
				else
				{
					ui.plainTextEditCity->setPlainText("server response is not an array");
				}
			}
			else
			{
				ui.plainTextEditCity->setPlainText("json parse error");
			}
		}
	}
	catch(...)
	{
		//30�볬ʱ����������
		ui.plainTextEditCity->setPlainText("problem");
	}
	SetCursor(NULL);//�ָ����״̬	
}
