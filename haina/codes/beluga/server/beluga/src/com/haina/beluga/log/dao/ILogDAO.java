package com.haina.beluga.log.dao;

import java.util.List;

import com.haina.beluga.core.dao.IBaseDao;
import com.haina.beluga.log.domain.Log;

/**
 * @author:付翔.
 * @createDate:2007-7-16.
 * @classInfo:
 */

public interface ILogDAO extends IBaseDao<Log, String> {
	
	List<Log> findlogbyParams( String[] param,int startIndex,int rowCount);
	Long getSizebyParams( String[] param);
}
