/*
 ============================================================================
 Name		: CEntityDb.cpp
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Database access base class
 ============================================================================
 */

#include "CEntityDb.h"

GLDEF_C void freeGStringArray(GPtrArray * pArray);

CEntityDb::CEntityDb()
	{
	m_nSortFieldIndex = -1;
	m_ndbCount = 0;
	m_bSortAscending = TRUE;
	m_pFieldsName = NULL;
	}

CEntityDb::~CEntityDb()
	{
	if (m_pFieldsName)
		freeGStringArray(m_pFieldsName);
	m_pFieldsName = NULL;
	}
	
EXPORT_C gint32 CEntityDb::SetSortKey(guint32 fieldIndex, gboolean ascending)
	{
	m_nSortFieldIndex = fieldIndex;
	m_bSortAscending = ascending;
	return 0;
	}
    
gint32 CEntityDb::GetEntityFieldsName(GPtrArray ** fieldsName)
	{
	*fieldsName = g_ptr_array_sized_new(m_pFieldsName->len);
	for(int i=0; i<m_pFieldsName->len, i++)
		g_ptr_array_add(*fieldsName, g_string_new((g_ptr_array_index(m_pFieldsName, i))->str));
	return 0;
	}

gint32 CEntityDb::OpenDatabase()
	{
	if (m_ndbCount == 0)
		m_dbBeluga.open(BELUGA_DATABASE);
	else 
		m_ndbCount++;
	
	return 0;
	}

gint32 CEntityDb::CloseDatabase()
	{
	if (m_ndbCount > 0)
		m_ndbCount--;
	else
		m_dbBeluga.close();
	
	return 0;
	}
	
CppSQLite3DB * CEntityDb::GetDatabase()
	{
	return &m_dbBeluga;
	}

CppSQLite3Query * CEntityDb::GetDbQuery()
	{
	return &m_dbQuery;
	}
