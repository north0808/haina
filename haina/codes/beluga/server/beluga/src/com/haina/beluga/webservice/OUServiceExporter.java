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

import com.caucho.hessian.micro.MicroHessianInput;
import com.caucho.hessian.micro.MicroHessianOutput;

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
		if (!"POST".equals(req.getMethod())) {
		  res.setStatus(500);
	      PrintWriter out = res.getWriter();
	      res.setContentType("text/html");
	      out.println("<h1>OUCenter - Requires POST</h1>");
	      return;
		}
		InputStream is = req.getInputStream();
		OutputStream os = res.getOutputStream();
	   String _method = req.getParameter(getMethod());
	   MicroHessianInput hessianInput = new MicroHessianInput(is);
	  // hessianInput.readString();
	   MicroHessianOutput hessianOutput = new MicroHessianOutput(os);
	   uuSkeleton.invoke(_method, hessianInput, hessianOutput);
		
	}



//	public String getServletInfo(){
//	    return "OUCenter - Servlet";
//	 }
	
//	public void init(ServletConfig config) throws ServletException{
//    
//		super.init(config);
//		WebApplicationContext wac = WebApplicationContextUtils
//        .getRequiredWebApplicationContext(getServletContext());
//	    try {
//	       if (getInitParameter("service-class") != null) {
//	    	   String className = getInitParameter("service-class");
//	    	   Class<?> homeClass = loadClass(className);
//	    	   serverImpl = homeClass.newInstance();
//	    	   init(serverImpl);
//	       }else {
//				if (getClass().equals(OUServlet.class))
//				  throw new ServletException("server must extend OUServlet");
//				serverImpl = this;
//	      }
//	      if (getInitParameter("service-api") != null) {
//	    	  String className = getInitParameter("service-api");
//	    	  serverAPI = loadClass(className);
//	      }
//	      method_def = getInitParameter("method");
//	      uuSkeleton = new OUSkeleton(serverImpl, serverAPI);
//
//	    } catch (ServletException e) {
//	      throw e;
//	    } catch (Exception e) {
//	      throw new ServletException(e);
//	    }
//	}
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//		InputStream is = req.getInputStream();
//		OutputStream os = res.getOutputStream();
//	   String method = req.getParameter(method_def);
//	   MicroHessianInput hessianInput = new MicroHessianInput(is);
//	  // hessianInput.readString();
//	   MicroHessianOutput hessianOutput = new MicroHessianOutput(os);
//	   uuSkeleton.invoke(method, hessianInput, hessianOutput);
//	}
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//		      res.setStatus(500);
//		      PrintWriter out = res.getWriter();
//		      res.setContentType("text/html");
//		      out.println("<h1>OUCenter - Requires POST</h1>");
//		      return;
//	}
//	@Override
//	public void service(ServletRequest req, ServletResponse res)
//			throws ServletException, IOException {
//		InputStream is = req.getInputStream();
//		OutputStream os = res.getOutputStream();
//	   String method = req.getParameter(method_def);
//	   MicroHessianInput hessianInput = new MicroHessianInput(is);
//	  // hessianInput.readString();
//	   MicroHessianOutput hessianOutput = new MicroHessianOutput(os);
//	   uuSkeleton.invoke(method, hessianInput, hessianOutput);
//	}

	private Class<?> loadClass(String className)throws ClassNotFoundException{
	    
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
	    if (loader != null)
	      return Class.forName(className, false, loader);
	    else
	      return Class.forName(className);
	  }

//	  private void init(Object service)throws ServletException{
//		  
//	    if (! this.getClass().equals(OUServlet.class)) {
//	    }
//	    else if (service instanceof Service)
//	      ((Service) service).init(getServletConfig());
//	    else if (service instanceof Servlet)
//	      ((Servlet) service).init(getServletConfig());
//	  }	
	
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
