package com.revature.saveTheDate.services;

import java.util.List;

import com.revature.saveTheDate.daos.ServiceDAO;
import com.revature.saveTheDate.models.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceServices {


	private final ServiceDAO serviceDAO;
	private final Logger logger = LogManager.getLogger();

	public ServiceServices(ServiceDAO serviceDAO) {
		this.serviceDAO = serviceDAO;
		logger.info("ServiceServices created");
	}

	public boolean addService(Service service) {
		logger.info("ServiceServices.addService called for Service: " + service);
		return serviceDAO.addService(service);
	}

	public List<Service> getAllServices() {
		logger.info("ServiceServices.getAllServices called");
		return serviceDAO.getAllService();
	}

	public Service getServiceById(int id) {
		logger.info("ServiceServices.getServiceById called for id: " + id);
		return serviceDAO.getServiceById(id);
	}

	public void updateServiceWithSessionMethod(Service service) {
		logger.info("ServiceServices.updateServiceWithSessionMethod called for Service: " + service);
		serviceDAO.updateServiceWithSessionMethod(service);
	}
		
	public void deleteService (int id) {
		logger.info("ServiceServices.deleteService called for Service: " + service);
		serviceDAO.deleteService(id);
	}

}
