package com.haina.beluga.contact.service;

import com.haina.beluga.webservice.data.hessian.HessianRemoteReturning;

public interface IIMService {
	
	 public HessianRemoteReturning getQQStatus(String qqCode);

}
