package com.oucenter.core.dao;
import java.io.Serializable;
import java.util.List;

import com.oucenter.core.model.IModel;
/**
 * 
 * @author X_FU.
 *
 */
public interface  IBaseDAO<T extends IModel> {
	
	public T getBaseModel();
	//无需set方法,注入所必须.
	public List<T> getModels(Class<T> clazz) ;
	public Long getModelSize(Class<T> clazz);
    public T getModel(Class<T> clazz, Serializable id) ;
    public void saveModel(T m) ;
    public void saveOrUpdateModel(T m) ;
    public void deleteModel(T m);
    public void deleteModel(Class<T> clazz, Serializable id);
    public void updateModel(T m);
}