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
	ECode_Not_Implemented = 1,
	ECode_Invalid_Param = 2,
	ECode_Not_Exist = 3,
	ECode_No_Memory = 4,
	ECode_Insert_Failed = 5,
	ECode_Update_Failed = 6,
	ECode_End_Of_Row = 7
	};

#define	ERROR(side, module, code)	((side << 24)|(module << 16)|(code))


#endif /* BELUGA_H_ */
