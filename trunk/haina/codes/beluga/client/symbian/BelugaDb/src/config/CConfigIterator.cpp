/*
 ============================================================================
 Name		: CGonfigIterator.cpp
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Config Iterator
 ============================================================================
 */

#include "CConfigIterator.h"
#include "CConfig.h"


EXPORT_C gint32 CConfigIterator::Current(CDbEntity ** ppEntity)
	{
	if (ppEntity == NULL)
		return ERROR(ESide_Client, EModule_Db, ECode_Invalid_Param);
	
	*ppEntity = NULL;
	if (m_dbQuery->eof())
		return ERROR(ESide_Client, EModule_Db, ECode_Not_Exist);
	
	 /* fill config table fields */
	*ppEntity = new CConfig(m_pEntityDb);
	if (NULL == *ppEntity)
		{
		return ERROR(ESide_Client, EModule_Db, ECode_No_Memory);
		}
	
	CConifg * pConfig = *ppEntity;
	for (int i=0; i<ConfigField_EndFlag; i++)
		{
		GString * fieldValue = g_string_new(m_dbQuery->fieldValue(i));
		pConfig->SetFieldValue(i, fieldValue);	
		g_string_free(fieldValue, TRUE);
		}
	
	return 0;
	}
 
EXPORT_C gint32 CConfigIterator::Next(gboolean * pSuccess)
	{
	*pSuccess = !m_dbQuery->eof();
	if (FALSE == *pSuccess)
		return ERROR(ESide_Client, EModule_Db, ECode_End_Of_Row);
	
	m_dbQuery->nextRow();
	return 0; 
	}

EXPORT_C gint32 CConfigIterator::Prev(gboolean * pSuccess)
	{
	*pSuccess = FALSE;
	return ERROR(ESide_Client, EModule_Db, ECode_Not_Implemented);
	}
