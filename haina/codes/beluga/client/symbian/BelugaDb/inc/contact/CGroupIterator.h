/*
 ============================================================================
 Name		: CGroupIterator.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Group Entity Iterator
 ============================================================================
 */

#ifndef __CGROUPITERATOR_H__
#define __CGROUPITERATOR_H__


#include <glib.h>
#include "CEntityIterator.h"
#include "CGroupDb.h"

class CGroupIterator : public CDbEntityIterator
{
public:
	CGroupIterator(CEntityDb * pEntityDb): 
		CDbEntityIterator(pEntityDb)
		{
		}
	
	~CGroupIterator()
		{
		}
	
	IMPORT_C gint32 Current(CDbEntity ** pEntity);
	IMPORT_C gint32 Next(gboolean * pSuccess);
	IMPORT_C gint32 Prev(gboolean * pSuccess);
	
};
#endif
