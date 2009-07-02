
enum SignatureField
{
    SignatureField_Id = 0,
    SignatureField_Content,
    SignatureField_DefaultFlag
}

class CSignature : public CDbEntity
{
    public:
        CSignature();
        ~CSignature();

        ECODE getFieldsName(String & fieldsName[]);
        ECODE getFieldsIndex(uint32 & fieldsIndex[]);

        ECODE getFields(uint16 fieldsIndex[], String & fields[]);
        ECODE getField(uint16 fieldIndex, String & field);
        ECODE setFields(uint16 fieldsIndex[], String fields[]);
        ECODE setField(uint16 fieldIndex, String field);

}