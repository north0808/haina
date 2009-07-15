/*
 ============================================================================
 Name		: CDbEntity.cpp
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Database base entity 
 ============================================================================
 */

#include "CDbEntity.h"
#include "CEntityDb.h"

GLDEF_C void freeGStringArray(GPtrArray * pArray);


EXPORT_C CDbEntity::~CDbEntity()
	{
	if (m_pFieldsValue)
		freeGStringArray(m_pFieldsValue);
	m_pFieldsValue = NULL;
	
	if (m_pFieldsIndex)
		g_array_free(m_pFieldsIndex, TRUE);
	m_pFieldsIndex= NULL;
	}

EXPORT_C gint32 CDbEntity::GetFieldsName(GPtrArray ** fieldsName)
	{
	m_pEntityDb->GetEntityFieldsName(fieldsName);
	return 0;
	}

EXPORT_C gint32 CDbEntity::GetFieldsValue(GArray * fieldsIndex, GPtrArray ** fieldsValue)
	{
	*fieldsValue = g_ptr_array_sized_new(fieldsIndex->len);
	for (guint i=0; i<fieldsIndex->len; i++)
		for (guint j=0; j<m_pFieldsIndex->len; i++)
			{
			if (g_array_index(m_pFieldsIndex, guint32, j) == g_array_index(fieldsIndex, guint32, i))
				g_ptr_array_add(*fieldsValue, g_ptr_array_index(m_pFieldsValue, j));
			}
	return 0;
	}

EXPORT_C gint32 CDbEntity::GetFieldValue(guint32 fieldIndex, GString ** fieldValue)
	{
	*fieldValue = g_string_new(((GString*)g_ptr_array_index(m_pFieldsValue, fieldIndex))->str);
	return 0;
	}

EXPORT_C gint32 CDbEntity::SetFieldsValue(GArray * fieldsIndex, const GPtrArray * fieldsValue)
	{
	for (guint i=0; i<fieldsIndex->len; i++)
		for (guint j=0; j<m_pFieldsIndex->len; i++)
			{
			if (g_array_index(m_pFieldsIndex, guint32, j) == g_array_index(fieldsIndex, guint32, i))
				{
				GString * fieldValue = (GString*)g_ptr_array_index(m_pFieldsValue, j);
				g_string_assign(fieldValue, ((GString*)g_ptr_array_index(fieldsValue, i))->str);
				}
			}
	return 0;
	}

EXPORT_C gint32 CDbEntity::SetFieldValue(guint32 fieldIndex, const GString * fieldValue)
	{
	GString * field = (GString*)g_ptr_array_index(m_pFieldsValue, fieldIndex);
	g_string_assign(field, fieldValue->str);
	return 0;
	}

CEntityDb* CDbEntity::GetEntityDb()
	{
	return m_pEntityDb;
	}
