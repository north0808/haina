
enum MsgFaceField
{
    MsgFaceField_Id = 0,
    MsgFaceField_Symbol,
    MsgFaceField_Picture
}

class CMsgFace : public CDbEntity
{
public:
    CMsgFace();
    ~CMsgFace();

    ECODE getFieldsName(String & fieldsName[]);
    ECODE getFieldsIndex(uint32 & fieldsIndex[]);

    ECODE getFields(uint16 fieldsIndex[], String & fields[]);
    ECODE getField(uint16 fieldIndex, String & field);
    ECODE setFields(uint16 fieldsIndex[], String fields[]);
    ECODE setField(uint16 fieldIndex, String field);
}