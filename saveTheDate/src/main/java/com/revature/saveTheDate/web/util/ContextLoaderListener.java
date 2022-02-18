package com.revature.saveTheDate.web.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.saveTheDate.daos.WeddingDAO;
import com.revature.saveTheDate.services.WeddingServices;
import com.revature.saveTheDate.web.servlets.WeddingServlet;

public class ContextLoaderListener implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		ObjectMapper mapper = new ObjectMapper();
		WeddingDAO weddingDAO = new WeddingDAO();
		//WeddingServices weddingServices = new WeddingServices(weddingDAO);
		//WeddingServices weddingServices = new WeddingServices(weddingDAO);
		//WeddingServices weddingServices = new WeddingServices(weddingDAO);
		//WeddingServices weddingServices = new WeddingServices(weddingDAO);
		//WeddingServices weddingServices = new WeddingServices(weddingDAO);
		//WeddingServices weddingServices = new WeddingServices(weddingDAO);
		//WeddingServices weddingServices = new WeddingServices(weddingDAO);
		
		WeddingServlet weddingServlet = new WeddingServlet(weddingServices, mapper);
		
		ServletContext context = sce.getServletContext();
		context.addServlet("WeddingServlet", weddingServlet).addMapping("/weddings/*");
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}
}
