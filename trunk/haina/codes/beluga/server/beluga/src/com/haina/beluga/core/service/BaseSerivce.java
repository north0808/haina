package com.haina.beluga.core.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.haina.beluga.core.dao.IBaseDao;
import com.haina.beluga.core.model.IModel;
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

	
	public void create(T model) {
		baseDao.create(model);
	}

	
	public void delete(T model) {
		baseDao.delete(model);
	}

	
	public void deleteById(PK id) {
		baseDao.deleteById(id);
	}

//	
//	public List<IModel> findAll() {
//		return null;
//	}

	
	public D getBaseDao() {
		return baseDao;
	}

	
	public T read(PK id) {
		return baseDao.read(id);
	}

	
	public void saveOrUpdate(T model) {
		baseDao.saveOrUpdate(model);
	}

	
	public void update(T model) {
		baseDao.update(model);
	}

	
	public List<T> findAll(boolean useCache) {
		return baseDao.getModels(useCache);
	}

	
	public Long findAllSize() {
		return baseDao.getModelSize();
	}

	
	public T load(PK id) {
		return baseDao.load(id);
	}
	

}
