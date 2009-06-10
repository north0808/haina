package com.haina.beluga.core.dao;

import java.io.Serializable;
import java.util.List;

import com.haina.beluga.core.model.IModel;

/**
 * The basic GenericDao interface with CRUD methods Finders are added with
 * interface inheritance and AOP introductions for concrete implementations
 * 
 * Extended interfaces may declare methods starting with find... list...
 * iterate... or scroll... They will execute a preconfigured query that is
 * looked up based on the rest of the method name
 */
public interface IBaseDao<T extends IModel, PK extends Serializable> {

	public PK create(T newInstance);

	public T read(PK id);

	// Load from cache
	public T load(PK id);

	public void saveOrUpdate(T newInstance);

	public void update(T transientObject);

	public void delete(T persistentObject);

	public void deleteById(PK id);

	public void setBaseModel(T t);

	public T getBaseModel();

	public List<T> getModels(boolean useCache);

	public Long getModelSize();
}
