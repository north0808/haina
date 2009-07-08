/*
 ============================================================================
 Name		: CMsgFaceIterator.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : MsgFace Entity Iterator
 ============================================================================
 */

#ifndef __CMSGFACEITERATOR_H__
#define __CMSGFACEITERATOR_H__

#include <glib.h>
#include "CEntityIterator.h"
#include "CMsgFaceDb.h"

class CMsgFaceIterator : public CDbEntityIterator
{
public:
	CMsgFaceIterator(CEntityDb * pEntityDb): 
		CDbEntityIterator(pEntityDb)
		{
		}
	
	~CMsgFaceIterator()
		{
		}
/*	
	IMPORT_C gint32 Current(CDbEntity ** pEntity);
	IMPORT_C gint32 Next(gboolean * pSuccess);
	IMPORT_C gint32 Prev(gboolean * pSuccess);*/
};	

#endif
