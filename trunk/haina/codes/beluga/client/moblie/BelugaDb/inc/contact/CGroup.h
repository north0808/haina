/*
 ============================================================================
 Name		: CGroup.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Group Entity
 ============================================================================
 */

#ifndef __CGROUP_H__
#define __CGROUP_H__


#include "CDbEntity.h"

#define  GROUPID_DEFAULT		(guint32(-1))

enum GroupField
{
    GroupField_Id = 0,
    GroupField_TagId,
    GroupField_Name,
    GroupField_NameSpell,
    GroupField_Logo,
    GroupField_GroupOrder,
    GroupField_DeleteFlag,
    GroupField_GroupType,
    GroupField_EndFlag
};

#define  GROUP_NAME_LEN			64
#define  GROUP_LOGO_LEN			256

class CGroup : public CDbEntity
{
public:
	CGroup(CEntityDb * pEntityDb): CDbEntity(pEntityDb)
	{
	m_pFieldsIndex = g_array_sized_new(FALSE, TRUE, sizeof(guint32), GroupField_EndFlag);
	m_pFieldsValue = g_ptr_array_sized_new(GroupField_EndFlag);
	for (int i=GroupField_Id; i<GroupField_EndFlag; i++)
		{
		m_nFieldsIndex[i] = i;
		g_array_append_val(m_pFieldsIndex, m_nFieldsIndex[i]);
		g_ptr_array_add(m_pFieldsValue, g_string_new(""));
		}
	}
	
	~CGroup()
		{
		
		}

private:
	guint32 m_nFieldsIndex[GroupField_EndFlag];
};

#endif 


