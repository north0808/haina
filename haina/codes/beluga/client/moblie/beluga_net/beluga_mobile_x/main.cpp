#include "myinc.h"
#include "HttpInternet.h"
#include "acNetEngine.h"
#include "xconfig.h"

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
	string qq = "95467703";

	CacNetEngine* acNetEngine = new CacNetEngine(KHostName,KHostNamePort);
	int ret = acNetEngine->getQQStatus(qq);
	if(ret == 10000)
		cout << qq << "\tonline" << endl;
	else if(ret == 10001)
		cout << qq << "\toffline" << endl;
	else
		cout << "error" << endl;

	delete acNetEngine;




	return 0;
}