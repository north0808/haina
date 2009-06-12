package com.haina.beluga.webservice;
/**
 * 基本常量以正整数开始.
 * 应用级错误、异常定义在:-1~-10000之间.
 * 系统级错误、异常定义在:-10000以后.
 * @author Administrator
 *
 */
public class Constant {
	
	/*QQ不在线返回的图像宽度*/
	public static final int QQ_OFFLINE_WIDTH = 16;
	/*QQ在线*/
	public static final int QQ_ONLINE = 0;
	/*QQ不在线*/
	public static final int QQ_OFFLINE = 1;
	
	
	/***
	 * 
	 */
	/*QQ参数错误*/
	public static final int QQ_ARG_ERROE = -1;
	/*网络错误*/
	public static final int NETWORK_ERROR = -10000;
}
