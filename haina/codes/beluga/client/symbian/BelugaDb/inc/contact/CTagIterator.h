/*
 ============================================================================
 Name		: CTagIterator.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Tag Entity Iterator
 ============================================================================
 */

#ifndef __CTAGITERATOR_H__
#define __CTAGITERATOR_H__


#include <glib.h>
#include "CDbEntityIterator.h"
#include "CTagDb.h"

class CTagIterator : public CDbEntityIterator
{
public:
	CTagIterator(CEntityDb * pEntityDb): 
		CDbEntityIterator(pEntityDb)
		{
		}
	
	~CTagIterator()
		{
		}
	
	IMPORT_C gint32 Current(CDbEntity ** pEntity);
	IMPORT_C gint32 Next(gboolean * pSuccess);
	IMPORT_C gint32 Prev(gboolean * pSuccess);
	
};
#endif
