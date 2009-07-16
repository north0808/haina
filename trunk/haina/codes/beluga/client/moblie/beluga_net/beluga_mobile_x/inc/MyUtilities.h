/*
============================================================================
Name        : MyUtilities.h
Author      : Unic_Kay
Version     : 
Copyright   : Your copyright notice
Description : MyDefine.h - header file
============================================================================
*/
#ifndef MYUTILITIES_H
#define MYUTILITIES_H


namespace MyUtilities
{
	BOOL MByteToWChar(LPCSTR lpcszStr, LPWSTR lpwszStr)
	{
		DWORD dwSize = strlen(lpcszStr) + 1;
		// Get the required size of the buffer that receives the Unicode
		// string.
		DWORD dwMinSize;
		dwMinSize = MultiByteToWideChar (CP_ACP, 0, lpcszStr, -1, NULL, 0);

		if(dwSize < dwMinSize)
		{
			return FALSE;
		}

		// Convert headers from ASCII to Unicode.
		MultiByteToWideChar (CP_ACP, 0, lpcszStr, -1, lpwszStr, dwMinSize);  
		return TRUE;
	}

	BOOL WCharToMByte(LPCWSTR lpcwszStr, LPSTR lpszStr)
	{
		DWORD dwSize = wcslen(lpcwszStr) + 1;
		DWORD dwMinSize;
		dwMinSize = WideCharToMultiByte(CP_OEMCP,NULL,lpcwszStr,-1,NULL,0,NULL,FALSE);
		if(dwSize < dwMinSize)
		{
			return FALSE;
		}
		WideCharToMultiByte(CP_OEMCP,NULL,lpcwszStr,-1,lpszStr,dwSize,NULL,FALSE);
		return TRUE;
	}





/*	void GBK2UTF8()
	{
		//MultiByteToWideChar 把 GB2312 转换为 Unicode，再用 WideCharToMultiByte 把 Unicode 转换为 UTF-8
		MultiByteToWideChar();
		WideCharToMultiByte();
	}

	void UTF82GBK()
	{
		WideCharToMultiByte();
		MultiByteToWideChar();
	}*/
	
	
	
	/*
void ConvertUtf8ToGBK(CString& strUtf8) 
{
	int len=MultiByteToWideChar(CP_UTF8, 0, (LPCTSTR)strUtf8, -1, NULL,0);
	unsigned short * wszGBK = new unsigned short[len+1];
	memset(wszGBK, 0, len * 2 + 2);
	MultiByteToWideChar(CP_UTF8, 0, (LPCTSTR)strUtf8, -1, (LPWSTR)wszGBK, len);

	len = WideCharToMultiByte(CP_ACP, 0, (LPCWSTR)wszGBK, -1, NULL, 0, NULL, NULL);
	char *szGBK=new char[len + 1];
	memset(szGBK, 0, len + 1);
	WideCharToMultiByte (CP_ACP, 0, (LPCWSTR)wszGBK, -1, szGBK, len, NULL,NULL);

	strUtf8 = szGBK;
	delete[] szGBK;
	delete[] wszGBK;
}


void ConvertGBKToUtf8(CString& strGBK)
{
	int len=MultiByteToWideChar(CP_ACP, 0, (LPCTSTR)strGBK, -1, NULL,0);
	unsigned short * wszUtf8 = new unsigned short[len+1];
	memset(wszUtf8, 0, len * 2 + 2);
	MultiByteToWideChar(CP_ACP, 0, (LPCTSTR)strGBK, -1, (LPWSTR)wszUtf8, len);

	len = WideCharToMultiByte(CP_UTF8, 0, (LPCWSTR)wszUtf8, -1, NULL, 0, NULL, NULL);
	char *szUtf8=new char[len + 1];
	memset(szUtf8, 0, len + 1);
	WideCharToMultiByte (CP_UTF8, 0, (LPCWSTR)wszUtf8, -1, szUtf8, len, NULL,NULL);

	strGBK = szUtf8;
	delete[] szUtf8;
	delete[] wszUtf8;
}

*/
};


#endif // MYUTILITIES_H

// End of File