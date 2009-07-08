/*
 ============================================================================
 Name		: CMsgFace.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : MsgFace Entity
 ============================================================================
 */

#ifndef __CMSGFACE_H__
#define __CMSGFACE_H__

enum MsgFaceField
{
    MsgFaceField_Id = 0,
    MsgFaceField_Symbol,
    MsgFaceField_Picture,
    MsgFaceField_EndFlag
};

class CMsgFace : public CDbEntity
{
public:
	CMsgFace(CEntityDb * pEntityDb): CDbEntity(pEntityDb)
	{
	m_pFieldsIndex = g_array_sized_new(FALSE, TRUE, sizeof(guint32), MsgFaceField_EndFlag);
	m_pFieldsValue = g_ptr_array_sized_new(MsgFaceField_EndFlag);
	for (int i=MsgFaceField_Id; i<MsgFaceField_EndFlag; i++)
		{
		m_nFieldsIndex[i] = i;
		g_array_append_val(m_pFieldsIndex, m_nFieldsIndex[i]);
		g_ptr_array_add(m_pFieldsValue, g_string_new(""));
		}
	}
	
	~CMsgFace()
		{
		
		}

private:
	guint32 m_nFieldsIndex[MsgFaceField_EndFlag];
};

#endif

