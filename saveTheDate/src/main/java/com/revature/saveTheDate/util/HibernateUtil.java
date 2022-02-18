package com.revature.saveTheDate.util;

import java.io.IOException;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.revature.saveTheDate.models.Attendee;
import com.revature.saveTheDate.models.Dinner;
import com.revature.saveTheDate.models.Role;
import com.revature.saveTheDate.models.Service;
import com.revature.saveTheDate.models.ServiceType;
import com.revature.saveTheDate.models.User;
import com.revature.saveTheDate.models.Wedding;


public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static Session session;
	
	public static Session getSession() throws IOException{
		if(sessionFactory == null) {
			Configuration configuration = new Configuration();
			Properties props = new Properties();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			props.load(loader.getResourceAsStream("hibernate.properties"));
			
			// Add properties to our configuration
			configuration.setProperties(props);
			
			configuration.addAnnotatedClass(Wedding.class);
			configuration.addAnnotatedClass(Service.class);
			configuration.addAnnotatedClass(Role.class);
			configuration.addAnnotatedClass(ServiceType.class);
			configuration.addAnnotatedClass(User.class);
			configuration.addAnnotatedClass(Dinner.class);
			configuration.addAnnotatedClass(Attendee.class);
			
			
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		}
		
		if(session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}
	
	public static void closeSession() {
		session.close();
		session = null;
	}
}
