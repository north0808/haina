package com.haina.beluga.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.haina.beluga.core.dao.BaseDao;
import com.haina.beluga.domain.Weather;
@Component
public class WeatherDao extends BaseDao<Weather,String> implements IWeatherDao {

	@Override
	public void delAll() {
//	     List<Weather> weathers =  getModels(true);
//	     for(Weather w:weathers){
//	    	 delete(w);
//	     }
//		getHibernateTemplate().execute(new HibernateCallback() { 
//			public Object doInHibernate(Session session) throws HibernateException {
//				
//				System.out.println(session.getTransaction().wasCommitted());
////				session.getTransaction().begin();
//				 session.createQuery("delete from Weather").executeUpdate();
//				 System.out.println(session.getTransaction().wasCommitted());
//				 return null;
//				}
//		});
		getSession().createQuery("delete from Weather").executeUpdate();
	}

	@Override
	public Iterator<Weather> get7WeatherDatas(String cityCode) {
		Session session = getSession();
		Query query = session.createQuery("from Weather where weatherCityCode =:cityCode");
		query.setString("cityCode",cityCode);
// unuse
//		query.setCacheable(true);
//		query.setCacheRegion("com.haina.beluga.domain.Weather");
		return query.iterate();
	}

	@Override
	public void saveAll(List<Weather> list) {
		Session session = getSession();
		session.createQuery("delete from Weather").executeUpdate();
		for(int i = 0 ; i < list.size(); i++){
			create(list.get(i));
			if(i % 500 ==0){
				session.flush();
				session.clear();
			}
		}
	}

	
}
