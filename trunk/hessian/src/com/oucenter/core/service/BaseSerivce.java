package com.oucenter.core.service;

import java.io.Serializable;
import java.util.List;

import com.oucenter.core.dao.IBaseDAO;
import com.oucenter.core.model.IModel;
/**
 *  基本的Service层实现.
 * @author X_FU.
 *
 */
@SuppressWarnings("unchecked")
public abstract class BaseSerivce<T extends IModel> implements IBaseSerivce<T> {
	
	/*基本DAO的注入*/
	private IBaseDAO<T> baseDAO;
	
	public void delete(T model) {
		//BeanUtil.copyPropertie(dto, baseDAO.getBaseModel());
		baseDAO.deleteModel(model);
	}

	public void deleteById(Object id) {
		baseDAO.deleteModel( (Class<T>) baseDAO.getBaseModel().getClass(), (Serializable)id);
	}
	/**
	 */
	public List<T> findAll() {
		return baseDAO.getModels( (Class<T>) baseDAO.getBaseModel().getClass());
	}
	public Long findAllSize() {
		return baseDAO.getModelSize((Class<T>) baseDAO.getBaseModel().getClass());
	}

	public T findById(Object id) {
		return baseDAO.getModel((Class<T>) baseDAO.getBaseModel().getClass(), (Serializable)id);
		 
	}

	public void save(T model){
		baseDAO.saveModel(model);
	}

	public void update(T model) {
		baseDAO.updateModel(model);
	}
	


	public void saveOrUpdate(T model) {
		baseDAO.saveOrUpdateModel(model);
		
	}

	public void setBaseDAO(IBaseDAO<T> baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	public IBaseDAO<T> getBaseDAO() {
		return baseDAO;
	}


}
