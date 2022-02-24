package com.revature.saveTheDate.daos;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.saveTheDate.models.Role;
import com.revature.saveTheDate.util.HibernateUtil;

public class RoleDAO {

	public boolean addRole(Role role) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(role);
			return true;
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public List<Role> getAllRole(){	
		try {
			Session session = HibernateUtil.getSession();
			List<Role> role = session.createQuery("from Role").list();
			return role;
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public Role getRoleById(int id){
			
		try {
			Session session = HibernateUtil.getSession();
			Role role = session.get(Role.class, id);
			return role;
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public void updateRoleWithSessionMethod(Role role) {
		
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.merge(role);
			transaction.commit();
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	//todo
	public void deleteRoleById(int id) {
		try {
			Session session = HibernateUtil.getSession();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}


}
