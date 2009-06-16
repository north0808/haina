drop table if exists LOG;
drop table if exists Weather;
create table LOG (ID varchar(255) not null, HANDLE varchar(20), INFOCLASS varchar(50), IP varchar(20), LOGTIME varchar(20), REMARK varchar(200), ROLENAME varchar(20), VERSION bigint, primary key (ID));
create table Weather (ID varchar(255) not null, date varchar(20), weatherCityCode varchar(20), weatherType varchar(50), high integer, low integer, wind varchar(30), icon varchar(30), isNight bit, issuetime varchar(10), VERSION bigint, primary key (ID));
