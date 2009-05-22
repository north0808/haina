package com.oucenter.log.service;

import java.util.ArrayList;
import java.util.List;

import com.oucenter.core.service.BaseSerivce;
import com.oucenter.log.dao.ILogDAO;
import com.oucenter.log.domain.Log;
import com.oucenter.log.mvc.LogCommand;
import com.oucenter.log.service.ILogService;

/**
 * @author:付翔.
 * @createDate:2007-7-17.
 * @classInfo:
 */

public class LogService extends BaseSerivce<Log> implements ILogService {

	private ILogDAO logdao;
	
	public List<Log> findByPaginate(LogCommand logc,int startIndex,int rowCount) {
		List<Log>  list = new ArrayList<Log>();
//		String[] parm = new String[]{logc.getUser(),logc.getRemark(),
//				logc.getStartIp(),logc.getEndIp(),
//				logc.getStartTime(),logc.getEndTime()};
//		List<Log> dbList =  logdao.findlogbyParams(parm,startIndex,rowCount);
//		if(dbList == null || dbList.size()<0)
//			return dbList;
//		IPSeeker ipSeeker = IPSeeker.getInstance();
		for(int i = 0 ; i < 20 ; i ++ ){
			Log log = new Log();
			log.setId(i+"");
			
//			String ip = dbList.get(i).getIp();
//			dbList.get(i).setIp("("+ip+")"+ipSeeker.getAddress(ip));
			list.add(log);
		}
		return list;
	}
	

	public Long getSizeByPaginate(LogCommand logc) {
		String[] parm = new String[]{logc.getUser(),logc.getRemark(),
				logc.getStartIp(),logc.getEndIp(),
				logc.getStartTime(),logc.getEndTime()};
		return logdao.getSizebyParams(parm);
	}

	public void setLogdao(ILogDAO logdao) {
		this.logdao = logdao;
	}
}
