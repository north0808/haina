/*
 ============================================================================
 Name		: BelugaDbDll.cpp 
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : BelugaDbDll.cpp - main DLL source
 ============================================================================
 */

//  Include Files  

#include <e32std.h>		 // GLDEF_C
#include <glib.h>

static void free_gstring (gpointer data, gpointer user_data)
	{
	g_string_free((GString*)data, TRUE);
	}

static void free_address_struct(gpointer data, gpointer user_data)
	{
	g_free(data);
	}

//  Exported Functions
GLDEF_C void freeGStringArray(GPtrArray * pArray)
	{
	if (pArray)
		{	
		g_ptr_array_foreach(pArray, free_gstring, NULL);
		g_ptr_array_free(pArray, TRUE);
		}
	}

GLDEF_C void freeAddressArray(GPtrArray * pArray)
	{
	if (pArray)
		{	
		g_ptr_array_foreach(pArray, free_address_struct, NULL);
	    g_ptr_array_free(pArray, TRUE);
		}
	}


#ifndef EKA2 // for EKA1 only
EXPORT_C TInt E32Dll(TDllReason /*aReason*/)
// Called when the DLL is loaded and unloaded. Note: have to define
// epoccalldllentrypoints in MMP file to get this called in THUMB.
	{
	return KErrNone;
	}
#endif

