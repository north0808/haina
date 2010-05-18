package com.sihus.core.enumerate.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import com.sihus.core.enumerate.IntegerEnumHelper;

/**
 * 枚举Integer自定义类型。<br/>
 * @author huangyongqiang
 * @version 1.0
 * @since 1.0
 * @date 2009-05-20
 */
public abstract class EnumIntegerType implements UserType {

	public int[] sqlTypes() {
		return (new int[] { Types.INTEGER});

	}

	public Class returnedClass() {
		return getEnumClass();
	}

	public boolean equals(Object arg0, Object arg1) throws HibernateException {
		return arg0 == arg1;
	}

	public int hashCode(Object obj) throws HibernateException {
		if(null!=obj) {
			return obj.hashCode();
		} else {
			return 0;
		}
	}

	public Object nullSafeGet(ResultSet rs, String[] names, Object obj)
			throws HibernateException, SQLException {
		String value = rs.getString(names[0]);
		if (value == null) {
			return null;
		} else {
			return IntegerEnumHelper.valueOf(getEnumClass(), value);
		}

	}

	public void nullSafeSet(PreparedStatement st, Object value, int index)
			throws HibernateException, SQLException {
		st.setInt(index, value == null ? -1: IntegerEnumHelper.getEnumName(value).intValue());

	}

	public Object deepCopy(Object arg0) throws HibernateException {
		return arg0;
	}

	public boolean isMutable() {
		return false;
	}

	
	public abstract Class getEnumClass();

	public Serializable disassemble(Object arg0) throws HibernateException {
		return (Serializable) deepCopy(arg0);
	}

	public Object assemble(Serializable arg0, Object arg1) throws HibernateException {
		return deepCopy(arg0);
	}

	public Object replace(Object arg0, Object arg1, Object arg2) throws HibernateException {
		return arg0;
	}

}
