package com.haina.beluga.webservice;

import java.io.Serializable;

public class AuthenticationConfig implements Serializable{

	private static final long serialVersionUID = -2917948711741108371L;
	
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
