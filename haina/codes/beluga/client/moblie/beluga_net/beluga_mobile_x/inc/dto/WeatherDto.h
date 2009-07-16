#ifndef __WEATHERDTO_H__
#define __WEATHERDTO_H__
#include <string>

using namespace std;

class WeatherDto
{
public:
	void	setDate(string aDate);
	string	getDate();
	
	void	setWeatherCityCode(string aCityCode);
	string	getWeatherCityCode();

	void	setWeatherType(string aWeatherType);
	string	getWeatherType();

	void	setWind(string aWind);
	string	getWind();

	void	setTemperature(string aTemperature);
	string	getTemperature();

	void	setIcon(string aIconPath);
	string	getIcon();

	void	setIsNight(bool aIsNight);
	bool	getIsNight();

	void	setHigh(int aHigh);
	int		getHigh();

	void	setLow(int aLow);
	int		getLow();

	void	setIssuetime(string aIssuetime);
	string	getIssuetime();

private:
	string	date;				/*天气日期*/ 				
	string	weatherCityCode;	/*城市代码*/				
	string	weatherType;		/*天气类型*/				
	string	wind;				/*风速*/				
	string	temperature;		/*实时温度*/				
	string	icon;				/*天气图片URI*/				
	bool	isNight;			/*是否夜里*/				
	int		high;				/*最高气温*/				
	int		low;				/*最低气温*/				
	string	issuetime;			/*发布时间*/				
};

#endif /* __WEATHERDTO_H__ */
