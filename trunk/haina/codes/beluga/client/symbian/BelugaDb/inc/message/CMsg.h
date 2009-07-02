
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
    MsgField_Bcc
}

class CMsg : public CDbEntity
{
public:
    CMsg();
    ~CMsg();

    ECODE getFieldsName(String & fieldsName[]);
    ECODE getFieldsIndex(uint32 & fieldsIndex[]);

    ECODE getFields(uint16 fieldsIndex[], String & fields[]);
    ECODE getField(uint16 fieldIndex, String & field);
    ECODE setFields(uint16 fieldsIndex[], String fields[]);
    ECODE setField(uint16 fieldIndex, String field);

    ECODE setMsgText(String textMsg);
    ECODE setMsgAttachment(String name, String postfix);

    ECODE getMsgText(String &textMsg);
    ECODE getMsgAttachment(String path);  /* save attachment file to the path */


}