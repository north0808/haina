package com.haina.beluga.webservice;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.HttpRequestHandler;


public class OUServiceExporter implements HttpRequestHandler,InitializingBean {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6735390662011813417L;
	
	private String serverAPI;
	private Serializable serverImpl;
	private OUSkeleton uuSkeleton;
	private String method;
	
	public OUServiceExporter(){
	 
	}

	
	
	@Override
	public void handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		if (!"POST".equals(req.getMethod())) {
		  res.setStatus(500);
	      PrintWriter out = res.getWriter();
	      res.setContentType("text/html");
	      out.println("<h1>OUCenter -OU中心 Requires POST</h1>");
	      return;
		}
		InputStream is = req.getInputStream();
		OutputStream os = res.getOutputStream();
	   String _method = req.getParameter(getMethod());
	   MicroHessianInput hessianInput = new MicroHessianInput(is);
	  // hessianInput.readString();
	   MicroHessianOutput hessianOutput = new MicroHessianOutput(os);
	   
	   uuSkeleton.invoke(_method, hessianInput, hessianOutput, req.getLocale());
		
	}

	private Class<?> loadClass(String className)throws ClassNotFoundException{
	    
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
	    if (loader != null)
	      return Class.forName(className, false, loader);
	    else
	      return Class.forName(className);
	  }
	
	public OUSkeleton getUuSkeleton() {
		return uuSkeleton;
	}

	public void setUuSkeleton(OUSkeleton uuSkeleton) {
		this.uuSkeleton = uuSkeleton;
	}
	

	public String getServerAPI() {
		return serverAPI;
	}



	public void setServerAPI(String serverAPI) {
		this.serverAPI = serverAPI;
	}



	public Serializable getServerImpl() {
		return serverImpl;
	}



	public void setServerImpl(Serializable serverImpl) {
		this.serverImpl = serverImpl;
	}



	public String getMethod() {
		return method;
	}



	public void setMethod(String method) {
		this.method = method;
	}



	@Override
	public void afterPropertiesSet() throws Exception {
//	    	   Class<?> homeClass = loadClass(getServerImpl());
	      uuSkeleton = new OUSkeleton(getServerImpl(), loadClass(getServerAPI()));
		
	}


}
