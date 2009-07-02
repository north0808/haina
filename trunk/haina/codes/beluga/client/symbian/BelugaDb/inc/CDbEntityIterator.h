/*
 ============================================================================
 Name		: CDbEntityIterator.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Database Entity Iterator
 ============================================================================
 */

#ifndef __CDBENTITYITERATOR_H__
#define __CDBENTITYITERATOR_H__

#include "CDbEntity.h"
#include "CppSQLite3.h"

class CDbEntityIterator
{
public:
	CDbEntityIterator(CEntityDb * pEntityDb): 
		m_pEntityDb(pEntityDb)
		{
		m_pEntityDb->OpenDatabase();
		m_dbBeluga = pEntityDb->GetDatabase();
		m_dbQuery = pEntityDb->GetDbQuery();
		}
	
	~CDbEntityIterator()
		{
		m_pEntityDb->CloseDatabase();
		}
	
    virtual gint32 Current(CDbEntity ** pEntity) = 0;
    virtual gint32 Next(gboolean * pSuccess) = 0;
    virtual gint32 Prev(gboolean * pSuccess) = 0;
    
protected:
	CEntityDb 		* m_pEntityDb;
	CppSQLite3DB 	* m_dbBeluga;
	CppSQLite3Query * m_dbQuery;
};

#endif
