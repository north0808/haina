package com.haina.beluga.webservice.data;


/**
 * 远程调用返回值的基类。<br/>
 * @author huangyongqiang
 * @version 1.0
 * @since 1.0
 * @date 2009-06-17
 */
public abstract class AbstractRemoteReturning extends AbstractRemoteData {

	/*返回值。*/
	protected Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
