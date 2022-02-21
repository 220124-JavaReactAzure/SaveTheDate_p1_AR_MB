package com.revature.saveTheDate.daos;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.saveTheDate.models.Wedding;
import com.revature.saveTheDate.util.HibernateUtil;


public class WeddingDAO {
	
	public boolean addWedding(Wedding wedding) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(wedding);
			return true;
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public List<Wedding> getAllWeddings(){	
		try {
			Session session = HibernateUtil.getSession();
			List<Wedding> weddings = session.createQuery("from Wedding").list();
			return weddings;
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public Wedding getWeddingById(int id){
			
		try {
			Session session = HibernateUtil.getSession();
			Wedding wedding = session.get(Wedding.class, id);
			return wedding;
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public void updateWeddingWithSessionMethod(Wedding wedding) {
		
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.merge(wedding);
			transaction.commit();
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	/*public void updateWeddingWithHQL(Wedding wedding) {
		
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			
			//Query query = session.createQuery("update Wedding set email='" + wedding.getEmail() + "', first_name='")
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}*/
	
	public void deleteWedding(int id) {
		try {
			Session session = HibernateUtil.getSession();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

}
