/*
 ============================================================================
 Name		: CConfigDb.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Config Database
 ============================================================================
 */

#ifndef __CCONFIGDB_H__
#define __CCONFIGDB_H__

#include "CEntityDb.h"

class CConfigDb : public CEntityDb
{
public:
	CConfigDb();
    ~CConfigDb();

    IMPORT_C gint32 InitEntityDb();
    IMPORT_C gint32 GetMaxId(guint32 * nMaxId);
    IMPORT_C gint32 GetEntityById(guint32 nId, CDbEntity** ppEntity);

    IMPORT_C gint32 SaveEntity(CDbEntity * pEntity);
    IMPORT_C gint32 DeleteEntity(guint32 nEntityId);
    IMPORT_C gint32 UpdateEntity(CDbEntity * pEntity);
    
    IMPORT_C gint32 GetConfigByName(gchar * configName, CConfig* pConfig);
    
    IMPORT_C gint32 DeleteAllConfigs();
    
    IMPORT_C gint32 GetAllConfigs(CConfigIterator ** ppConfigIterator); 
    
    IMPORT_C gint32 GetConfigsTotality(guint32 * totality);
};

#endif
