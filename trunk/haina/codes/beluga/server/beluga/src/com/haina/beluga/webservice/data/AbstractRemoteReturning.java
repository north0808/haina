package com.haina.beluga.webservice.data;

import com.haina.beluga.core.dto.IDto;


/**
 * 远程调用返回值的基类。<br/>
 * @author huangyongqiang
 * @version 1.0
 * @since 1.0
 * @date 2009-06-17
 */
public abstract class AbstractRemoteReturning extends AbstractRemoteData {

	/*返回值。*/
	protected IDto value;

	public IDto getValue() {
		return value;
	}

	public void setValue(IDto value) {
		this.value = value;
	}
}
