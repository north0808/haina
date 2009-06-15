package com.haina.beluga.webservice;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caucho.hessian.micro.MicroHessianInput;
import com.caucho.hessian.micro.MicroHessianOutput;
import com.caucho.services.server.Service;

public class OUServlet extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6735390662011813417L;
	
	private Class<?> serverAPI;
	private Object serverImpl;
	private OUSkeleton uuSkeleton;
	private String method_def;
	
	public OUServlet(){
	 
	}

	public String getServletInfo(){
	    return "OUCenter - Servlet";
	 }
	
	public void init(ServletConfig config) throws ServletException{
    
		super.init(config);
    
	    try {
	       if (getInitParameter("service-class") != null) {
	    	   String className = getInitParameter("service-class");
	    	   Class<?> homeClass = loadClass(className);
	    	   serverImpl = homeClass.newInstance();
	    	   init(serverImpl);
	       }else {
				if (getClass().equals(OUServlet.class))
				  throw new ServletException("server must extend OUServlet");
				serverImpl = this;
	      }
	      if (getInitParameter("service-api") != null) {
	    	  String className = getInitParameter("service-api");
	    	  serverAPI = loadClass(className);
	      }
	      method_def = getInitParameter("method");
	      uuSkeleton = new OUSkeleton(serverImpl, serverAPI);

	    } catch (ServletException e) {
	      throw e;
	    } catch (Exception e) {
	      throw new ServletException(e);
	    }
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		InputStream is = req.getInputStream();
		OutputStream os = res.getOutputStream();
	   String method = req.getParameter(method_def);
	   MicroHessianInput hessianInput = new MicroHessianInput(is);
	  // hessianInput.readString();
	   MicroHessianOutput hessianOutput = new MicroHessianOutput(os);
	   uuSkeleton.invoke(method, hessianInput, hessianOutput);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		      res.setStatus(500);
		      PrintWriter out = res.getWriter();
		      res.setContentType("text/html");
		      out.println("<h1>OUCenter - Requires POST</h1>");
		      return;
	}
//	@Override
//	public void service(ServletRequest request, ServletResponse response)
//			throws ServletException, IOException {
//			    HttpServletRequest req = (HttpServletRequest) request;
//			    HttpServletResponse res = (HttpServletResponse) response;
//			    if (!req.getMethod().equals("POST")) {
//				      res.setStatus(500);
//				      PrintWriter out = res.getWriter();
//				      res.setContentType("text/html");
//				      out.println("<h1>UUCenter - Requires POST</h1>");
//				      return;
//			    }
//			   String method = req.getParameter(method_def);
//			   MicroHessianInput hessianInput = new MicroHessianInput(req.getInputStream());
//			   hessianInput.readString();
//			   MicroHessianOutput hessianOutput = new MicroHessianOutput(res.getOutputStream());
//			   uuSkeleton.invoke(method, hessianInput, hessianOutput);
//	}

	private Class<?> loadClass(String className)throws ClassNotFoundException{
	    
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
	    if (loader != null)
	      return Class.forName(className, false, loader);
	    else
	      return Class.forName(className);
	  }

	  private void init(Object service)throws ServletException{
		  
	    if (! this.getClass().equals(OUServlet.class)) {
	    }
	    else if (service instanceof Service)
	      ((Service) service).init(getServletConfig());
	    else if (service instanceof Servlet)
	      ((Servlet) service).init(getServletConfig());
	  }	
	public Class<?> getServerAPI() {
		return serverAPI;
	}

	public void setServerAPI(Class<?> serverAPI) {
		this.serverAPI = serverAPI;
	}

	public Object getServerImpl() {
		return serverImpl;
	}

	public void setServerImpl(Object serverImpl) {
		this.serverImpl = serverImpl;
	}

	public OUSkeleton getUuSkeleton() {
		return uuSkeleton;
	}

	public void setUuSkeleton(OUSkeleton uuSkeleton) {
		this.uuSkeleton = uuSkeleton;
	}

	public String getMethod_def() {
		return method_def;
	}

	public void setMethod_def(String method_def) {
		this.method_def = method_def;
	}

}
