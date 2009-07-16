#ifndef __PHONEDISTRICTDTO_H__
#define __PHONEDISTRICTDTO_H__
#include <string>

using namespace std;

class PhoneDistrictDto
{
public:
	void	setDistrictNumber(string aNumber);
	string	getDistrictNumber();
	
	void	setRange(string aRange);
	string	getRange();

	void	setFeeType(string aFeeType);
	string	getFeeType();

	void	setDistrictCity(string aCity);
	string	getDistrictCity();

	void	setDistrictProvince(string aProvince);
	string	getDistrictProvince();

	void	setUpdateFlg(int aFlg);
	int		getUpdateFlg();

	void	setWeatherCityCode(string aWeatherCityCode);
	string	getWeatherCityCode();

private:
	string		districtNumber;			/*区号*/				
	string		range;					/*手机前7位范围*/				
	string		feeType;				/*资费类型*/				
	string		districtCity;			/*城市*/				
	string		districtProvince;		/*省份*/				
	int			updateFlg;				/*更新标志*/				
	string		weatherCityCode;		/*天气代码*/				
	
};

#endif /* __PHONEDISTRICTDTO_H__ */
