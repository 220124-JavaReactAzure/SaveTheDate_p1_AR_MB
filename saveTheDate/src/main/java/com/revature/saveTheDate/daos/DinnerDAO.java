package com.revature.saveTheDate.daos;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.saveTheDate.models.Dinner;
import com.revature.saveTheDate.util.HibernateUtil;

public class DinnerDAO {
	
	public boolean addDinner(Dinner dinner) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(dinner);
			return true;
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public List<Dinner> getAllDinners(){	
		try {
			Session session = HibernateUtil.getSession();
			List<Dinner> dinner = session.createQuery("from Dinner").list();
			return dinner;
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public Dinner getDinnerById(int id){
			
		try {
			Session session = HibernateUtil.getSession();
			Dinner dinner = session.get(Dinner.class, id);
			return dinner;
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public void updateDinnerWithSessionMethod(Dinner dinner) {
		
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.merge(dinner);
			transaction.commit();
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	//todo
	public void deleteDinnerById(int id) {
		try {
			Session session = HibernateUtil.getSession();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}


}
