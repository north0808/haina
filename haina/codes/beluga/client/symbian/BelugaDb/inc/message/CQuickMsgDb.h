
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
}

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
}

struct SRecentContact
{
    uint32 nContactId;
    EContactEvent eContactEvent;
    String CommInfo;
    TIME time;
}


class CContactDb : public CEntityDb
{
public:
    CContactDb();
    ~CContactDb();

    ECODE setSortKey(uint32 fieldIndex, bool ascending);

    /* contact module methods */
    ECODE getMaxContactId(uint32 * pMaxContactId);
    ECODE getMaxGroupId(uint32 * pMaxGroupId);
    ECODE getMaxTagId(uint32 * pMaxTagId);

    ECODE getContactById(uint32 nContactId, CContact** ppContact);
    ECODE getGroupById(uint32 nGroupId, CGroup** ppGroup);
    ECODE getTagById(uint32 nTagId, CTag** ppTag);

    ECODE createContact(CContact** ppContact);
    ECODE createGroup(CGroup** ppGroup);
    ECODE createTag(CTag** ppTag);

    ECODE saveContact(CContact * pContact);
    ECODE saveGroup(CGroup * pGroup);
    ECODE saveTag(CGroup * pTag);

    ECODE deleteContact(CContact * pContact);
    ECODE deleteContact(uint32 nContactId);
    ECODE deleteGroup(CGroup* pGroup);
    ECODE deleteGroup(uint32 nGroupId);
    ECODE deleteTag(CTag* pTag);
    ECODE deleteTag(uint32 nTagId);

    ECODE deleteAllContactsByTag(uint32 nTagId);
    ECODE deleteAllGroupsByTag(uint32 nTagId);
    ECODE deleteAllTags();

    ECODE releaseContactGroupRelation(CContact * pContact, CGroup * pGroup);
    ECODE releaseContactGroupRelation(uint32 nContactId, uint32 nGroupId);
    ECODE releaseContactAllRelations(uint32 nContactId);
    ECODE releaseContactAllRelations(CContact * pContact);
    ECODE releaseGroupAllRelations(uint32 nGroupId);
    ECODE releaseGroupAllRelations(CGroup * pGroup);

    ECODE createContactGroupRelation(CContact * pContact, CGroup * pGroup);
    ECODE createContactGroupRelation(uint32 nContactId, uint32 nGroupId);

    ECODE updateContact(CContact * pContact);
    ECODE updateGroup(CGroup * pGroup);
    ECODE updateTag(CTag * pTag);

    ECODE searchTags(uint32 fieldsIndex[], String fieldsValue[], CTagIterator ** ppTagIterator);
    ECODE searchContactsByTag(uint32 nTagId, uint32 fieldsIndex[], String fieldsValue[], bool onlyPref, CContactIterator ** ppContactIterator);
    ECODE searchGroupsByTag(uint32 nTagId, uint32 fieldsIndex[], String fieldsValue[], CGroupIterator ** ppGroupIterator);

    ECODE getMostMatchingContactByTag(uint32 nTagId, uint32 fieldsIndex[], String fieldsValue[], bool onlyPref, CContact ** ppContact);
    ECODE getMostMatchingContactByTag(uint32 nTagId, uint32 fieldsIndex[], String fieldsValue[], uint32 & nContactId);

    ECODE getAllTags(CTagIterator ** ppTagIterator);
    ECODE getAllContactsByTag(uint32 nTagId, bool onlyPref, CContactIterator ** ppContactIterator);
    ECODE getAllContactsByGroup(uint32 nGroupId, bool onlyPref, CContactIterator ** ppContactIterator);
    ECODE getAllGroupsByTag(uint32 nTagId, CGroupIterator ** ppGroupIterator);

    ECODE getContactsTotalityByTag(uint32 nTagId, uint32 &totality);
    ECODE getContactsTotalityByGroup(uint32 nGroupId, uint32 &totality);
    ECODE getGroupsTotalityByTag(uint32 nTagId, uint32 &totality);
    ECODE getTagsTotality(uint32 &totality);

    EOCDE isGroupNameConflict(bool & bConflict);
    EOCDE isTagNameConflict(bool & bConflict);

    ECODE getPhoneDistrict(String phoneNumber, uint32 &districtNumber, String &districtName, uint32 &feeType);

    ECODE getRecentContacts(SRecentContact &contact[]);
    ECODE saveRecentContact(SRecentContact contact);

    /* message module methods */
    ECODE getMaxMsgId(uint32 * pMaxMsgId);
    ECODE getMaxQuickMsgId(uint32 * pMaxQuickMsgId);
    ECODE getMaxSignatureId(uint32 * pMaxSigId);
    ECODE getMaxMsgFaceId(uint32 * pMaxFaceId);

    ECODE getMsgById(uint32 nMsgId, CMsg** ppMsg);
    ECODE getQuickMsgById(uint32 nQuickMsgId, CQuickMsg** ppMsg);
    ECODE getSignatureById(uint32 nSignatureId, CSignature** ppSig);
    ECODE getMsgFaceById(uint32 nMsgFaceId, CMsgFace** ppFace);

    ECODE createMsg(CMsg** ppMsg);
    ECODE createQuickMsg(CQuickMsg** ppMsg);
    ECODE createSignature(CSignature** ppSig);
    ECODE createMsgFace(CMsgFace** ppFace);

    ECODE saveMsg(CMsg * ppMsg);
    ECODE saveQuickMsg(CQuickMsg * pMsg);
    ECODE saveSignature(CSignature * pSig);
    ECODE saveMsgFace(CMsgFace * pFace);

    ECODE updateMsg(CMsg * pMsg);
    ECODE updateQuickMsg(CQuickMsg * pMsg);
    ECODE updateSignature(CSignature * pSig);
    ECODE updateMsgFace(CMsgFace * pFace);

    ECODE deleteMsg(CMsg * pMsg);
    ECODE deleteMsg(uint32 nMsgId);
    ECODE deleteQuickMsg(CQuickMsg * pMsg);
    ECODE deleteQuickMsg(uint32 nMsgId);
    ECODE deleteSignature(CSignature * pSig);
    ECODE deleteSignature(uint32 nSigId);
    ECODE deleteMsgFace(CMsgFace * pFace);
    ECODE deleteMsgFace(uint32 nFaceId);

    ECODE deleteAllMsgsByContact(uint32 nContactId); /* delete all msgs sent to and received from a contact */
    ECODE deleteAllMsgs();

    ECODE getMsgsTotality(uint32 &totality);
    ECODE getMsgsTotalityByContact(uint32 nContactId, uint32 &totality);
    ECODE getMsgsTotalityByContact(uint32 nContactId, TIME time, uint32 &totality);  /* msgs totality from the time on */
    ECODE getMsgsTotalityByGroup(uint32 nGroupId, uint32 &totality);
    ECODE getMsgFacesTotality(uint32 &totality);
    ECODE getSignaturesTotality(uint32 &totality);
    ECODE getQuickMsgsTotality(uint32 &totality);


    ECODE searchMsgs(uint32 fieldsIndex[], String fieldsValue[], uint32 limit, uint32 offset, CMsgIterator ** ppMsgIterator);
    ECODE searchMsgsByContact(uint32 nContactId,  uint32 limit, uint32 offset, CMsgIterator ** ppMsgIterator); /* search msgs sent to and received from a contact */
    ECODE searchMsgsByContact(uint32 nContactId, TIME time, uint32 limit, uint32 offset, CMsgIterator ** ppMsgIterator); /* search msgs sent to and received from a contact base on time */
    ECODE searchMsgsByGroup(uint32 nGroupId, uint32 limit, uint32 offset, CMsgIterator ** ppMsgIterator); /* search msgs sent to and received from a group */
    ECODE searchMsgsByGroup(uint32 nGroupId, TIME time, uint32 limit, uint32 offset, CMsgIterator ** ppMsgIterator); /* search msgs sent to and received from a group base on time */

private:
    ECODE getMaxContactCommId(uint32 * pMaxCommId);
    ECODE getMaxMsgContentId(uint32 * pMaxContentId);
};