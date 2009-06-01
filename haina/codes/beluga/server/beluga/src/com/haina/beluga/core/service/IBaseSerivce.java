package com.haina.beluga.core.service;

import java.io.Serializable;
import java.util.List;

import com.haina.beluga.core.dao.IBaseDao;
import com.haina.beluga.core.model.IModel;
/**
 * 业务与管理分离,业务逻辑不处理异常.
 * 异常交与SpringAOP事务管理．
 * @author X_FU.
 *
 */
public interface IBaseSerivce<D extends IBaseDao<T,PK>,T extends IModel,PK extends Serializable> {
	
	D getBaseDao();
	
	void create(T model);
	void saveOrUpdate(T model);
	void update(T model); 
	void delete(T model);
	void deleteById(PK id);
	// load from cache
	T load(PK id);
	T read(PK id);
	List<T> findAll(boolean useCache);
	Long findAllSize();
}
