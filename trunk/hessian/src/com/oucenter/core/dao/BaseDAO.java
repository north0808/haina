package com.oucenter.core.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.oucenter.core.model.IModel;
/**
 * 对Hibernate封装,增,删,改,查的抽象实现.
 * @author X_FU.
 */
@SuppressWarnings({ "hiding", "unchecked" })
public abstract  class BaseDAO<T extends IModel> extends HibernateDaoSupport implements IBaseDAO<T> {
	/**
	 * 
	 */
	private T baseModel;
	/**
	 * Log.
	 */
    protected final Log log = LogFactory.getLog(getClass());
    /**
     * save
     */
    public void saveModel(T o){
    	 getHibernateTemplate().save(o);
    }
    /**
     * saveOrupdate
     */
    public void saveOrUpdateModel(T o) {
        getHibernateTemplate().saveOrUpdate(o);
    }
    /**
     * update
     */
    public void updateModel(T o) {
        getHibernateTemplate().update(o);
    }
    /**
     * getOne
     */
	public T getModel(Class<T> clazz, Serializable id) {
        Object o = getHibernateTemplate().get(clazz, id);
        if (o == null) {
            throw new ObjectRetrievalFailureException(clazz, id);
        }
        return (T)o;
    }
    /**
     * getList
     */
	public List<T> getModels(Class<T> clazz) {
        return getHibernateTemplate().loadAll(clazz);
    }
    /**
     * getListSize 
     */
    public Long getModelSize(Class<T> clazz) {
    	return (Long) getHibernateTemplate().find("select count(*) from "+clazz.getSimpleName()).get(0);
       // return (Integer)list.get(0);
    }
    /**
     * delete by id
     */
    public void deleteModel(Class<T> clazz, Serializable id) {
        getHibernateTemplate().delete(getModel(clazz, id));
    }
    /**
     * delete by Object
     */
    public void deleteModel(T o) {
        getHibernateTemplate().delete(o);
    }
    /**
     * 通过HQL和参数查出结果集.
     * @param hql.
     * @return List.
     */
    public List<T> getResultByHQLAndParam(String hql){
    	return getHibernateTemplate().find(hql);
    }
    
    public List<T> getResultByHQLAndParam(String hql,Object object){
    	return getHibernateTemplate().find(hql,object);
    }
    
    public List<T> getResultByHQLAndParam(String hql,Object[] object){
    	return getHibernateTemplate().find(hql,object);
    }
    
	public T getBaseModel() {
		return baseModel;
	}
	public void setBaseModel(T baseModel) {
		this.baseModel = baseModel;
	}
	
}
