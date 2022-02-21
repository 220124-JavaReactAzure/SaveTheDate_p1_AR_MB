package com.revature.saveTheDate.daos;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.saveTheDate.models.Service;
import com.revature.saveTheDate.util.HibernateUtil;

public class ServiceDAO {
	
	public boolean addService(Service service) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(service);
			return true;
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public List<Service> getAllService(){	
		try {
			Session session = HibernateUtil.getSession();
			List<Service> service = session.createQuery("from Service").list();
			return service;
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public Service getServiceById(int id){
			
		try {
			Session session = HibernateUtil.getSession();
			Service service = session.get(Service.class, id);
			return service;
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public void updateServiceWithSessionMethod(Service service) {
		
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.merge(service);
			transaction.commit();
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	//todo
	public void deleteService(int id) {
		try {
			Session session = HibernateUtil.getSession();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}


}
