/*
 ============================================================================
 Name		: CConfigIterator.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Config Entity Iterator
 ============================================================================
 */

#ifndef __CCONFIGITERATOR_H__
#define __CCONFIGITERATOR_H__

#include <glib.h>
#include "CDbEntityIterator.h"
#include "CConfigDb.h"

class CConfigIterator : public CDbEntityIterator
{
public:
	IMPORT_C CConfigIterator(CEntityDb * pEntityDb): 
		CDbEntityIterator(pEntityDb)
		{
		}
	
	IMPORT_C ~CConfigIterator()
		{
		}
	
	IMPORT_C gint32 Current(CDbEntity ** pEntity);
	IMPORT_C gint32 Next(gboolean * pSuccess);
	IMPORT_C gint32 Prev(gboolean * pSuccess);
};	

#endif
