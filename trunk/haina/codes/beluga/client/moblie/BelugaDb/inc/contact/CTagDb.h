/*
 ============================================================================
 Name		: CTagDb.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Tag Database
 ============================================================================
 */

#ifndef __CTAGDB_H__
#define __CTAGDB_H__


#include <glib.h>
#include "CEntityDb.h"
#include "CTag.h"

class CTagIterator;

class CTagDb : public CEntityDb
{
public:
	IMPORT_C CTagDb();
    IMPORT_C ~CTagDb();

    IMPORT_C gint32 InitEntityDb();
    IMPORT_C gint32 GetMaxId(guint32 * nMaxId);
    IMPORT_C gint32 GetEntityById(guint32 nId, CDbEntity** ppEntity);

    IMPORT_C gint32 SaveEntity(CDbEntity * pEntity);
    IMPORT_C gint32 DeleteEntity(guint32 nEntityId);
    IMPORT_C gint32 UpdateEntity(CDbEntity * pEntity);

    IMPORT_C gint32 DeleteAllTags();
    
    IMPORT_C gint32 GetAllTags(CTagIterator ** ppTagIterator);
    
    IMPORT_C gint32 GetTagsTotality(guint32 *totality);

    IMPORT_C gint32 CheckTagNameConflict(gchar * tagName, gboolean * bConflict);

};

#endif

