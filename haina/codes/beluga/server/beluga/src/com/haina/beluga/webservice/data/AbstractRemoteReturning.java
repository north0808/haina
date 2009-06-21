package com.haina.beluga.webservice.data;

import java.io.Serializable;

/**
 * 远程调用返回值的基类。<br/>
 * @author huangyongqiang
 * @version 1.0
 * @since 1.0
 * @date 2009-06-17
 */
public abstract class AbstractRemoteReturning extends AbstractRemoteData {

	/*返回值。*/
	protected Serializable value;

	public Serializable getValue() {
		return value;
	}

	public void setValue(Serializable value) {
		this.value = value;
	}
}
