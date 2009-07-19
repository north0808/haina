/*
 ============================================================================
 Name		: BelugaDbDll.cpp
 Author	  : shaochuan.yang
 Copyright   : haina
 Description : BelugaDbDll.cpp - main DLL source
 ============================================================================
 */

//  Include Files

#include <glib.h>
#include "Beluga.h"		 


static void free_gstring (gpointer data, gpointer user_data)
	{
	g_string_free((GString*)data, TRUE);
	}

static void free_address_struct(gpointer data, gpointer user_data)
	{
	g_free(data);
	}

//  Exported Functions
EXPORT_C void freeGStringArray(GPtrArray * pArray)
	{
	if (pArray)
		{
		g_ptr_array_foreach(pArray, free_gstring, NULL);
		g_ptr_array_free(pArray, TRUE);
		}
	}

EXPORT_C void freeAddressArray(GPtrArray * pArray)
	{
	if (pArray)
		{
		g_ptr_array_foreach(pArray, free_address_struct, NULL);
	    g_ptr_array_free(pArray, TRUE);
		}
	}
