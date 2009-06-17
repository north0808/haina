#include "Hessian.h"
#include <e32std.h>
#include <e32base.h>

CCHessian::CCHessian(void)
{
	sendBytes=HBufC8::NewLC(1);

}

TInt CCHessian::GetTBuf8Length(TBuf8<15> &tbuf)
{
		TInt aTbufLength=0;
		TInt length=tbuf.Length();
		aTbufLength=length+3;
		return aTbufLength;
}
TInt CCHessian::GetTBufLength(TBuf<256> &tbuf)
{
		TInt aTbufLength=0;
		TInt length=tbuf.Length();
		aTbufLength+=3;
		for(TInt i=0;i<length;i++)
		{
			if (tbuf[i] < 0x80){
				aTbufLength+=1;
			}else if (tbuf[i] < 0x800) {
				aTbufLength+=2;
			}else {
				aTbufLength+=3;
			}
		}
		return aTbufLength;
}
CCHessian::~CCHessian(void)
{
	CleanupStack::Pop();
	delete sendBytes;
}

void CCHessian::WriteByte(HBufC8* hbufc8)//3+length
{
	TInt length=hbufc8->Length();
	if(length==0)
	{
		sendBytes=sendBytes->ReAllocL(sendBytes->Length()+1);
		sendBytes->Des().Append(TChar('N'));
	}else
	{
		sendBytes=sendBytes->ReAllocL(sendBytes->Length()+3+length);
		sendBytes->Des().Append(TChar('B'));
		TInt8 b32 = length >> 24;
		TInt8 b24 = (length >> 16);//& 0x000000FF;
		TInt8 b16 = (length >> 8);//& 0x000000FF;
		TInt8 b8 = length;//& 0x000000FF;
		sendBytes->Des().Append(TChar(b16));
		sendBytes->Des().Append(TChar(b8));
		sendBytes->Des().Append(*hbufc8);
	}


}
void CCHessian::WirteInt(TInt tint)//5
{
		TInt8 b32 = tint >> 24;
		TInt8 b24 = (tint >> 16);// & 0x000000FF;
		TInt8 b16 = (tint >> 8);// & 0x000000FF;
		TInt8 b8 = tint;// & 0x000000FF;
	if(sendBytes->Length()==1){
		sendBytes=sendBytes->ReAllocL(5);
	}else
	{
		sendBytes=sendBytes->ReAllocL(sendBytes->Length()+5);
	}
		sendBytes->Des().Append(TChar('I'));
		/*
		TBuf8<1> tb32;
		tb32.Format(_L8("%d"),b32);
		TBuf8<1> tb24;
		tb24.Format(_L8("%d"),b24);
		TBuf8<1> tb16;
		tb16.Format(_L8("%d"),b16);
		TBuf8<1> tb8;
		tb8.Format(_L8("%d"),b8);
		*/
		sendBytes->Des().Append(TChar(b32));
		sendBytes->Des().Append(TChar(b24));
		sendBytes->Des().Append(TChar(b16));
		sendBytes->Des().Append(TChar(b8));
}

void CCHessian::WirteHBufC8(HBufC8* hbufc8)//
{
	if(hbufc8->Length()==0)
	{
		sendBytes=sendBytes->ReAllocL(sendBytes->Length()+1);
		sendBytes->Des().Append(TChar(TChar('N')));
	}else
	{
		TInt length=hbufc8->Length();
		sendBytes=sendBytes->ReAllocL(sendBytes->Length()+3);
		TInt8 b32 = length >> 24;
		TInt8 b24 = (length >> 16);//& 0x000000FF;
		TInt8 b16 = (length >> 8);// & 0x000000FF;
		TInt8 b8 = length;// & 0x000000FF;
/*
		TBuf8<1> tb32;
		tb32.Format(_L8("%d"),b32);
		TBuf8<1> tb24;
		tb24.Format(_L8("%d"),b24);
		TBuf8<1> tb16;
		tb16.Format(_L8("%d"),b16);
		TBuf8<1> tb8;
		tb8.Format(_L8("%d"),b8);
*/
		sendBytes->Des().Append(TChar('B'));
		sendBytes->Des().Append(TChar(b16));
		sendBytes->Des().Append(TChar(b8));
		sendBytes=sendBytes->ReAllocL(sendBytes->Length()+hbufc8->Length());
		sendBytes->Des().Append(*hbufc8);
		
	}

}


void CCHessian::WriteLong(TInt tint)
	{

		TInt64 i64=TInt64(tint);
				
		TInt8 b64 = (tint >> 56);//& 0x00000000000000FF;
		TInt8 b56 = (tint >> 48);//& 0x00000000000000FF;
		TInt8 b48 = (tint >> 40);//& 0x00000000000000FF;
		TInt8 b40 = (tint >> 32);//& 0x00000000000000FF;
		TInt8 b32 = (tint >> 24);//& 0x00000000000000FF;
		TInt8 b24 = (tint >> 16);//& 0x00000000000000FF;
		TInt8 b16 = (tint >> 8);//& 0x00000000000000FF;
		TInt8 b8 = tint;//t& 0x00000000000000FF;
		
		sendBytes=sendBytes->ReAllocL(sendBytes->Length()+9);
		sendBytes->Des().Append('L');
		sendBytes->Des().Append(TChar(b64));
		sendBytes->Des().Append(TChar(b56));
		sendBytes->Des().Append(TChar(b48));
		sendBytes->Des().Append(TChar(b40));
		sendBytes->Des().Append(TChar(b32));
		sendBytes->Des().Append(TChar(b24));
		sendBytes->Des().Append(TChar(b16));
		sendBytes->Des().Append(TChar(b8));
		//WirteInt(tint);
	}

	void CCHessian::WriteTBuf(TBuf<256> &tbuf)
	{
	if(tbuf.Length()==0)
	{
		sendBytes=sendBytes->ReAllocL(sendBytes->Length()+1);
		sendBytes->Des().Append(TChar('N'));
	}else
	{
		
		TInt length=tbuf.Length();
		/*for(TInt i=0;i<tbuf.Length();i++)
		{
			//TChar ch =TChar(tbuf[i]);
			if (tbuf[i] < 0x80){
				length+=1;
			}else if (tbuf[i] < 0x800) {
				length+=2;
			}else {
				length+=3;
			}
		}
		*/
		sendBytes=sendBytes->ReAllocL(sendBytes->Length()+3);
		TInt8 b32 = length >> 24;
		TInt8 b24 = (length >> 16);//& 0x000000FF;
		TInt8 b16 = (length >> 8);// & 0x000000FF;
		TInt8 b8 = length;// & 0x000000FF;

		sendBytes->Des().Append(TChar('S'));
		sendBytes->Des().Append(TChar(b16));
		sendBytes->Des().Append(TChar(b8));
		//sendBytes=sendBytes->ReAllocL(sendBytes->Length()+length);
		for(TInt i=0;i<tbuf.Length();i++)
		{
			if (tbuf[i] < 0x80){
				sendBytes=sendBytes->ReAllocL(sendBytes->Length()+1);
				sendBytes->Des().Append(TChar(tbuf[i]));
			}else if (tbuf[i] < 0x800) {
				sendBytes=sendBytes->ReAllocL(sendBytes->Length()+2);
				sendBytes->Des().Append(TChar(0xc0 + ((tbuf[i] >> 6) & 0x1f)));
				sendBytes->Des().Append(TChar(0x80 + (tbuf[i] & 0x3f)));
			}else {
				sendBytes=sendBytes->ReAllocL(sendBytes->Length()+3);
				sendBytes->Des().Append(TChar(0xe0 + ((tbuf[i] >> 12) & 0xf)));
				sendBytes->Des().Append(TChar(0x80 + ((tbuf[i] >> 6) & 0x3f)));
				sendBytes->Des().Append(TChar(0x80 + (tbuf[i] & 0x3f)));
			}
		}
		
	}

	}


	void CCHessian::WriteTBuf8(TBuf8<15> &tbuf)
	{
	if(tbuf.Length()==0)
	{
		sendBytes=sendBytes->ReAllocL(sendBytes->Length()+1);
		sendBytes->Des().Append(TChar('N'));
	}else
	{
		
		TInt length=tbuf.Length();
		/*for(TInt i=0;i<tbuf.Length();i++)
		{
			//TChar ch =TChar(tbuf[i]);
			if (tbuf[i] < 0x80){
				length+=1;
			}else if (tbuf[i] < 0x800) {
				length+=2;
			}else {
				length+=3;
			}
		}
		*/
		sendBytes=sendBytes->ReAllocL(sendBytes->Length()+3+length);
		TInt8 b32 = length >> 24;
		TInt8 b24 = (length >> 16);//& 0x000000FF;
		TInt8 b16 = (length >> 8);// & 0x000000FF;
		TInt8 b8 = length;// & 0x000000FF;

		sendBytes->Des().Append(TChar('S'));
		sendBytes->Des().Append(TChar(b16));
		sendBytes->Des().Append(TChar(b8));
		sendBytes->Des().Append(tbuf);
	}
		
	
	}