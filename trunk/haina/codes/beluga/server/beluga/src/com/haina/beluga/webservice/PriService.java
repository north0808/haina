package com.haina.beluga.webservice;

import org.springframework.stereotype.Component;

/**
 * 个人服务Api接口实现类。<br/>
 * @author huangyongqiang
 * @version 1.0
 * @since 1.0
 * @date 2009-06-29
 */

@Component(value="priService")
public class PriService implements IPriService {
	
	private static final long serialVersionUID = 4943053100272442973L;

	@Override
	public void editLoginName(String email, String neoEmail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editMobile(String email, String oldMobile, String neoMobile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editPwd(String email, String oldPwd, String neoPwd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void login(String email, String pwd, String srcAppCode,
			String destAppCode, String userLoginIp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logout(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void register(String email, String password, String mobile,
			String description, String srcAppCode, String registerIp) {
		// TODO Auto-generated method stub
		
	}

}
