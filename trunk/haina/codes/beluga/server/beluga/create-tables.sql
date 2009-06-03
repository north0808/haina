drop table if exists LOG;
drop table if exists PhoneDistrict;
create table LOG (ID varchar(255) not null, HANDLE varchar(20), INFOCLASS varchar(50), IP varchar(20), LOGTIME varchar(20), REMARK varchar(200), ROLENAME varchar(20), VERSION bigint, primary key (ID));
create table PhoneDistrict (ID varchar(255) not null, districtNumber varchar(20), rangeStart bigint, rangeEnd bigint, feeType varchar(20), districtCity varchar(20), districtProvince varchar(20), updateFlg integer, pingyinCity varchar(20), weatherCityCode varchar(20), VERSION bigint, primary key (ID));
