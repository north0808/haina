package com.haina.beluga.core.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.haina.beluga.core.model.IModel;

/**
 * Hibernate implementation of GenericDao A typesafe implementation of CRUD and
 * finder methods based on Hibernate and Spring AOP The finders are implemented
 * through the executeFinder method. Normally called by the
 * FinderIntroductionInterceptor
 */
@SuppressWarnings("unchecked")
//
public class BaseDao<T extends IModel, PK extends Serializable> extends
		HibernateDaoSupport implements IBaseDao<T, PK> {

	private SessionFactory sessionFactory;

	private T type;
	@Override
	public PK create(T o) {
		return (PK) getHibernateTemplate().save(o);
	}
	@Override
	public T read(PK id) {
		return (T) getHibernateTemplate().get(type.getClass(), id);
	}
	@Override
	/**
	 * 走缓存
	 */
	public T load(PK id) {
		return (T) getHibernateTemplate().load(type.getClass(), id);
	}
	@Override
	public void update(T o) {
		getHibernateTemplate().update(o);
	}
	@Override
	public void delete(T o) {
		getHibernateTemplate().delete(o);
	}

	// public Session getSession() {
	// boolean allowCreate = true;
	// return SessionFactoryUtils.getSession(sessionFactory, allowCreate);
	// }

	@Override
	public void saveOrUpdate(T newInstance) {
		getHibernateTemplate().saveOrUpdate(newInstance);
	}

	@Override
	public void deleteById(PK id) {
		getHibernateTemplate().delete(read(id));
	}

	@Autowired(required = true)
	@Override
	public void setBaseModel(T t) {
		type = t;

	}

	@Override
	public T getBaseModel() {
		return type;
	}

	@Override
	public List<T> getModels() {
//		return (List<T>) getResultByHQLAndParam("from "+ type.getClass().getSimpleName());
		return getHibernateTemplate().loadAll(type.getClass());
	}

	@Override
	public Long getModelSize() {
		String hql = "select count(*) from " + type.getClass().getSimpleName();
		List list= getResultByHQLAndParam(hql);
		return ((BigInteger) list.get(0)).longValue();
	}

	@Autowired(required = true)
	public void setSessionFactory1(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public List<T> getModelByPage(T exampleEntity, int begin, int count) {
		int first=begin;
		int size=count;
		if(first<0) {
			first=1;
		}
		if(size<0) {
			size=1;
		}
		List<T> ret = null;
		if (exampleEntity == null) {
			exampleEntity = type;
		}
		ret = getHibernateTemplate().findByExample(exampleEntity, first, size);
		return ret;
	}

	@Override
	public List<T> getModelByHibernateCriteria(DetachedCriteria criteria) {
		List<T> list=null;
		if(criteria!=null) {
			list=this.getHibernateTemplate().findByCriteria(criteria);
		}
		return list;
	}

	@Override
	public List<T> getModelByHibernateCriteria(DetachedCriteria criteria,
			int begin, int count) {
		int first=begin;
		int size=count;
		if(first<0) {
			first=1;
		}
		if(size<0) {
			size=1;
		}
		List<T> list=null;
		if(criteria!=null) {
			list=this.getHibernateTemplate().findByCriteria(criteria, first, size);
		}
		return list;
	}
	/**
     * 通过HQL和参数查出结果集.
     * @param hql.
     * @return List.
     */
	@Override
    public List<?> getResultByHQLAndParam(String hql){
    	return getHibernateTemplate().find(hql);
    }
	@Override
    public List<?> getResultByHQLAndParam(String hql,Object object){
    	return getHibernateTemplate().find(hql,object);
    }
	@Override
    public List<?> getResultByHQLAndParam(String hql,Object[] object){
    	return getHibernateTemplate().find(hql,object);
    }
    /**
     * 通过HQL和参数查出结果集的Iterator.
     * @param hql.
     * @return List.
     *
	 * 走缓存
	 */
	@Override
    public Iterator<?> getIteratorByHQLAndParam(String hql){
    	return getHibernateTemplate().iterate(hql);
    }
	@Override
    public Iterator<?> getIteratorByHQLAndParam(String hql,Object object){
    	return getHibernateTemplate().iterate(hql,object);
    }
	@Override
    public Iterator<?> getIteratorByHQLAndParam(String hql,Object[] object){
    	return getHibernateTemplate().iterate(hql,object);
    }

}
