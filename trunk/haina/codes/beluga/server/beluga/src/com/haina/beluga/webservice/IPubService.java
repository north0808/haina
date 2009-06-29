package com.haina.beluga.webservice;

import java.io.Serializable;

import com.haina.beluga.webservice.data.hessian.HessianRemoteReturning;
/**
 * 公共服务api.
 * @author Administrator
 *
 */
public interface IPubService extends Serializable {
	/**
	 * weather
	 */
	public HessianRemoteReturning getLiveWeather(String cityCode);
	public HessianRemoteReturning get7Weatherdatas(String cityCode);
	/**
	 * IM
	 */
	public HessianRemoteReturning getQQStatus(int qqCode);
	/**
	 * 手机归属地更新
	 * @param updateFlg
	 * @return
	 */
	public HessianRemoteReturning getOrUpdatePD(int updateFlg);
	

}
