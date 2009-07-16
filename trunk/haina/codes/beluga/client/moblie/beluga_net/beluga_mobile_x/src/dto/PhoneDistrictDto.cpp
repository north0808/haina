#include "PhoneDistrictDto.h"

void PhoneDistrictDto::setDistrictNumber(string aNumber)
{
	districtNumber = aNumber;
}
string PhoneDistrictDto::getDistrictNumber()
{
	return districtNumber;
}

void PhoneDistrictDto::setRange(string aRange)
{
	range = aRange;
}
string PhoneDistrictDto::getRange()
{
	return range;
}

void PhoneDistrictDto::setFeeType(string aFeeType)
{
	feeType = aFeeType;
}
string PhoneDistrictDto::getFeeType()
{
	return feeType;
}

void PhoneDistrictDto::setDistrictCity(string aCity)
{
	districtCity = aCity;
}
string PhoneDistrictDto::getDistrictCity()
{
	return districtCity;
}

void PhoneDistrictDto::setDistrictProvince(string aProvince)
{
	districtProvince = aProvince;
}
string PhoneDistrictDto::getDistrictProvince()
{
	return districtProvince;
}

void PhoneDistrictDto::setUpdateFlg(int aFlg)
{
	updateFlg = aFlg;
}
int	 PhoneDistrictDto::getUpdateFlg()
{
	return updateFlg;
}

void PhoneDistrictDto::setWeatherCityCode(string aWeatherCityCode)
{
	weatherCityCode = aWeatherCityCode;
}
string PhoneDistrictDto::getWeatherCityCode()
{
	return weatherCityCode;
}
