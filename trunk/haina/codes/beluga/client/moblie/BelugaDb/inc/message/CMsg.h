/*
 ============================================================================
 Name		: CMsg.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Msg Entity
 ============================================================================
 */

#ifndef __CMSG_H__
#define __CMSG_H__

enum MsgField
{
    MsgField_Id = 0,
    MsgField_Type,
    MsgField_Status,
    MsgField_Time,
    MsgField_From,
    MsgField_To,
    MsgField_GroupId,
    MsgField_ContentId,
    MsgField_Subject,
    MsgField_Cc,
    MsgField_Bcc,
    MsgField_EndFlag
};


class CMsg : public CDbEntity
{
public:
	IMPORT_C CMsg(CEntityDb * pEntityDb): CDbEntity(pEntityDb)
	{
	m_pFieldsIndex = g_array_sized_new(FALSE, TRUE, sizeof(guint32), MsgField_EndFlag);
	m_pFieldsValue = g_ptr_array_sized_new(MsgField_EndFlag);
	for (int i=MsgField_Id; i<MsgField_EndFlag; i++)
		{
		m_nFieldsIndex[i] = i;
		g_array_append_val(m_pFieldsIndex, m_nFieldsIndex[i]);
		g_ptr_array_add(m_pFieldsValue, g_string_new(""));
		}
	}
	
	IMPORT_C ~CMsg()
		{
		
		}
/*
    IMPORT_C gint32 SetText(gchar * textMsg);
    IMPORT_C gint32 SetAttachment(gchar * attachName, gchar * postfix);

    IMPORT_C gint32 GetText(String &textMsg);
    IMPORT_C gint32 GetAttachment(String path);  /* save attachment file to the path */
*/    
private:
	guint32 m_nFieldsIndex[MsgField_EndFlag];
};

#endif

