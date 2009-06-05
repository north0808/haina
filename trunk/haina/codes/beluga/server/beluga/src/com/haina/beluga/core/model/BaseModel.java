package com.haina.beluga.core.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 基本的持久化模型类�。<br/>
 * @author huangyongqiang
 * @version 1.0
 * @since 1.0
 * @date 2009-05-20
 */
public abstract class BaseModel implements IModel {

	private static final long serialVersionUID = -4852447890149395894L;
	
	protected final transient Log logger = LogFactory.getLog(this.getClass());
	
	protected String id;

	public void setId(String id) {
		if(isNew()) {
		    this.id = id;
		}
	}
	
	public abstract String getId();
	
	public boolean isNew() {
		return id == null;
	}
	
	public abstract int hashCode();

	public abstract boolean equals(Object object);
	
	public abstract String toString();
}