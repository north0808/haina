//#include <WinInet.h>
#include "PhoneDistrictDto.h"
#include "WeatherDto.h"
#include "glib/glib.h"

#ifdef BELUGA_DLL_BUILD
#define BELUGA_API __declspec(dllexport)
#else
#define BELUGA_API __declspec(dllimport)
#endif
using namespace std;
class BELUGA_API iAcNetEngine
{
public:
	~iAcNetEngine();
	iAcNetEngine();
	virtual iAcNetEngine*		GetInstance()=0;
	virtual void		setNetHost(LPCTSTR aHostName,int aHttp_Port)=0;
	virtual int			getErrCode()=0;
	virtual int			getQQStatus(string aQQId)=0;
	virtual WeatherDto*	getLiveWeather(string aCityCode)=0;
	virtual GPtrArray*	get7WeatherDatas(string aCityCode)=0;
	virtual GPtrArray*	getOrUpdatePD(string aFlag)=0;
	virtual string		registerx(string loginName, string password, string mobile)=0;
	virtual string		login(string loginName, string password)=0;
	virtual bool		logoutByPsssport(string passport)=0;
};