/*
 ============================================================================
 Name		: CIMContact.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : IM Contact Entity
 ============================================================================
 */

#ifndef __CIMCONTACT_H__
#define __CIMCONTACT_H__


class CIMContact : public CContact
{
public:
    
	IMPORT_C CIMContact(CEntityDb * pEntityDb) :
    CContact(pEntityDb)
		{
		}
    
    IMPORT_C ~CIMContact()
    	{ 	
    	}
};

#endif
