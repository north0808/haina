/*
 ============================================================================
 Name		: Beluga.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Base define
 ============================================================================
 */

#ifndef BELUGA_H_
#define BELUGA_H_

/* database name */
#define	BELUGA_DATABASE		"beluga.db"



/* define error code */
enum ESide
	{
	ESide_Client = 0,
	ESide_Server = 1
	};

enum EModule
	{
	EModule_Sys = 0,
	EModule_Db = 1
	};

enum ECode
	{
	ECode_No_Error = 0,
	ECode_Invalid_Param = 1,
	ECode_Not_Exist = 2,
	ECode_No_Memory = 3,
	ECode_Insert_Failed = 4,
	ECode_Update_Failed = 5,
	
	};

#define	ERROR(side, module, code)	((side << 24)|(module << 16)|(code))


#endif /* BELUGA_H_ */
