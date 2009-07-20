package genericdaotest;

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
import com.haina.beluga.log.service.LogService;
import com.haina.beluga.service.IPhoneDistrictService;
import com.haina.beluga.service.IWeatherService;
import com.haina.beluga.service.PhoneDistrictService;
import com.haina.beluga.service.WeatherService;
import com.haina.beluga.webservice.PriService;
import com.haina.beluga.webservice.data.hessian.HessianRemoteReturning;

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
//    public void testCrud() throws Exception
//    {

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
//    }
    
//    public void testCache() throws Exception
//    {
////    	LogService logService = getLogService();
////    	logService.findAll(true);
////    	logService.findAll(true);
//    }
//    public void testWeather() throws Exception {
//    	getWeatherService().getLiveWeather("56672");
//    	getWeatherService().findAll(true);
    	
//    	List<WeatherDto> list = getWeatherService().get7Weatherdatas("112020");
//    	getWeatherService().get7Weatherdatas("112020");
//    	for(WeatherDto  dto:list){
//    		System.out.println(dto.getDate()+":"+dto.getWeatherType()+":"+dto.getHigh());
//    	}
//    }
//    public void testPD() throws Exception {
//    	getPhoneDistrictDao().getModels(true);
//    	Iterator<PhoneDistrict> iterator = getPhoneDistrictDao().getPhoneDistrictsByUpdateFlg(0);
//    	int i = 0;
//    	while(iterator.hasNext()){
//			PhoneDistrict w = iterator.next();
//			System.out.println(w.getDistrictCity()+i++);
//		}
//    	getPhoneDistrictService().getOrUpdatePhoneDistricts(1);
//    }
    public void testPri() throws Exception {
//    	getPhoneDistrictDao().getModels();
    	HessianRemoteReturning hess  =  getPhoneDistrictService().getOrUpdatePhoneDistricts(2);
    		System.out.println(hess);
    	for(int i = 0 ; i < 10; i++)
    	 getPhoneDistrictDao().getWeatherCityCodes();
    	
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
    
    private PriService getPriService()
    {
    	PriService priService = (PriService) factory.getBean("priService");
    	
        return priService;
    }
    private LogService getLogService()
    {
    	LogService logService = (LogService) factory.getBean("logService");
    	
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
    private IPhoneDistrictService getPhoneDistrictService()
    {
    	PhoneDistrictService phoneDistrictService = (PhoneDistrictService) factory.getBean("phoneDistrictService");
    	
        return phoneDistrictService;
    }
}
