package genericdaotest;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.haina.beluga.log.domain.Log;
import com.haina.beluga.log.service.LogService;

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
   
    public void testCrud() throws Exception
    {

        // Create
    	LogService logService = getLogService();
    	Log log = new Log();
    	log.setVersion(new Long(0));
    	logService.create(log);
//
//        assertNotNull(log.getId());
//        String id = log.getId();
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
        Long size = logService.findAllSize();
        //assertEquals(size.longValue(), 10);
    }
    
//    public void testCache() throws Exception
//    {
////    	LogService logService = getLogService();
////    	logService.findAll(true);
////    	logService.findAll(true);
//    }

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
}
