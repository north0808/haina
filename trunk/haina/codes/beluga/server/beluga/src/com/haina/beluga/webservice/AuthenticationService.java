package com.haina.beluga.webservice;

/**
 * 认证业务处理接口实现类。<br/>
 * @author huangyongqiang
 * @version 1.0
 * @since 1.0
 * @date 2009-07-05
 */
public class AuthenticationService implements IAuthenticationService {
	
	private String proxyLoginUrl;
	
	private String proxyLogoutUrl;
	
	private String proxyRegisterUrl;
	
	private String loginUrl;
	
	private String registerUrl;
	
	private String logoutUrl;

	public String getProxyLoginUrl() {
		return proxyLoginUrl;
	}

	public void setProxyLoginUrl(String proxyLoginUrl) {
		this.proxyLoginUrl = proxyLoginUrl;
	}

	public String getProxyRegisterUrl() {
		return proxyRegisterUrl;
	}

	public void setProxyRegisterUrl(String proxyRegisterUrl) {
		this.proxyRegisterUrl = proxyRegisterUrl;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getRegisterUrl() {
		return registerUrl;
	}

	public void setRegisterUrl(String registerUrl) {
		this.registerUrl = registerUrl;
	}

	public String getProxyLogoutUrl() {
		return proxyLogoutUrl;
	}

	public void setProxyLogoutUrl(String proxyLogoutUrl) {
		this.proxyLogoutUrl = proxyLogoutUrl;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

}
