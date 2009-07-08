/*
 ============================================================================
 Name		: CSignature.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Signature Entity
 ============================================================================
 */

#ifndef __CSIGNATURE_H__
#define __CSIGNATURE_H__

enum SignatureField
{
    SignatureField_Id = 0,
    SignatureField_Content,
    SignatureField_DefaultFlag,
    SignatureField_EndFlag
};

class CSignature : public CDbEntity
{
public:
	CSignature(CEntityDb * pEntityDb): CDbEntity(pEntityDb)
	{
	m_pFieldsIndex = g_array_sized_new(FALSE, TRUE, sizeof(guint32), SignatureField_EndFlag);
	m_pFieldsValue = g_ptr_array_sized_new(SignatureField_EndFlag);
	for (int i=SignatureField_Id; i<SignatureField_EndFlag; i++)
		{
		m_nFieldsIndex[i] = i;
		g_array_append_val(m_pFieldsIndex, m_nFieldsIndex[i]);
		g_ptr_array_add(m_pFieldsValue, g_string_new(""));
		}
	}
	
	~CSignature()
		{
		
		}

private:
	guint32 m_nFieldsIndex[SignatureField_EndFlag];
};

#endif

