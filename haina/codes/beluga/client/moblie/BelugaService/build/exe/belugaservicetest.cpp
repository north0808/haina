#include "belugaservicetest.h"
//user add
#include <BelugaService.h>
#include <hessian/HttpConnection.h>
#include <pubService/IPubServiceProxy.h>

#include <windows.h>//cursor

using namespace std;
using namespace hessian;
using namespace pubService;

CBelugaServiceTest::CBelugaServiceTest(QWidget *parent, Qt::WFlags flags)
: QMainWindow(parent, flags)
{
	ui.setupUi(this);
	QObject::connect(ui.pushButtonSubmit,SIGNAL(clicked()),this,SLOT(pushButtonSubmitClick()));
}

CBelugaServiceTest::~CBelugaServiceTest()
{

}

void CBelugaServiceTest::pushButtonSubmitClick()
{
	SetCursor(LoadCursor(NULL,IDC_WAIT)); //�趨���æµ״̬
	ui.labelStatus->setText("");
	string qq(ui.lineEditQQ->text().toAscii());
	HttpConnection connection;
	IPubServiceProxy *service = new IPubServiceProxy(connection);
	try
	{
		string ret = service->getQQStatus(qq);//Ĭ��30�볬ʱ
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
		if(ret.compare("10000")==0)
		{
			//����
			ui.labelStatus->setText("online");
		}
		else if(ret.compare("-1")==0)
		{
			//����
			ui.labelStatus->setText("server busy");
		}
		else
		{
			//������
			ui.labelStatus->setText("offline");
		}
	}
	catch(...)
	{
		//30�볬ʱ����������
		ui.labelStatus->setText("net problem");
	}
	SetCursor(NULL);//�ָ����״̬	
}