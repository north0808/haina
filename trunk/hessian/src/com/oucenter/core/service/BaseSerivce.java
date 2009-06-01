package com.oucenter.core.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.oucenter.core.dao.IBaseDao;
import com.oucenter.core.model.IModel;
/**
 *  基本的Service层实现.
 * @author X_FU.
 *
 */


public  class BaseSerivce<D extends IBaseDao<T,PK>,T extends IModel,PK extends Serializable> implements IBaseSerivce<D,T,PK> {
	
	
	private D baseDao;
	

	@Autowired(required=true)
	public void setBaseDao(D baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void create(T model) {
		baseDao.create(model);
	}

	@Override
	public void delete(T model) {
		baseDao.delete(model);
	}

	@Override
	public void deleteById(PK id) {
		baseDao.deleteById(id);
	}

//	@Override
//	public List<IModel> findAll() {
//		return null;
//	}

	@Override
	public D getBaseDao() {
		return baseDao;
	}

	@Override
	public T read(PK id) {
		return baseDao.read(id);
	}

	@Override
	public void saveOrUpdate(T model) {
		baseDao.saveOrUpdate(model);
	}

	@Override
	public void update(T model) {
		baseDao.update(model);
	}

	@Override
	public List<T> findAll(boolean useCache) {
		return baseDao.getModels(useCache);
	}

	@Override
	public Long findAllSize() {
		return baseDao.getModelSize();
	}

	@Override
	public T load(PK id) {
		return baseDao.load(id);
	}
	

}
