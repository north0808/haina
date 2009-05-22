package com.oucenter.log.service;

import java.util.List;

import com.oucenter.core.service.IBaseSerivce;
import com.oucenter.log.domain.Log;
import com.oucenter.log.mvc.LogCommand;

/**
 * @author:付翔.
 * @createDate:2007-7-17.
 * @classInfo:
 */

public interface ILogService extends IBaseSerivce<Log> {
	
	/*分页*/
	public List<Log> findByPaginate(LogCommand logc,int startIndex,int rowCount);
	public Long getSizeByPaginate(LogCommand logc);

}
