package com.oucenter.log.dao;

import java.util.List;

import com.oucenter.core.dao.IBaseDAO;
import com.oucenter.log.domain.Log;

/**
 * @author:付翔.
 * @createDate:2007-7-16.
 * @classInfo:
 */

public interface ILogDAO extends IBaseDAO<Log> {
	
	List<Log> findlogbyParams( String[] param,int startIndex,int rowCount);
	Long getSizebyParams( String[] param);
}
