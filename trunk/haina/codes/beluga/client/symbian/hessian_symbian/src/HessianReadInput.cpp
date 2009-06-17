/*
============================================================================
 Name        : HessianReadInput.cpp
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : CHessianReadInput implementation
============================================================================
*/

#include "HessianReadInput.h"
#include <s32strm.h>
#include <s32buf.h>
#include <s32mem.h>
#include <e32std.h>
CHessianReadInput::CHessianReadInput()
{
	// No implementation required
}


CHessianReadInput::~CHessianReadInput()
{
	
	iInputStream->Close();
	//CleanupStack::PopAndDestroy(iInputStream);
	delete iInputStream;
	//iInputStream->Pop();

}

CHessianReadInput* CHessianReadInput::NewLC(HBufC8 * acontent)
{
	CHessianReadInput* self = new (ELeave)CHessianReadInput();
	CleanupStack::PushL(self);
	self->ConstructL(acontent);
	return self;
}

CHessianReadInput* CHessianReadInput::NewL(HBufC8 * acontent)
{
	CHessianReadInput* self=CHessianReadInput::NewLC(acontent);
	CleanupStack::Pop(); // self;
	return self;
}

void CHessianReadInput::ConstructL(HBufC8 * acontent)
{
	//this->iInputStream=new (ELeave)RReadStream();
	iInputStream =new (ELeave)RDesReadStream(*acontent); 
	//CleanupClosePushL(*iInputStream);
	//iInputStream->PushL();
	//iInputStream->

	//MStreamBuf * aSource;
	//aSource->WriteL(acontent,acontent->Length());
	//this->iInputStream=new (ELeave)RReadStream(aSource);
}


TInt CHessianReadInput::ReadInt()
{
	TInt tag=iInputStream->ReadInt8L();
	 if (tag == 'I')
	 {
		TInt b32 = iInputStream->ReadInt8L();
		TInt b24 = iInputStream->ReadInt8L();
		TInt b16 = iInputStream->ReadInt8L();
		TInt b8 = iInputStream->ReadInt8L();
		//TInt b32n = 0;
		//TInt b24n = 0;
		//TInt b16n = 0;
		//TInt b8n = 0;
		if(b8<0)
		{
			b8=b8+256;
		}
		if(b16<0)
		{
			b16=b16+256;
		}
		if(b24<0)
		{
			b24=b24+256;
		}
		if(b32<0)
		{
			b32=b32+256;
		}
		//if(b16<0)
		//{
		//	b16=b16+65536;
		//}
		TInt len=(b32<<24)+(b24<<16)+(b16<< 8)+b8;
		//TInt len=(b32*16777216)+(b24*65536)+(b16*256)+b8;
		return len;
	 }
	 return 0;
}

HBufC8* CHessianReadInput::ReadBytes()
{
	TChar aChar=iInputStream->ReadUint8L();
	if(aChar=='N')
	{
		return NULL;
	}
	if(aChar=='B')
	{
		TInt b16=iInputStream->ReadInt8L();
		TInt b8=iInputStream->ReadInt8L();
		if(b8<0)
		{
			b8=256+b8;
		}
		if(b16<0)
		{
			b16=b16+256;
			
		}
		TInt len=(b16 << 8)+b8;
		HBufC8 * aBytes=HBufC8::NewLC(1);
		CleanupStack::Pop();
		aBytes=aBytes->ReAllocL(len);
		TPtr8 bytesPtr(aBytes->Des());
		iInputStream->ReadL(bytesPtr,len);
		return aBytes;
	}
	return NULL;
}

TBool CHessianReadInput::ReadBoolean()
{
	TInt tag = iInputStream->ReadInt8L();
    switch (tag) {
    case 'T': return ETrue;
    case 'F': return EFalse;
    default:
      break;
    }
}

HBufC * CHessianReadInput::ReadUnicodeString()
{
	TInt tag = iInputStream->ReadInt8L();
	if (tag == 'N')
		return NULL;
	if (tag == 'S')
	{
		TInt b16=iInputStream->ReadInt8L();
		TInt b8=iInputStream->ReadInt8L();
		if(b8<0)
		{
			b8=256+b8;
		}
		if(b16<0)
		{
			b16=b16+256;
		}
		TInt len=(b16 << 8) + b8;
		HBufC * aStringResult=HBufC::NewLC(1);
		CleanupStack::Pop();
		for(TInt i=0;i<len;i++)
		{
			TUint ch=iInputStream->ReadUint8L();
			
			if (ch < 0x80){
				aStringResult=aStringResult->ReAllocL(aStringResult->Length()+1);
				aStringResult->Des().Append(TChar(ch));
			}else if ((ch & 0xe0) == 0xc0) {
				TUint ch1=iInputStream->ReadUint8L();
				aStringResult=aStringResult->ReAllocL(aStringResult->Length()+1);
				TUint v=((ch & 0x1f) << 6) + (ch1 & 0x3f);
				aStringResult->Des().Append(TChar(v));		
			}else if ((ch & 0xf0) == 0xe0)
			{
				TUint  ch1 = iInputStream->ReadUint8L();
				TUint  ch2 = iInputStream->ReadUint8L();
				aStringResult=aStringResult->ReAllocL(aStringResult->Length()+1);
				TUint  v = ((ch & 0x0f) << 12) + ((ch1 & 0x3f) << 6) + (ch2 & 0x3f);
				aStringResult->Des().Append(TChar(v));
			}
		}
		return aStringResult;
	}
	return NULL;

}



HBufC8* CHessianReadInput::ReadString()
{
	TInt tag = iInputStream->ReadInt8L();
    if (tag == 'N')
      return NULL;
    if (tag == 'S')
	{
		TInt b16=iInputStream->ReadInt8L();
		TInt b8=iInputStream->ReadInt8L();
		if(b8<0)
		{
			b8=256+b8;
		}
		if(b16<0)
		{
			b16=b16+256;
		}
		TInt len=(b16 << 8) + b8;
		//HBufC8* aString=HBufC8::NewLC(1);
		//CleanupStack::Pop();
		HBufC8 * aStringResult=HBufC8::NewLC(1);
		CleanupStack::Pop();



		for(TInt i=0;i<len;i++)
		{
			TInt ch=iInputStream->ReadUint8L();

			if (ch < 0x80){
				aStringResult=aStringResult->ReAllocL(aStringResult->Length()+1);
				aStringResult->Des().Append(TChar(ch));
			}else if ((ch & 0xe0) == 0xc0) {
				TInt ch1=iInputStream->ReadUint8L();
				aStringResult=aStringResult->ReAllocL(aStringResult->Length()+2);
				//TInt v=((ch & 0x1f) << 6) + (ch1 & 0x3f);
				aStringResult->Des().Append((ch & 0x1f) << 6);
				aStringResult->Des().Append(ch1 & 0x3f);			
			}else if ((ch & 0xf0) == 0xe0)
			{
				TUint  ch1 = iInputStream->ReadUint8L();
				TUint  ch2 = iInputStream->ReadUint8L();
				aStringResult=aStringResult->ReAllocL(aStringResult->Length()+4);
				TUint  v = ((ch & 0x0f) << 12) + ((ch1 & 0x3f) << 6) + (ch2 & 0x3f);
				//TBuf<256> haha;
				//haha.Append(TChar(v));

				//aStringResult->Des().AppendFill(v,);
				aStringResult->Des().Append(v>>8);
				aStringResult->Des().Append(v);

				//aStringResult->Des().Append((ch & 0x0f) << 12);
				//aStringResult->Des().Append((ch1 & 0x3f) << 6);
				//aStringResult->Des().Append(ch2 & 0x3f);	
			}
			
			
		}
		return aStringResult;
	}
	return NULL;
}

TInt64 CHessianReadInput::ReadLongContent()
{
 TInt tag = iInputStream->ReadInt8L();
 if (tag == 'L'){
		TInt64 b64 = iInputStream->ReadInt8L();
		TInt64 b56 = iInputStream->ReadInt8L();
		TInt64 b48 = iInputStream->ReadInt8L();
		TInt64 b40 = iInputStream->ReadInt8L();
		TInt64 b32 = iInputStream->ReadInt8L();
		TInt64 b24 = iInputStream->ReadInt8L();
		TInt64 b16 = iInputStream->ReadInt8L();
		TInt64 b8 = iInputStream->ReadInt8L();
		if(b8<0)
		{
			b8=b8+256;
		}
		if(b16<0)
		{
			b16=b16+256;
		}
		if(b24<0)
		{
			b24=b24+256;
		}
		if(b32<0)
		{
			b32=b32+256;
		}
		if(b40<0)
		{
			b40=b40+256;
		}
		if(b48<0)
		{
			b48=b48+256;
		}
		if(b56<0)
		{
			b56=b56+256;
		}
		if(b64<0)
		{
			b64=b64+256;
		}
		TInt64 aInt=(b64 << 56) +(b56 << 48) +(b48 << 40) +(b40 << 32) +(b32 << 24) +(b24 << 16) +(b16 << 8) + b8;
		return aInt;  
 }
 return 0;
}