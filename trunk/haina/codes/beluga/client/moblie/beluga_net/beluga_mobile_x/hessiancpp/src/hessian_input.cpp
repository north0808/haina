// Copyright (C) 2004 Aldratech Ltd. See the LICENSE file for licensing information.
/*
 This file is part of hessiancpp.

		hessiancpp is free software; you can redistribute it and/or modify
		it under the terms of the GNU Lesser General Public License as published by
		the Free Software Foundation; either version 2.1 of the License, or
		(at your option) any later version.

		hessiancpp is distributed in the hope that it will be useful,
		but WITHOUT ANY WARRANTY; without even the implied warranty of
		MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
		GNU Lesser General Public License for more details.

		You should have received a copy of the GNU Lesser General Public License
		along with hessiancpp; if not, write to the Free Software
		Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/
/*
	Code in this file is partially based on code
	from the Micro-Hessian Java implementation.
*/
#include "hessian_input.h"

using namespace hessian::wrappers;

namespace hessian {

	void hessian_input::start_reply() throw(io_exception) {
		int tag = _is->read();

		if (tag != 'r')
			throw expect("hessian reply", tag);

		int major = _is->read();
		int minor = _is->read();
		if (major & minor);	// avoid warning
	}

	void hessian_input::complete_reply() throw(io_exception) {
		int tag = _is->read();

		if (tag != 'z')
			throw expect("end of reply", tag);
	}

/*	Object* hessian_input::get_result() throw(io_exception) {
		return read_object();
	}

	Object* hessian_input::read_object() throw(io_exception) {
		int tag = _is->read();
		return read_object(tag);
	}

	Object* hessian_input::read_object(int tag) throw(io_exception) {
		switch (tag) {
		case 'b':
		case 'B': return new Binary(read_bytes(tag));
		case 'T':
		case 'F': return new Boolean(read_boolean(tag));
		case 'd': return new Date(read_date(tag));
		case 'D': return new Double(read_double(tag));
		case 'f': return new Fault(read_fault(tag));
		case 'I': return new Integer(read_int(tag));
		case 'V': return new List(read_list(tag));
		case 'L': return new Long(read_long(tag));
		case 'M': return new Map(read_map(tag));
		case 'N': return new Null();
		case 'R': return new Ref(read_ref(tag));
		case 'S': return new String(read_string(tag));
		case 'X': return new Xml(read_xml(tag));
		default:
			throw io_exception(string("hessian_input::readObject(): tag ").append(1, (char) tag).append(" cannot be handled"));
		}
		return NULL;
	}*/


	bool hessian_input::read_boolean() throw(io_exception) {
		int tag = _is->read();
		switch (tag) {
		case 'T': return true;
		case 'F': return false;
		default:
			throw expect("bool", tag);
		}
	}

	int hessian_input::read_int() throw(io_exception) {
		int tag = _is->read();
		if (tag != 'I')
			throw expect("integer", tag);

		int b32 = _is->read() & 0xFF;
		int b24 = _is->read() & 0xFF;
		int b16 = _is->read() & 0xFF;
		int b8 = _is->read() & 0xFF;

		return((b32 << 24) + (b24 << 16) + (b16 << 8) + b8) & 0x00000000FFFFFFFF;
	}

	long long hessian_input::read_long() throw(io_exception) {
		int tag = _is->read();
		if (tag != 'L')
			throw expect("long", tag);

		return read_long64();
	}

	double hessian_input::read_double() throw(io_exception) {
		int tag = _is->read();
		if (tag != 'D')
			throw expect("double", tag);
		long long lval = read_long64();
		double* dval = (double*) & lval;
		return *dval;
	}

	long long hessian_input::read_date() throw(io_exception) {
		int tag = _is->read();
		if (tag != 'd')
			throw expect("date", tag);
		return read_long64();
	}

	long long hessian_input::read_long64() throw(io_exception) {
		unsigned long long b64 = _is->read() & 0xFFULL;
		unsigned long long b56 = _is->read() & 0xFF;
		unsigned long long b48 = _is->read() & 0xFF;
		unsigned long long b40 = _is->read() & 0xFF;
		unsigned long long b32 = _is->read() & 0xFF;
		unsigned long long b24 = _is->read() & 0xFF;
		unsigned long long b16 = _is->read() & 0xFF;
		unsigned long long b8 = _is->read() & 0xFF;
		long long value = (b64 << 56) +
											(b56 << 48) +
											(b48 << 40) +
											(b40 << 32) +
											(b32 << 24) +
											(b24 << 16) +
											(b16 << 8) +
											b8;
		return value;
	}

	string hessian_input::read_string() throw(io_exception) {
		int tag = _is->read();
		if (tag == 'N')
			return string("");
		if (tag != 'S')
			throw expect("string", tag);

// 		int b16 = _is->read();
// 		int b8 = _is->read();
// 
// 		int len = (b16 << 8) + b8;
		int len = read_int();

		return read_string_impl(len);
	}

	void read_byte_chunk(auto_ptr<input_stream>& is, string& bytes) {
		int b16 = is->read() & 0xFF;
		int b8 = is->read() & 0xFF;
		int len = (b16 << 8) + b8;
		for (int i = 0; i < len; i++)
			bytes.push_back( (char)is->read() );
	}

	string hessian_input::read_bytes() throw(io_exception) {
		int tag = _is->read();
		if (tag == 'N')
			return("");
		string bos;
		while (tag == 'b') {
			read_byte_chunk(_is, bos);
			tag = _is->read();
		}

		if (tag != 'B')
			throw expect("bytes", tag);
		read_byte_chunk(_is, bos);

		return bos;
	}

	string hessian_input::read_xml() throw(io_exception) {
		int tag = _is->read();
		if (tag == 'N') {
			return("");
		}
		if (tag != 'X') {
			throw expect("xml", tag);
		}
		int b16 = _is->read();
		int b8 = _is->read();

		int len = (b16 << 8) + b8;

		return read_string_impl(len);
	}

/*	list<Object*> hessian_input::read_list(int tag) throw(io_exception) {
		if (tag == 'N') {
			list<Object*> l;
			return l;
		}
		if (tag != 'V') {
			throw expect("list", tag);
		}
		int list_length = 0;
		int meta = _is->read();
		// parse optional (meta) type and/or length
		while (meta == 't' || meta == 'l') {
			if (meta == 't') {
				// type
				string list_type = read_string('S');
			}
			if (meta == 'l') {
				// length
				int b32 = _is->read();
				int b24 = _is->read();
				int b16 = _is->read();
				int b8 = _is->read();
				list_length = (b32 << 24) + (b24 << 16) + (b16 << 8) + b8;
			}
			meta = _is->read();
		}
		// read in the list data
		list<Object*> l;
		while (meta != 'z') {	// list ends with 'z'
			// read object
			Object* obj = read_object(meta);
			// add to list
			l.push_back(obj);
			// next please
			meta = _is->read();
		}
		return l;
	}

	map<string,Object*> hessian_input::read_map() throw(io_exception) {
		int tag = _is->read();
		if (tag == 'N') {
			Map::basic_type l;
			return l;
		}
		if (tag != 'M') {
			throw expect("map", tag);
		}
		int meta = _is->read();
		// parse optional (meta) type
		while (meta == 't') {
			if (meta == 't') {
				// type
				string map_type = read_string('S');
			}
			meta = _is->read();
		}
		// read in the map data
		Map::basic_type m;
		while (meta != 'z') {	// map ends with 'z'
			// read key object
			string key(read_string(meta));
			// read value object
			meta = _is->read();
			Object* val = read_object(meta);
			// add to list
			m.insert(std::make_pair(key, val));
			// next please
			meta = _is->read();
		}
		return m;
	}*/

	int hessian_input::read_ref() throw(io_exception) {
		int tag = _is->read();
		if (tag != 'R') {
			throw expect("ref", tag);
		}
		int b32 = _is->read() & 0xFF;
		int b24 = _is->read() & 0xFF;
		int b16 = _is->read() & 0xFF;
		int b8 = _is->read() & 0xFF;

		return((b32 << 24) + (b24 << 16) + (b16 << 8) + b8) & 0x00000000FFFFFFFF;
	}

	string hessian_input::read_string_impl(int length) throw(io_exception) {
		string sb;

		for (int i = 0; i < length; i++) {
			int ch = _is->read();

			if (ch < 0x80)
				sb.append(1, (char) ch);
			else if ((ch & 0xe0) == 0xc0) {
				int ch1 = _is->read();
				int v = ((ch & 0x1f) << 6) + (ch1 & 0x3f);

				sb.append(1, (char) v);
			}
			else if ((ch & 0xf0) == 0xe0) {
				int ch1 = _is->read();
				i++;
				int ch2 = _is->read();
				i++;
				int v = ((ch & 0x0f) << 12) + ((ch1 & 0x3f) << 6) + (ch2 & 0x3f);

				sb.append(1, (char) v);
			}
			else
				throw	io_exception("bad utf-8 encoding");
		}

		return sb;
	}

/*	Fault hessian_input::read_fault(int tag) throw(io_exception) {
		
		if (tag != 'f')
			throw expect("fault", tag);

		// skip over "code"
		int _tag = _is->read();
		read_string(_tag);
		// read code value
		_tag = _is->read();
		String code(read_string(_tag));

		// skip over "message"
		_tag = _is->read();
		read_string(_tag);
		// read message value
		_tag = _is->read();
		String message(read_string(_tag));

		// skip over "detail"
		_tag = _is->read();
		read_string(_tag);

		//read stack trace
		_tag = _is->read();
		Map destroyer(read_map(_tag));
		// the Map above acts goes out of scope below
		// and deletes itself to prevent a lot of leaks

		string mesg = message.value();
		// hack: exception type encoded in the message
		unsigned long exception_type_start = mesg.find(']');
		if (exception_type_start != string::npos) {
			string exception_type(mesg.substr(0, exception_type_start));
			string exception_mesg(mesg.substr(exception_type_start + 1));
			return Fault(exception_type, exception_mesg);
		}
		// end hack
		return Fault(code, message);
	}*/

	io_exception hessian_input::expect(const string& expect, int ch) {
		if (ch < 0)
			return io_exception("expected " + expect + " at end of file");
		else
			return io_exception("expected " + expect + " at " + (char) ch);
	}
}

