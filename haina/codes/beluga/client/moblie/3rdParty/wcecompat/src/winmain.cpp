/*  wcecompat: Windows CE C Runtime Library "compatibility" library.
 *
 *  Copyright (C) 2001-2002 Essemer Pty Ltd.  All rights reserved.
 *  http://www.essemer.com.au/
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */


#include <winsock2.h>
#include "args.h"
#include "redir.h"	// initStdHandles

int main(int argc, char* argv[]);


int
WINAPI
WinMain(
	HINSTANCE /*hInstance*/,
	HINSTANCE /*hPrevInstance*/,
	LPWSTR lpCmdLine,
	int /*nShowCmd*/)
{
	int		result;
	int		argc;
	char**	argv;

	// convert program name and lpCmdLine into argc/argv, and handle I/O redirection
	argc = processCmdLine(lpCmdLine, &argv);

#if _WIN32_WCE < 0x500 || !defined(COREDLL_CORESIOA)
	initStdHandles();	// get environment variables from ChildData
#endif

	result = main(argc, (char**)argv);

	return result;
}
