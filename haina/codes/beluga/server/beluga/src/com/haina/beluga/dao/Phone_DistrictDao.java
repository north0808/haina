package com.haina.beluga.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.haina.beluga.core.dao.BaseDao;
import com.haina.beluga.domain.Phone_District;
@Component
public class Phone_DistrictDao extends BaseDao<Phone_District,String> {
	
	public void saveAll(Collection<Phone_District> list) {
		Session session = getSession();
		int i = 0;
		for(Phone_District p_d :list){
			i++;
			create(p_d);
			if(i % 50 ==0){
				session.flush();
				session.clear();
			}
		}
	}

}
