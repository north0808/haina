/* 
*  this class encapsulate the platform api
*/

#ifndef BELUGAMOBILE_H
#define BELUGAMOBILE_H

#include "dll.h"

#include "CContactDb.h"
#include "CPhoneContact.h"
#include "CContact.h"
#include "glib.h"

namespace BelugaWinMobile
{
	/* mobile platform contact */
	class Contact {
	public:
		/* read contact read contact from mobile platform */
		Contact();
		~Contact();
		
		gboolean importContacts(CContactDb * pContactDb);

	private:
		CContactDll * m_pContact;
	};
	
	class SMS {};

	class MMS {};

	class Email {};

	class Call {};
}

#endif



