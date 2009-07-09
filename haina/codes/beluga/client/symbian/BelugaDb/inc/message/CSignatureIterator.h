/*
 ============================================================================
 Name		: CSignatureIterator.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Signature Entity Iterator
 ============================================================================
 */

#ifndef __CSIGNATUREITERATOR_H__
#define __CSIGNATUREITERATOR_H__


#include <glib.h>
#include "CDbEntityIterator.h"
#include "CSignatureDb.h"

class CSignatureIterator : public CDbEntityIterator
{
public:
	CSignatureIterator(CEntityDb * pEntityDb): 
		CDbEntityIterator(pEntityDb)
		{
		}
	
	~CSignatureIterator()
		{
		}
	/*
	IMPORT_C gint32 Current(CDbEntity ** pEntity);
	IMPORT_C gint32 Next(gboolean * pSuccess);
	IMPORT_C gint32 Prev(gboolean * pSuccess);*/
};	

#endif
