package com.revature.saveTheDate.daos;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.saveTheDate.models.Attendee;
import com.revature.saveTheDate.util.HibernateUtil;

public class AttendeeDAO {
	
	public boolean addAttendee(Attendee attendee) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(attendee);
			return true;
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public List<Attendee> getAllAttendee(){	
		try {
			Session session = HibernateUtil.getSession();
			List<Attendee> attendee = session.createQuery("from Attendee").list();
			return attendee;
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public Attendee getAttendeeById(int id){
			
		try {
			Session session = HibernateUtil.getSession();
			Attendee attendee = session.get(Attendee.class, id);
			return attendee;
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public void updateAttendeeWithSessionMethod(Attendee attendee) {
		
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.merge(attendee);
			transaction.commit();
		} catch(HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	//todo
	public void deleteAttendeeById(int id) {
		try {
			Session session = HibernateUtil.getSession();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

}
