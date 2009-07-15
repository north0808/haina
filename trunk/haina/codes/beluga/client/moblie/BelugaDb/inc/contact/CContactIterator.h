/*
 ============================================================================
 Name		: CContactIterator.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Contact Entity Iterator
 ============================================================================
 */

#ifndef __CCONTACTITERATOR_H__
#define __CCONTACTITERATOR_H__

#include <glib.h>
#include "CDbEntityIterator.h"
#include "CContactDb.h"

class CContactIterator : public CDbEntityIterator
{
public:
	IMPORT_C CContactIterator(CEntityDb * pEntityDb, gboolean bOnlyPref): 
		CDbEntityIterator(pEntityDb), m_bOnlyPref(bOnlyPref)
		{
		m_bAlreadyNext = FALSE;
		m_nSameContactId = (guint32)-1;
		}
	
	IMPORT_C ~CContactIterator()
		{
		}
	
	IMPORT_C gint32 Current(CDbEntity ** pEntity);
	IMPORT_C gint32 Next(gboolean * pSuccess);
	IMPORT_C gint32 Prev(gboolean * pSuccess);
	
private:
	gboolean	m_bAlreadyNext;
	guint32		m_nSameContactId;
	gboolean    m_bOnlyPref;
};

#endif

