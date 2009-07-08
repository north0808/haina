/*
 ============================================================================
 Name		: CQuickMsgIterator.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : QuickMsg Entity Iterator
 ============================================================================
 */

#ifndef __CTAG_H__
#define __CTAG_H__

#include <glib.h>
#include "CEntityIterator.h"
#include "CQuickMsgDb.h"

class CQuickMsgIterator : public CDbEntityIterator
{
public:
	CQuickMsgIterator(CEntityDb * pEntityDb): 
		CDbEntityIterator(pEntityDb)
		{
		}
	
	~CQuickMsgIterator()
		{
		}
/*	
	IMPORT_C gint32 Current(CDbEntity ** pEntity);
	IMPORT_C gint32 Next(gboolean * pSuccess);
	IMPORT_C gint32 Prev(gboolean * pSuccess);*/
};	

#endif
