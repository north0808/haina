drop table LOG;
drop table Weather;
drop table if exists PhoneDistrict;

create table LOG (ID varchar(255) not null, HANDLE varchar(20), INFOCLASS varchar(50), IP varchar(20), LOGTIME varchar(20), REMARK varchar(200), ROLENAME varchar(20), VERSION int8, primary key (ID));
create table Weather (ID varchar(255) not null, date varchar(20), weatherCityCode varchar(20), weatherType varchar(50), high int4, low int4, wind varchar(30), icon varchar(30), isNight bool, issuetime varchar(10), VERSION int8, primary key (ID));
