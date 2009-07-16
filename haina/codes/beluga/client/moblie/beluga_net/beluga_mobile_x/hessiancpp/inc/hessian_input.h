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

#ifndef HESSIAN_HESSIAN_INPUT_H
#define HESSIAN_HESSIAN_INPUT_H

#include "wrappers.h"
#include "exceptions.h"
#include "input_stream.h"
#include <string>
#include <list>
#include <memory>

using namespace hessian::exceptions;
using namespace hessian::wrappers;
using namespace std;

namespace hessian {

	/// Process input stream for Hessian responses.
	class hessian_input {
	public:
		/**
		 * Creates a new Hessian input stream, initialized with an
		 * underlying input stream.
		 *
		 * @param is the underlying input stream.
		 */
		hessian_input(auto_ptr<input_stream> is) : _is(is) {
			;
		}

		/// Destructor.
		virtual ~hessian_input() {
		}

		/**
		 * Starts reading the reply.
		 * <p>Hessian:
		 * <pre>
		 * r x01 x00
		 * </pre>
		 * @exception io_exception
		 */
		void start_reply() throw(io_exception);

		/**
		 * Completes reading the call.
		 * <p>Hessian:
		 * <pre>
		 * z
		 * </pre>
		 * @exception io_exception
		 */
		void complete_reply() throw(io_exception);

		///Retrieve the call result.
//		Object* get_result() throw(io_exception);

	protected:
		//
	public:
		/// Reads an object from the stream
		/**
		 * @exception io_exception when IO error or expected class type not found
		 */
//		Object* read_object() throw(io_exception);

		/// Reads an object from the stream with object tag already read
		/**
		 * @param tag the object tag from the stream
		 * @exception io_exception when IO error or expected class type not found
		 */
//		Object* read_object(int tag) throw(io_exception);

		/**
		 * Reads a bool.
		 * <p>Hessian:
		 * <pre>
		 * T
		 * F
		 * </pre>
		 * @exception io_exception
		 */
		bool read_boolean() throw(io_exception);

		/**
		 * Reads an int.
		 * <p>Hessian:
		 * <pre>
		 * I b32 b24 b16 b8
		 * </pre>
		 * @exception io_exception
		 */
		int read_int() throw(io_exception);

		/**
		 * Reads a double.
		 * <p>Hessian:
		 * <pre>
		 * D b64 b56 b48 b40 b32 b24 b16 b8
		 * </pre>
		 * @exception io_exception
		 */
		double read_double() throw(io_exception);

		/**
		 * Reads a long.
		 * <pre>
		 * L b64 b56 b48 b40 b32 b24 b16 b8
		 * </pre>
		 * @exception io_exception
		 */
		long long read_long() throw(io_exception);

		/**
		 * Reads an UTC date.
		 * <p>Hessian:
		 * <pre>
		 * T b64 b56 b48 b40 b32 b24 b16 b8
		 * </pre>
		 * @exception io_exception
		 */
		long long read_date() throw(io_exception);

		/**
		 * Reads a string.
		 * <p>Hessian:
		 * <pre>
		 * S b16 b8 string value
		 * </pre>
		 * @exception io_exception
		 */
		string read_string() throw(io_exception);

		/**
		 * Reads a byte array.
		 * <p>Hessian:
		 * <pre>
		 * B b16 b8 data value
		 * </pre>
		 * @exception io_exception
		 */
		string read_bytes() throw(io_exception);

		/**
		 * Reads a list.
		 * <p>Hessian:
		 * <pre>
		 * V (t b16 b8 type-string)?
		 * length?
		 * object*
		 * z
		 * </pre>
		 * @exception io_exception
		 */
//		list<Object*> read_list(int tag) throw(io_exception);

		/**
		 * Reads a map.
		 * <p>Hessian:
		 * <pre>
		 * M (t b16 b8 type-string)?
		 * (object, object)*
		 * z
		 * </pre>
		 * @exception io_exception
		 */
//		map<string, Object*> read_map() throw(io_exception);

		/**
		 * Reads a ref.
		 * <p>Hessian:
		 * <pre>
		 * R b32 b24 b16 b8
		 * </pre>
		 * @exception io_exception
		 */
		int read_ref() throw(io_exception);

		/**
		 * Reads an Xml.
		 * <p>Hessian:
		 * <pre>
		 * X b16 b8 string value
		 * </pre>
		 * @exception io_exception
		 */
		string read_xml() throw(io_exception);

		/**
		 * Reads a Fault.
		 * @exception io_exception
		 */
//		Fault read_fault(int tag) throw(io_exception);

		///Reads a string from the underlying stream.
		string read_string_impl(int length) throw(io_exception);
		///Reads a 64-bit long from the underlying stream, no tags.
		long long read_long64() throw (io_exception);
		///Creates exception message.
		io_exception expect(const string& expect, int ch);
		///Auto-Ptr to input stream.
		auto_ptr<input_stream> _is;
	};
}

#endif

