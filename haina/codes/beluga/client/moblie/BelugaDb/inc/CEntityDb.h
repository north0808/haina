/*
 ============================================================================
 Name		: CEntityDb.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Database access base class
 ============================================================================
 */

#ifndef __CENTITYDB_H__
#define __CENTITYDB_H__

#include <glib.h>
#include "CppSQLite3.h"
#include "Beluga.h"

class CDbEntity;

class CEntityDb
{
public:
	IMPORT_C CEntityDb();
    IMPORT_C ~CEntityDb();
    
	
    IMPORT_C gint32 SetSortKey(guint32 fieldIndex, gboolean ascending);
    
    gint32 GetEntityFieldsName(GPtrArray ** fieldsName);
    
    virtual gint32 InitEntityDb(gchar* dbName) = 0;
    virtual gint32 GetMaxId(guint32 * nMaxId) = 0;
    virtual gint32 GetEntityById(guint32 nId, CDbEntity** ppEntity) = 0;

    virtual gint32 SaveEntity(CDbEntity * pEntity) = 0;
    virtual gint32 DeleteEntity(guint32 nEntityId) = 0;
    virtual gint32 UpdateEntity(CDbEntity * pEntity) = 0;
    
    IMPORT_C CppSQLite3DB * GetDatabase();
    IMPORT_C CppSQLite3Query * GetDbQuery();
    
	IMPORT_C gint32 OpenDatabase();
	IMPORT_C gint32 CloseDatabase();
	
protected:
	guint32 		m_nSortFieldIndex;
	gboolean		m_bSortAscending;
	
	GPtrArray	  * m_pFieldsName;
	CppSQLite3DB  	m_dbBeluga;
	CppSQLite3Query m_dbQuery;
	
	gchar		    m_dbName[260];	
	guint32  		m_ndbCount;
};

#endif 

