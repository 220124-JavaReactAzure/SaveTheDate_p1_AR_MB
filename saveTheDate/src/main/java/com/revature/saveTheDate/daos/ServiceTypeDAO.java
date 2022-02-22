package com.revature.saveTheDate.daos;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.saveTheDate.models.ServiceType;
import com.revature.saveTheDate.util.HibernateUtil;

public class ServiceTypeDAO {
	
	public boolean addServiceType(ServiceType serviceType) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(serviceType);
			return true;
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public List<ServiceType> getAllServiceTypes(){	
		try {
			Session session = HibernateUtil.getSession();
			List<ServiceType> serviceType = session.createQuery("from ServiceType").list();
			return serviceType;
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public ServiceType getServiceTypeById(int id){
			
		try {
			Session session = HibernateUtil.getSession();
			ServiceType serviceType = session.get(ServiceType.class, id);
			return serviceType;
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public void updateServiceTypeWithSessionMethod(ServiceType serviceType) {
		
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.merge(serviceType);
			transaction.commit();
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	//todo
	public void deleteServiceTypeById(int id) {
		try {
			Session session = HibernateUtil.getSession();
			session.delete(id);

		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

}
