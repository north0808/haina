/*
 ============================================================================
 Name		: CDbEntity.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Base Database Entity
 ============================================================================
 */

#ifndef __CDBENTITY_H__
#define __CDBENTITY_H__

#include <glib.h>

class CDbEntity
{
	CDbEntity(CEntityDb * pEntityDb):
	m_pEntityDb(pEntityDb)
	{
	m_pFieldsValue = NULL;
	m_pFieldsIndex = NULL;
	}
	
	~CDbEntity();
	
public:
	IMPORT_C gint32 GetFieldsName(GPtrArray ** fieldsName);
	IMPORT_C gint32 GetFieldsValue(GArray * fieldsIndex, GPtrArray ** fieldsValue);
	IMPORT_C gint32 GetFieldValue(guint32 fieldIndex, GString ** fieldValue);
	IMPORT_C gint32 SetFieldsValue(GArray * fieldsIndex, const GPtrArray * fieldsValue);
	IMPORT_C gint32 SetFieldValue(guint32 fieldIndex, const GString * fieldValue);
	
	CEntityDb * GetEntityDb();
	
protected:
	CEntityDb * m_pEntityDb;
	
	GPtrArray * m_pFieldsValue;
	GArray	  * m_pFieldsIndex;
};

#endif
