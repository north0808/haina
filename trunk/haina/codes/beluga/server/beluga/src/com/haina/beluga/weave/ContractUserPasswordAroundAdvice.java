package com.haina.beluga.weave;

import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.haina.beluga.core.util.DESUtil;
import com.haina.beluga.domain.ContactUser;

/**
 * 用户密码编码解码AOP拦截器。<br/>
 * @author huangyongqiang
 * @version 1.0
 * @since 1.0
 * @date 2009-06-15
 */

@Component(value="contractUserPasswordAroundAdvice")
public class ContractUserPasswordAroundAdvice implements MethodInterceptor, InitializingBean {

	/*需要对对密码编码的方法。*/
	private String[] encryptPasswordMethod;
	
	/*需要对对密码编码的方法。*/
	private String[] decryptPasswordMethod;
	
	@SuppressWarnings("unchecked")
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String methodName=invocation.getMethod().getName();
		for(String m:decryptPasswordMethod) {
			if(m.equals(methodName)) {
				Object retObj=invocation.proceed();
				if(retObj.getClass().equals(List.class)) {
					List<ContactUser> list=(List<ContactUser>)retObj;
					if(!list.isEmpty()) {
						for(ContactUser user:list) {
							user.setPassword(DESUtil.decrypt(user.getPassword()));
						}
					}
				} else if(retObj.getClass().equals(ContactUser.class)) {
					ContactUser contactUser=(ContactUser)retObj;
					contactUser.setPassword(DESUtil.decrypt(contactUser.getPassword()));
				}
				break;
			}
		}
		for(String m:encryptPasswordMethod) {
			if(m.equals(methodName)) {
				Object[] args=invocation.getArguments();
				if(args!=null && args.length>0) {
					for(Object o:args) {
						if(o!=null && o.getClass().equals(ContactUser.class)) {
							ContactUser contactUser=(ContactUser)o;
							contactUser.setPassword(DESUtil.encrypt(contactUser.getPassword()));
						}
					}
				}
				break;
			}
		}
		return invocation.proceed();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		encryptPasswordMethod=new String[]{"create","update","saveOrUpdate"};
		decryptPasswordMethod=new String[]{
				"read","load","getModels","getContactUserByLoginName","getContactUserByMobile"};
	}


}
