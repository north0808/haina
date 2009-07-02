
class CMsgIterator : public IDbEntityIterator
{
public:
    ECODE init(IDBResultSet * pResultSet);
    ECODE reset();
    ECODE current(CDbEntity ** pEntity);
    ECODE next(bool * pSuccess);
    ECODE prev(bool * pSuccess);
}