/*
 ============================================================================
 Name		: CQuickMsg.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : QuickMsg Entity
 ============================================================================
 */

#ifndef __CQUICKMSG_H__
#define __CQUICKMSG_H__

enum QuickMsgField
{
    QuickMsgField_Id = 0,
    QuickMsgField_Type,
    QuickMsgField_Content,
    QuickMsgField_EndFlag
};

class CQuickMsg : public CDbEntity
{
public:
	IMPORT_C CQuickMsg(CEntityDb * pEntityDb): CDbEntity(pEntityDb)
	{
	m_pFieldsIndex = g_array_sized_new(FALSE, TRUE, sizeof(guint32), QuickMsgField_EndFlag);
	m_pFieldsValue = g_ptr_array_sized_new(QuickMsgField_EndFlag);
	for (int i=QuickMsgField_Id; i<QuickMsgField_EndFlag; i++)
		{
		m_nFieldsIndex[i] = i;
		g_array_append_val(m_pFieldsIndex, m_nFieldsIndex[i]);
		g_ptr_array_add(m_pFieldsValue, g_string_new(""));
		}
	}
	
	IMPORT_C ~CQuickMsg()
		{
		
		}

private:
	guint32 m_nFieldsIndex[QuickMsgField_EndFlag];
};

#endif
