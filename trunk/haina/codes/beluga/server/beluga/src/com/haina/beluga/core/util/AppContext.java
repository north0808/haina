package com.haina.beluga.core.util;
/**
 * 整合Struts框架时所需,此设计有待修改,
 * @author X_FU.
 *
 */
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {

	  private static AppContext instance;

	  private AbstractApplicationContext appContext;

	  public synchronized static AppContext getInstance() {
	    if (instance == null) {
	      instance = new AppContext();
	    }
	    return instance;
	  }
	  private AppContext() {
	    this.appContext = new ClassPathXmlApplicationContext(
	        "/applicationContext.xml");
	  }
	  public AbstractApplicationContext getAppContext() {
	    return appContext;
	  }
	}


