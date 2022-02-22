package com.revature.saveTheDate.daos;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.saveTheDate.models.User;
import com.revature.saveTheDate.util.HibernateUtil;

public class UserDAO {
	
	public boolean addUser(User user) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(user);
			return true;
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public List<User> getAllUsers(){	
		try {
			Session session = HibernateUtil.getSession();
			List<User> user = session.createQuery("from User").list();
			return user;
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public User getUserById(int id){
			
		try {
			Session session = HibernateUtil.getSession();
			User user = session.get(User.class, id);
			return user;
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public void updateUserWithSessionMethod(User user) {
		
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.merge(user);
			transaction.commit();
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	//todo
	public void deleteUserType(int id) {
		try {
			Session session = HibernateUtil.getSession();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}


}
