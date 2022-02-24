package com.revature.saveTheDate.services;

import java.util.List;

import com.revature.saveTheDate.daos.ServiceTypeDAO;
import com.revature.saveTheDate.models.ServiceType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ServiceTypeServices {

	private final ServiceTypeDAO serviceTypeDAO;
	private final Logger logger = LogManager.getLogger();
	
	public ServiceTypeServices(ServiceTypeDAO serviceTypeDAO) {
		this.serviceTypeDAO = serviceTypeDAO;
		logger.info("ServiceTypeService created");
	}
	
	public boolean addServiceType(ServiceType serviceType) {
		logger.info("ServiceTypeServices.addServiceType was called for ServiceType: " + serviceType);
		return serviceTypeDAO.addServiceType(serviceType);
	}
	
	public List<ServiceType> getAllServiceTypes(){
		logger.info("ServiceTypeServices.getAllServiceTypes was called");
		return serviceTypeDAO.getAllServiceTypes();
	}
	
	public ServiceType getServiceTypeById(int id){
		logger.info("ServiceTypeServices.getServiceTypeById was called for id: " + id);
		return serviceTypeDAO.getServiceTypeById(id);
	}
	
	public void updateServiceTypeWithSessionMethod(ServiceType serviceType) {
		logger.info("ServiceTypeServices.updateServiceTypeWithSessionMethod was called for ServiceType: " + serviceType);
		serviceTypeDAO.updateServiceTypeWithSessionMethod(serviceType);
	}
	
	/*public void updateDirectorWithHQL(ServiceType serviceType) {
		
		serviceTypeDAO.updateServiceTypeWithHQL(serviceType);
	}*/
	
	public void deleteServiceTypeById(int id) {
		logger.info("ServiceTypeServices.deleteServiceTypeById was called for id: " + id);
		serviceTypeDAO.deleteServiceTypeById(id);
	}
}
