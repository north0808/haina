#ifndef		__MYINC_H__
#define		__MYINC_H__

#include <iostream>
#include <tchar.h>
#include "glib/glib.h"


// static void free_gstring (gpointer data, gpointer user_data)
// {
// 	g_string_free((GString*)data, TRUE);
// }

static void free_point_struct(gpointer data, gpointer user_data)
{
	g_free(data);
}

//  Exported Functions
// void freeGStringArray(GPtrArray * pArray)
// {
// 	if (pArray)
// 	{
// 		g_ptr_array_foreach(pArray, free_gstring, NULL);
// 		g_ptr_array_free(pArray, TRUE);
// 	}
// }

void freeGPtrArray(GPtrArray * pArray)
{
	if (pArray)
	{
		g_ptr_array_foreach(pArray, free_point_struct, NULL);
		g_ptr_array_free(pArray, TRUE);
	}
}

#endif		/* __MYINC_H__ */