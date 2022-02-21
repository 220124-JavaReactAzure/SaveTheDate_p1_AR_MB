package com.revature.saveTheDate.web.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.saveTheDate.daos.AttendeeDAO;
import com.revature.saveTheDate.daos.DinnerDAO;
import com.revature.saveTheDate.daos.RoleDAO;
import com.revature.saveTheDate.daos.ServiceDAO;
import com.revature.saveTheDate.daos.ServiceTypeDAO;
import com.revature.saveTheDate.daos.UserDAO;
import com.revature.saveTheDate.daos.WeddingDAO;
import com.revature.saveTheDate.services.AttendeeServices;
import com.revature.saveTheDate.services.DinnerServices;
import com.revature.saveTheDate.services.RoleServices;
import com.revature.saveTheDate.services.ServiceServices;
import com.revature.saveTheDate.services.ServiceTypeServices;
import com.revature.saveTheDate.services.UserServices;
import com.revature.saveTheDate.services.WeddingServices;
import com.revature.saveTheDate.web.servlets.AttendeeServlet;
import com.revature.saveTheDate.web.servlets.DinnerServlet;
import com.revature.saveTheDate.web.servlets.RoleServlet;
import com.revature.saveTheDate.web.servlets.ServiceServlet;
import com.revature.saveTheDate.web.servlets.ServiceTypeServlet;
import com.revature.saveTheDate.web.servlets.UserServlet;
import com.revature.saveTheDate.web.servlets.WeddingServlet;

public class ContextLoaderListener implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		ObjectMapper mapper = new ObjectMapper();
		WeddingDAO weddingDAO = new WeddingDAO();
		UserDAO userDAO = new UserDAO();
		ServiceDAO serviceDAO = new ServiceDAO();
		DinnerDAO dinnerDAO = new DinnerDAO();
		ServiceTypeDAO serviceTypeDAO = new ServiceTypeDAO();
		AttendeeDAO attendeeDAO = new AttendeeDAO();
		RoleDAO roleDAO = new RoleDAO();
		
		
		WeddingServices weddingServices = new WeddingServices(weddingDAO);
		UserServices userServices = new UserServices(userDAO);
		ServiceServices serviceServices = new ServiceServices(serviceDAO);
		DinnerServices dinnerServices = new DinnerServices(dinnerDAO);
		ServiceTypeServices serviceTypeServices = new ServiceTypeServices(serviceTypeDAO);
		AttendeeServices attendeeServices = new AttendeeServices(attendeeDAO);
		RoleServices roleServices = new RoleServices(roleDAO);
		
		WeddingServlet weddingServlet = new WeddingServlet(weddingServices, mapper);
		UserServlet userServlet = new UserServlet(userServices, mapper);
		ServiceServlet serviceServlet = new ServiceServlet(serviceServices, mapper);
		DinnerServlet dinnerServlet = new DinnerServlet(dinnerServices, mapper);
		ServiceTypeServlet serviceTypeServlet = new ServiceTypeServlet(serviceTypeServices, mapper);
		AttendeeServlet attendeeServlet = new AttendeeServlet(attendeeServices, mapper);
		RoleServlet roleServlet = new RoleServlet(roleServices, mapper);
		
		ServletContext context = sce.getServletContext();
		context.addServlet("WeddingServlet", weddingServlet).addMapping("/weddings/*");
		context.addServlet("ServiceServlet", weddingServlet).addMapping("/services/*");
		context.addServlet("UserServlet", weddingServlet).addMapping("/users/*");
		context.addServlet("DinnerServlet", weddingServlet).addMapping("/dinners/*");
		context.addServlet("ServiceTypeServlet", weddingServlet).addMapping("/serviceTypes/*");
		context.addServlet("AttendeeServlet", weddingServlet).addMapping("/attendees/*");
		context.addServlet("RoleServlet", weddingServlet).addMapping("/roles/*");
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}
}
