package com.haina.beluga.log.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.haina.beluga.core.dao.BaseDao;
import com.haina.beluga.log.domain.Log;
/**
 * @author:付翔.
 * @createDate:2007-7-16.
 * @classInfo:
 */
@Component
public class LogDAO extends BaseDao<Log,String> implements ILogDAO{

	@SuppressWarnings("unchecked")
	public List<Log> findlogbyParams( String[] param,final int startIndex,final int rowCount) {
		final String hqlStr = toHql(param,false);
//		return (List<Log>)getHibernateTemplate().execute(   
//                new HibernateCallback() {   
//                    public Object doInHibernate(Session session) throws HibernateException {   
//                        Query query = session.createQuery(hqlStr);   
//                        query.setFirstResult(startIndex).setMaxResults(rowCount);   
//                        return query.list();   
//                    }   
//                }   
//            );   
		
//		Session session=this.getHibernateTemplate().getSessionFactory().openSession(); 
//		Query query = session.createQuery(hql.toString());
//        query.setFirstResult(startIndex).setMaxResults(rowCount);
//        return query.list();

		//return this.getResultByHQLAndParam(hql.toString());
		return null;
	}


	public Long getSizebyParams(String[] param) {
		final String hqlStr = toHql(param,true);
//		return (Long) getHibernateTemplate().find(hqlStr).get(0);
		return null;
	}
	private String toHql(String[] param,boolean isGetSize){
		StringBuffer hql = null;
		if( ! isGetSize)
			hql = new StringBuffer("select log from Log log where 1=1 ");
		else
			hql = new StringBuffer("select count(*) from Log log where 1=1 ");
		if(!"".equals(param[0])){
			if( ! isGetSize)
				hql = new StringBuffer("select log from Log log,Account at where log.account=at.id and at.username='"+param[0]+"' ");
			else
				hql = new StringBuffer("select count(*) from Log log,Account at where log.account=at.id and at.username='"+param[0]+"' ");
			
		}
		if(!"".equals(param[1])){
			hql.append("and log.remark>="+param[1]+" ");
		}
		if(!"".equals(param[2])){
			hql.append("and log.ip >='"+param[2]+"' ");
		}
		if(!"".equals(param[3])){
			hql.append("and log.ip <='"+param[3]+"' ");
		}
		if(!"".equals(param[4])){
			hql.append("and log.logTime >='"+param[4]+"' ");
		}
		if(!"".equals(param[5])){
			hql.append("and log.logTime <='"+param[5]+"'");
		}
		hql.append(" order by log.logTime desc");
		return hql.toString();
	}
}
