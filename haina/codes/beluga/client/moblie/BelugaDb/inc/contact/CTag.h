/*
 ============================================================================
 Name		: CTag.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Tag Entity
 ============================================================================
 */

#ifndef __CTAG_H__
#define __CTAG_H__

#include "CDbEntity.h"

enum TagField
{
    TagField_Id = 0,
    TagField_Name,
    TagField_NameSpell,
    TagField_Logo,
    TagField_TagOrder,
    TagField_DeleteFlag,
    TagField_EndFlag
};

#define  TAG_NAME_LEN			64
#define  TAG_LOGO_LEN			256


class CTag : public CDbEntity
{
public:
	IMPORT_C CTag(CEntityDb * pEntityDb): CDbEntity(pEntityDb)
	{
	m_pFieldsIndex = g_array_sized_new(FALSE, TRUE, sizeof(guint32), TagField_EndFlag);
	m_pFieldsValue = g_ptr_array_sized_new(TagField_EndFlag);
	for (int i=TagField_Id; i<TagField_EndFlag; i++)
		{
		m_nFieldsIndex[i] = i;
		g_array_append_val(m_pFieldsIndex, m_nFieldsIndex[i]);
		g_ptr_array_add(m_pFieldsValue, g_string_new(""));
		}
	}
	
	IMPORT_C ~CTag()
		{
		
		}

private:
	guint32 m_nFieldsIndex[TagField_EndFlag];
};

#endif

