package com.oucenter.core.service;

import java.io.Serializable;

import com.oucenter.core.dao.IBaseDao;
import com.oucenter.core.model.IModel;
/**
 * 业务与管理分离,业务逻辑不处理异常.
 * 异常交与SpringAOP事务管理．
 * 更新,删除,保存,不返回值.
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
	//IBaseDTO findById(Object id);
	T read(PK id);
//	List<IModel> findAll();
//	Long findAllSize();
}
