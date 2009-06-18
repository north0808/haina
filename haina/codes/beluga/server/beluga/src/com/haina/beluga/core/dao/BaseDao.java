package com.haina.beluga.core.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
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
public class BaseDao<T extends IModel, PK extends Serializable> extends HibernateDaoSupport implements
		IBaseDao<T, PK> {
	
	private SessionFactory sessionFactory;

	private T type;

	public PK create(T o) {
		return (PK) getHibernateTemplate().save(o);
	}

	public T read(PK id) {
		return (T) getHibernateTemplate().get(type.getClass(), id);
	}

	public void update(T o) {
		getHibernateTemplate().update(o);
	}

	public void delete(T o) {
		getHibernateTemplate().delete(o);
	}

//	public Session getSession() {
//		boolean allowCreate = true;
//		return SessionFactoryUtils.getSession(sessionFactory, allowCreate);
//	}

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
	public List<T> getModels(boolean useCache) {
		Criteria c = getSession().createCriteria(type.getClass());
		c.setCacheable(useCache);
		return c.list();
	}

	@Override
	public Long getModelSize() {
		Query q = getSession().createSQLQuery(
				"select count(*) from " + type.getClass().getSimpleName());
		return ((BigInteger) q.list().get(0)).longValue();
	}

	@Override
	public T load(PK id) {
		return (T) getSession().load(type.getClass(), id);
	}
	
	@Autowired(required = true)
	public void setSessionFactory1(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	

}
