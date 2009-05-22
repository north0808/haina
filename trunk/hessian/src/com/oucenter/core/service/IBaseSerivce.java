package com.oucenter.core.service;

import java.util.List;

import com.oucenter.core.dao.IBaseDAO;
import com.oucenter.core.model.IModel;
/**
 * 业务与管理分离,业务逻辑不处理异常.
 * 异常交与SpringAOP事务管理．
 * 更新,删除,保存,不返回值.
 * @author X_FU.
 *
 */
public interface IBaseSerivce<T extends IModel> {
	
	IBaseDAO<T> getBaseDAO();
	void save(T model);
	void saveOrUpdate(T model);
	void update(T model); 
	void delete(T model);
	void deleteById(Object id);
	//IBaseDTO findById(Object id);
	T findById(Object id);
	List<T> findAll();
	Long findAllSize();
}
