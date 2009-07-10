/*
 ============================================================================
 Name		: CContactDb.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Contact Database
 ============================================================================
 */

#ifndef __CCONTACTDB_H__
#define __CCONTACTDB_H__

#include <time.h>
#include "CEntityDb.h"
#include "CContact.h"
#include "CContactIterator.h"


enum EContactEvent
{
    ContactEvent_SMS = 1,
    ContactEvent_Email,
    ContactEvent_MMS,
    ContactEvent_AnsweredCall,
    ContactEvent_RejectedCall,
    ContactEvent_OutgoCall,
    ContactEvent_QQ,
    ContactEvent_MSN
};

enum ECommType
{
    CommType_Phone = 0x10,
    CommType_Email = 0x20,
    CommType_Address = 0x30,
    CommType_IM = 0x40,

    CommType_Home = 0x01,
    CommType_Work = 0x02,
    CommType_Other = 0x03,
    CommType_Pref = 0x04,
    CommType_QQ = 0x05,
    CommType_MSN = 0x06,
};

struct stRecentContact
{
    guint32 nContactId;
    EContactEvent event;
    gchar eventCommInfo[256];
    tm * time;
};

#define 	MAX_RECENT_CONTACT_NUM		15



class CGroup;
class CContactIterator;
class CPhoneContact;

class CContactDb : public CEntityDb
{
public:
    CContactDb();
    ~CContactDb();
    
    IMPORT_C gint32 InitEntityDb();
    IMPORT_C gint32 GetMaxId(guint32 * nMaxId);
    IMPORT_C gint32 GetEntityById(guint32 nId, CDbEntity** ppEntity);

    IMPORT_C gint32 SaveEntity(CDbEntity * pEntity);
    IMPORT_C gint32 DeleteEntity(guint32 nEntityId);
    IMPORT_C gint32 UpdateEntity(CDbEntity * pEntity);
    
    IMPORT_C gint32 DeleteAllContactsByTag(guint32 nTagId);  /* delete all contacts but groups */

    IMPORT_C gint32 ReleaseContactGroupRelation(CContact * pContact, CGroup * pGroup);
    IMPORT_C gint32 ReleaseContactGroupRelation(guint32 nContactId, guint32 nGroupId);
    IMPORT_C gint32 ReleaseContactAllRelations(guint32 nContactId);
    IMPORT_C gint32 ReleaseContactAllRelations(CContact * pContact);

    IMPORT_C gint32 CreateContactGroupRelation(CContact * pContact, CGroup * pGroup);
    IMPORT_C gint32 CreateContactGroupRelation(guint32 nContactId, guint32 nGroupId);

    IMPORT_C gint32 SearchContactsByTag(guint32 nTagId, GArray * fieldsIndex, GPtrArray * fieldsValue, 
								gboolean onlyPref, CContactIterator ** ppContactIterator);
    IMPORT_C gint32 SearchPhoneContactsByPhoneOrEmail(gchar * commValue, 
								gboolean onlyPref, CContactIterator ** ppContactIterator);
    IMPORT_C gint32 SearchContactsByName(guint32 nTagId, gchar* name, gboolean onlyPref, 
											CContactIterator ** ppContactIterator);
//    IMPORT_C gint32 SearchContactsByWordsFirstLetter(guint32 nTagId, gchar* nameLetters, gboolean onlyPref, 
//    											CContactIterator ** ppContactIterator);
        
//    IMPORT_C gint32 GetMostMatchingContactByTag(guint32 nTagId, GArray * fieldsIndex, GPtrArray * fieldsValue, 
//										gboolean onlyPref, CContact ** ppContact);
    IMPORT_C gint32 GetMostMatchingPhoneContactByPhoneOrEmail(gchar * commValue, CContact ** ppContact);
        
    IMPORT_C gint32 GetAllContactsByTag(guint32 nTagId, gboolean onlyPref, CContactIterator ** ppContactIterator);
    IMPORT_C gint32 GetAllContactsNotInGroupByTag(guint32 nTagId, gboolean onlyPref, CContactIterator ** ppContactIterator);
    IMPORT_C gint32 GetAllContactsByGroup(guint32 nGroupId, gboolean onlyPref, CContactIterator ** ppContactIterator);
    
    IMPORT_C gint32 GetContactsTotalityByTag(guint32 nTagId, guint32 * totality);
    IMPORT_C gint32 GetContactsTotalityByGroup(guint32 nGroupId, guint32 * totality);

    IMPORT_C gint32 GetPhoneDistrict(gchar* phoneNumber, gchar ** districtNumber, gchar ** districtName, gchar ** feeType);
 
    IMPORT_C gint32 GetRecentContacts(GPtrArray ** pContacts);
    IMPORT_C gint32 SaveRecentContact(stRecentContact * contact);

    gint32 GetContactCommInfo(CPhoneContact * pContact); /* must create a hashtable and a ptrarray */
private:
	gint32 GetMaxContactCommId(guint32 * pMaxCommId);
	gboolean CheckGroupInTag(guint32 nGroupId, guint32 nTagId);
	
private:
	gint32 m_nDbErrCode;

};

#endif
