package com.haina.beluga.core.dao;

import java.io.Serializable;
import java.util.List;

import com.haina.beluga.core.model.IModel;

/**
 * The basic GenericDao interface with CRUD methods
 * Finders are added with interface inheritance and AOP introductions for concrete implementations
 *
 * Extended interfaces may declare methods starting with find... list... iterate... or scroll...
 * They will execute a preconfigured query that is looked up based on the rest of the method name
 */
public interface IBaseDao<T extends IModel, PK extends Serializable>
{

    PK create(T newInstance);

    T read(PK id);
    //Load from cache
    T load(PK id);
    
    void saveOrUpdate(T newInstance);
    
    void update(T transientObject);

    void delete(T persistentObject);
    
    void deleteById(PK id);
    
    void setBaseModel(T t);
    
    T getBaseModel();
    
	public List<T> getModels(boolean useCache);
	
	public Long getModelSize();
}
