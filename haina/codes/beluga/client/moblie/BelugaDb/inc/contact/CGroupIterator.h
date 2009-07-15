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
#include "CDbEntityIterator.h"
#include "CGroupDb.h"

class CGroupIterator : public CDbEntityIterator
{
public:
	IMPORT_C CGroupIterator(CEntityDb * pEntityDb): 
		CDbEntityIterator(pEntityDb)
		{
		}
	
	IMPORT_C ~CGroupIterator()
		{
		}
	
	IMPORT_C gint32 Current(CDbEntity ** pEntity);
	IMPORT_C gint32 Next(gboolean * pSuccess);
	IMPORT_C gint32 Prev(gboolean * pSuccess);
	
};
#endif
