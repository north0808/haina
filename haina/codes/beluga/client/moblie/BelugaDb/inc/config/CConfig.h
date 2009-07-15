/*
 ============================================================================
 Name		: CConfig.h
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : Config Entity
 ============================================================================
 */

#ifndef __CCONFIG_H__
#define __CCONFIG_H__

enum ConfigField
{
    ConfigField_Id = 0,
    ConfigField_Name,
    ConfigField_Key,
    ConfigField_Value,
    ConfigField_EndFlag
};

#define  CONFIG_NAME_LEN	64


class CConfig : public CDbEntity
{
public:
	IMPORT_C CConfig(CEntityDb * pEntityDb): CDbEntity(pEntityDb)
	{
	m_pFieldsIndex = g_array_sized_new(FALSE, TRUE, sizeof(guint32), ConfigField_EndFlag);
	m_pFieldsValue = g_ptr_array_sized_new(ConfigField_EndFlag);
	for (int i=ConfigField_Id; i<ConfigField_EndFlag; i++)
		{
		m_nFieldsIndex[i] = i;
		g_array_append_val(m_pFieldsIndex, m_nFieldsIndex[i]);
		g_ptr_array_add(m_pFieldsValue, g_string_new(""));
		}
	}
	
	IMPORT_C ~CConfig()
		{
		
		}

private:
	guint32 m_nFieldsIndex[ConfigField_EndFlag];
};

#endif
