
enum QuickMsgField
{
    QuickMsgField_Id = 0,
    QuickMsgField_Content
}

class CQuickMsg : public CDbEntity
{
public:
    CQuickMsg();
    ~CQuickMsg();

    ECODE getFieldsName(String & fieldsName[]);
    ECODE getFieldsIndex(uint32 & fieldsIndex[]);

    ECODE getFields(uint16 fieldsIndex[], String & fields[]);
    ECODE getField(uint16 fieldIndex, String & field);
    ECODE setFields(uint16 fieldsIndex[], String fields[]);
    ECODE setField(uint16 fieldIndex, String field);
}