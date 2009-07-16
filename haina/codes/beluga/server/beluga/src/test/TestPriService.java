package test;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.haina.beluga.webservice.PriService;
import com.haina.beluga.webservice.data.hessian.HessianRemoteReturning;

/**
 * Simple test of the PersonDao
 */
public class TestPriService extends TestCase
{
    private ApplicationContext factory;
 
    public TestPriService(String s)
    {
        super(s);
        factory = new ClassPathXmlApplicationContext("test/test-applicationContext.xml");
    }
    
    public void testRegister() throws Exception {
    	HessianRemoteReturning h = getPriService().register("fuxiang14", "fuxiang12", "15901819285");
    	System.out.println(h);
    }
    public void testLogin() throws Exception {
    	HessianRemoteReturning h = getPriService().login("fuxiang1", "fuxiang1");
    	System.out.println(h);
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
   
}
