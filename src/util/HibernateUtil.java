package util;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.jmx.StatisticsService;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			// FIXME Deprecated????

			// for JConsole (Hibernate monitoring)
			StatisticsService statsMBean = new StatisticsService();
			statsMBean.setSessionFactory(sessionFactory);
			statsMBean.setStatisticsEnabled(true);

			MBeanServer mBeanServer = ManagementFactory
					.getPlatformMBeanServer();
			mBeanServer.registerMBean(statsMBean, new ObjectName(
					"Hibernate:application=Statistics"));

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	// TODO check?
	public static Session getSession() {
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return session;
	}

}