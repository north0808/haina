/*
 ============================================================================
 Name		: CMsgDb.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Msg Database
 ============================================================================
 */

#ifndef __CMSGDB_H__
#define __CMSGDB_H__



/*
class CContactDb : public CEntityDb
{
public:
    CContactDb();
    ~CContactDb();


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
*/
	/* delete all msgs sent to and received from a contact */
/*    ECODE deleteAllMsgsByContact(uint32 nContactId); 
    ECODE deleteAllMsgs();

    ECODE getMsgsTotality(uint32 &totality);
    ECODE getMsgsTotalityByContact(uint32 nContactId, uint32 &totality); */
    /* msgs totality from the time on */
/*    ECODE getMsgsTotalityByContact(uint32 nContactId, TIME time, uint32 &totality);  
    ECODE getMsgsTotalityByGroup(uint32 nGroupId, uint32 &totality);
    ECODE getMsgFacesTotality(uint32 &totality);
    ECODE getSignaturesTotality(uint32 &totality);
    ECODE getQuickMsgsTotality(uint32 &totality);


    ECODE searchMsgs(uint32 fieldsIndex[], String fieldsValue[], uint32 limit, uint32 offset, CMsgIterator ** ppMsgIterator); */
	/* search msgs sent to and received from a contact */
/*    ECODE searchMsgsByContact(uint32 nContactId,  uint32 limit, uint32 offset, CMsgIterator ** ppMsgIterator); 
 * 
 */	
	/* search msgs sent to and received from a contact base on time */
//    ECODE searchMsgsByContact(uint32 nContactId, TIME time, uint32 limit, uint32 offset, CMsgIterator ** ppMsgIterator);
    /* search msgs sent to and received from a group */
//    ECODE searchMsgsByGroup(uint32 nGroupId, uint32 limit, uint32 offset, CMsgIterator ** ppMsgIterator); 
    /* search msgs sent to and received from a group base on time */
//    ECODE searchMsgsByGroup(uint32 nGroupId, TIME time, uint32 limit, uint32 offset, CMsgIterator ** ppMsgIterator); 
/*
private:
    ECODE getMaxMsgContentId(uint32 * pMaxContentId);
};

*/
#endif

