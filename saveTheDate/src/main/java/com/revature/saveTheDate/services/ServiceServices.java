package com.revature.saveTheDate.services;

import java.util.List;

import com.revature.saveTheDate.daos.ServiceDAO;
import com.revature.saveTheDate.models.Service;

public class ServiceServices {


	private final ServiceDAO serviceDAO;

	public ServiceServices(ServiceDAO serviceDAO) {
		this.serviceDAO = serviceDAO;
	}

	public boolean addService(Service service) {

		return serviceDAO.addService(service);
	}

	public List<Service> getAllServices() {
		return serviceDAO.getAllService();

	}

	public Service getServiceById(int id) {

		return serviceDAO.getServiceById(id);
	}

	public void updateServiceWithSessionMethod(Service service) {

		serviceDAO.updateServiceWithSessionMethod(service);
	}
		
	public void deleteService (int id) {
		serviceDAO.deleteService(id);
	}

}
