// Author JackyHo
// Create Date:2009.8.30
// Description:��ȡmobile6.0ͨѶ¼
// The following ifdef block is the standard way of creating macros which make exporting 
// from a DLL simpler. All files within this DLL are compiled with the DLL_EXPORTS
// symbol defined on the command line. this symbol should not be defined on any project
// that uses this DLL. This way any other project whose source files include this file see 
// DLL_API functions as being imported from a DLL, whereas this DLL sees symbols
// defined with this macro as being exported.
#ifdef DLL_EXPORTS
#define DLL_API __declspec(dllexport)
#else
#define DLL_API __declspec(dllimport)
#endif

#include <glib.h>

// This class is exported from the dll.dll
class DLL_API Cdll {
public:
	Cdll(void);
	// TODO: add your methods here.
	//�豸�ϵ�����ϵ������
	gint totalCount;
	//��ʼ�������õ�ǰʵ�������е�����ϵ������
	gboolean init(void);
	// ��ȡ�豸�ϴ洢����ϵ��
	gboolean getContact(GList *pGListContact, guint offset, guint len) const;
};

extern DLL_API int ndll;

DLL_API int fndll(void);
