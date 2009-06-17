/*
============================================================================
 Name        : HessionReadInput.h
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : CHessianReadInput declaration
============================================================================
*/

#ifndef HESSIANREADINPUT_H
#define HESSIANREADINPUT_H

// INCLUDES
#include <e32std.h>
#include <e32base.h>
#include <s32strm.h>
#include <s32mem.h>
// CLASS DECLARATION

/**
*  CHessianReadInput
* 
*/
class CHessianReadInput : public CBase
{
public: // Constructors and destructor

	/**
        * Destructor.
        */
	~CHessianReadInput();

        /**
        * Two-phased constructor.
        */
	static CHessianReadInput* NewL(HBufC8 * acontent);

        /**
        * Two-phased constructor.
        */
	static CHessianReadInput* NewLC(HBufC8 * acontent);

private:

	/**
        * Constructor for performing 1st stage construction
        */
	CHessianReadInput();

	/**
        * EPOC default constructor for performing 2nd stage construction
        */
	void ConstructL(HBufC8 * acontent);
private:
	//RReadStream * iInputStream;
	RDesReadStream* iInputStream;
public :
	TInt ReadInt();
	HBufC8* ReadBytes();
	TBool ReadBoolean();
	HBufC8* ReadString();
	TInt64 ReadLongContent();
	HBufC * ReadUnicodeString();

};

#endif // HESSIANREADINPUT_H
