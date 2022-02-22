package com.revature.saveTheDate.services;

import java.util.List;

import com.revature.saveTheDate.daos.ServiceTypeDAO;
import com.revature.saveTheDate.models.ServiceType;


public class ServiceTypeServices {

private final ServiceTypeDAO serviceTypeDAO;
	
	public ServiceTypeServices(ServiceTypeDAO serviceTypeDAO) {
		this.serviceTypeDAO = serviceTypeDAO;
	}
	
	public boolean addServiceType(ServiceType serviceType) {
		
		return serviceTypeDAO.addServiceType(serviceType);
	}
	
	public List<ServiceType> getAllServiceTypes(){
		return serviceTypeDAO.getAllServiceTypes();
		
	}
	
	public ServiceType getServiceTypeById(int id){
		
		return serviceTypeDAO.getServiceTypeById(id);
	}
	
	
	public void updateServiceTypeWithSessionMethod(ServiceType serviceType) {
		
		serviceTypeDAO.updateServiceTypeWithSessionMethod(serviceType);
	}
	
	/*public void updateDirectorWithHQL(ServiceType serviceType) {
		
		serviceTypeDAO.updateServiceTypeWithHQL(serviceType);
	}*/
	
	public void deleteServiceTypeById(int id) {
		serviceTypeDAO.deleteServiceTypeById(id);
	}
}
