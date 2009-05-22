package com.oucenter.log.weave;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

import com.oucenter.core.util.Constants;
import com.oucenter.core.util.SafeMapUtil;

public class LogBeforeAdvice implements MethodBeforeAdvice {


	
	public void before(Method method, Object[] arg, Object object) throws Throwable {
		//System.out.println("***********"+object.getClass().getSimpleName()+"----------");
		long startTime = System.currentTimeMillis();
		SafeMapUtil.set(Constants.STARTTIME, startTime);
		//System.out.println("startTime:"+System.currentTimeMillis());
	}

}
