
enum ConfigField
{
    ConfigField_Id = 0,
    ConfigField_Name,
    ConfigField_Key,
    ConfigField_Value
}

class CConfig : public CDbEntity
{
    public:
        CConfig();
        ~CConfig();

        ECODE getFieldsName(String & fieldsName[]);
        ECODE getFieldsIndex(uint32 & fieldsIndex[]);

        ECODE getFields(uint16 fieldsIndex[], String & fields[]);
        ECODE getField(uint16 fieldIndex, String & field);
        ECODE setFields(uint16 fieldsIndex[], String fields[]);
        ECODE setField(uint16 fieldIndex, String field);
};