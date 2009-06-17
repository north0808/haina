#ifndef HESSIAN_H
#define HESSIAN_H
#include <e32std.h>
class CHessian
{
public:
	HBufC8 * sendBytes;
public:
	CHessian(void);
	~CHessian(void);
	void WirteInt(TInt tint);
	void WirteHBufC8(HBufC8* hbufc8);
	void WriteLong(TInt tint);
	void WriteByte(HBufC8* hbufc8);
	void WriteTBuf(TBuf<256> &tbuf);
	void WriteTBuf8(TBuf8<15> &tbuf);
	TInt GetTBufLength(TBuf<256> &tbuf);
	TInt GetTBuf8Length(TBuf8<15> &tbuf);
};
#endif /*HESSIAN_H*/
