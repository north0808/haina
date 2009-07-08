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
#include "CEntityIterator.h"
#include "CContactDb.h"

class CContactIterator : public CDbEntityIterator
{
public:
	CContactIterator(CEntityDb * pEntityDb): 
		CDbEntityIterator(pEntityDb)
		{
		m_bAlreadyNext = FALSE;
		m_nSameContactId = (guint32)-1;
		}
	
	~CContactIterator()
		{
		}
	
	IMPORT_C gint32 Current(CDbEntity ** pEntity);
	IMPORT_C gint32 Next(gboolean * pSuccess);
	IMPORT_C gint32 Prev(gboolean * pSuccess);
	
private:
	gboolean	m_bAlreadyNext;
	guint32		m_nSameContactId;
};

#endif

