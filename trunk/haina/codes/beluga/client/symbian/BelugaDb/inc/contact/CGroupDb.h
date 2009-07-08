/*
 ============================================================================
 Name		: CGroupDb.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Group Database
 ============================================================================
 */

#ifndef __CGROUPDB_H__
#define __CGROUPDB_H__


class CGroupDb : public CEntityDb
{
public:
	CGroupDb();
    ~CGroupDb();

    IMPORT_C gint32 InitEntityDb();
    IMPORT_C gint32 GetMaxId(guint32 * nMaxId);
    IMPORT_C gint32 GetEntityById(guint32 nId, CDbEntity** ppEntity);

    IMPORT_C gint32 SaveEntity(CDbEntity * pEntity);
    IMPORT_C gint32 DeleteEntity(guint32 nEntityId);
    IMPORT_C gint32 UpdateEntity(CDbEntity * pEntity);

    IMPORT_C gint32 DeleteAllGroupsByTag(guint32 nTagId);
    IMPORT_C gint32 ReleaseGroupAllRelations(guint32 nGroupId);
    IMPORT_C gint32 ReleaseGroupAllRelations(CGroup * pGroup);
    
    IMPORT_C gint32 GetContactRelationGroups(guint32 nContactId, CGroupIterator ** ppGroupIterator);
    IMPORT_C gint32 GetAllGroupsByTag(guint32 nTagId, CGroupIterator ** ppGroupIterator);

    IMPORT_C gint32 GetGroupsTotalityByTag(guint32 nTagId, guint32 *totality);

    IMPORT_C gint32 CheckGroupNameConflict(gchar * groupName, gboolean * bConflict);

private:
	gboolean CheckGroupInTag(guint32 nGroupId, guint32 nTagId);
};

#endif

