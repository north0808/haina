package genericdaotest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.haina.beluga.dao.PhoneDistrictDao;
import com.haina.beluga.dao.Phone_DistrictDao;
import com.haina.beluga.domain.PhoneDistrict;
import com.haina.beluga.domain.Phone_District;
import com.haina.beluga.log.service.LogService;
import com.haina.beluga.service.IWeatherService;
import com.haina.beluga.service.WeatherService;

/**
 * Simple test of the PersonDao
 */
public class LogDaoTest extends TestCase
{
    private ApplicationContext factory;
 
    public LogDaoTest(String s)
    {
        super(s);
        factory = new ClassPathXmlApplicationContext("test/test-applicationContext.xml");
    }
    @Ignore
    public void testCrud() throws Exception
    {

        // Create
//    	LogService logService = getLogService();
//    	Log log = new Log();
//    	log.setVersion(new Long(0));
//    	log.set
//    	logService.create(log);

//        assertNotNull(log.getId());
//        String id = log.getId();
//        System.out.println(id);
        
//        restartSession();
        
//        logService.findAll(true);
//       Log log1 =  logService.load("402881e721af68a00121af68a1b40001");
//       System.out.println(log1.getHandle());
//       logService.findAll(true);
//       logService.findByPaginate(new LogCommand(), 0, 20);
//       logService.findByPaginate(new LogCommand(), 0, 20);
//       System.out.println(log1.getHandle());
//
//        restartSession();
//
//        // Read
//        Log foundLog = logService.read(id);
//        assertEquals(foundLog.getId(), log.getId());
//       
//        restartSession();
//        Log x = logService.load(id);
//        // Update
//        foundLog.setInfoClass("test");
//        logService.update(foundLog);
//        Log updatedLog = logService.read(id);
//        assertEquals("test", updatedLog.getInfoClass());
//
//        restartSession();
//
//        // Delete
//        logService.deleteById(id);
//        restartSession();
//        assertNull(logService.read(id));
//      
//        restartSession();
//        // find size
//        Long size = logService.findAllSize();
        //assertEquals(size.longValue(), 10);
    }
    
//    public void testCache() throws Exception
//    {
////    	LogService logService = getLogService();
////    	logService.findAll(true);
////    	logService.findAll(true);
//    }
    public void testWeather() throws Exception {
//    	getWeatherService().getLiveWeather("56672");
//    	getWeatherService().findAll(true);
    	
//    	List<WeatherDto> list = getWeatherService().get7Weatherdatas("112020");
//    	getWeatherService().get7Weatherdatas("112020");
//    	for(WeatherDto  dto:list){
//    		System.out.println(dto.getDate()+":"+dto.getWeatherType()+":"+dto.getHigh());
//    	}
    }
    public void testGenPhones() throws Exception {
    	Map<String, Phone_District> map = new HashMap<String, Phone_District>();
    	List<PhoneDistrict> list = getPhoneDistrictDao().getModels(true);
    	for(PhoneDistrict pd : list){
    		if(null == map.get(pd.getDistrictCity())){
    			String rs = ranges(pd);
    			Phone_District p_d = new Phone_District();
    			p_d.setRange(rs);
    			p_d.setDistrictCity(pd.getDistrictCity());
    			p_d.setDistrictNumber(pd.getDistrictNumber());
    			p_d.setDistrictProvince(pd.getDistrictProvince());
    			p_d.setFeeType(pd.getFeeType());
    			p_d.setPingyinCity(pd.getPingyinCity());
    			p_d.setUpdateFlg(pd.getUpdateFlg());
    			p_d.setWeatherCityCode(pd.getWeatherCityCode());
    			
    			map.put(pd.getDistrictCity(), p_d);
    		}else{
    			Phone_District p_d = map.get(pd.getDistrictCity());
    			
    			p_d.setRange(p_d.getRange()+ranges(pd));
    			p_d.setDistrictCity(pd.getDistrictCity());
    			p_d.setDistrictNumber(pd.getDistrictNumber());
    			p_d.setDistrictProvince(pd.getDistrictProvince());
    			p_d.setFeeType(pd.getFeeType());
    			p_d.setPingyinCity(pd.getPingyinCity());
    			p_d.setUpdateFlg(pd.getUpdateFlg());
    			p_d.setWeatherCityCode(pd.getWeatherCityCode());
//    			getPhone_DistrictDao().create(p_d);
    			map.put(pd.getDistrictCity(), p_d);
    		}
    	}
    	Collection<Phone_District>  p_dList =  map.values();
//    	getPhone_DistrictDao().saveAll(p_dList);
    	for   (Iterator<Phone_District>   iter   =   p_dList.iterator();   iter.hasNext();) {
    		getPhone_DistrictDao().create(iter.next());
//    		break;
    	}
    }
    private String ranges(PhoneDistrict pd){
    	if(pd.getRangeStart().equals(pd.getRangeEnd())){
    		return pd.getRangeEnd().toString()+",";
    	}else{
    		StringBuffer s = new StringBuffer();
    		for(long i = pd.getRangeStart(); i<=pd.getRangeEnd();  i ++ ){
    			s.append(i+",");
    		}
    		return s.toString();
    	}
    }
    protected void setUp() throws Exception
    {
    	
        openSession();
    }

    protected void tearDown() throws Exception
    {
        closeSession();
    }

    private void openSession()
    {
        SessionFactory sessionFactory = getSessionFactory();
        Session session = SessionFactoryUtils.getSession(sessionFactory, true);
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
    }

    private void closeSession()
    {
        SessionFactory sessionFactory = getSessionFactory();
        SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager.unbindResource(sessionFactory);
        sessionHolder.getSession().flush();
        sessionHolder.getSession().close();
        SessionFactoryUtils.releaseSession(sessionHolder.getSession(), sessionFactory);
    }

    private void restartSession()
    {
        closeSession();
        openSession();
    }

    private SessionFactory getSessionFactory()
    {
        return (SessionFactory) factory.getBean("sessionFactory");
    }

    private LogService getLogService()
    {
    	LogService logService = (LogService) factory.getBean("logService");
    	
        return logService;
    }
    private Phone_DistrictDao getPhone_DistrictDao()
    {
    	Phone_DistrictDao logService = (Phone_DistrictDao) factory.getBean("phone_DistrictDao");
    	
        return logService;
    }
    private PhoneDistrictDao getPhoneDistrictDao()
    {
    	PhoneDistrictDao logService = (PhoneDistrictDao) factory.getBean("phoneDistrictDao");
    	
        return logService;
    }
    private IWeatherService getWeatherService()
    {
    	WeatherService weatherService = (WeatherService) factory.getBean("weatherService");
    	
        return weatherService;
    }
}