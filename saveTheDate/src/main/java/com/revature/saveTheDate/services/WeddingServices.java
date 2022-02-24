package com.revature.saveTheDate.services;

import java.util.List;

import com.revature.saveTheDate.daos.WeddingDAO;
import com.revature.saveTheDate.models.Wedding;
import com.revature.saveTheDate.daos.ServiceDAO;
import com.revature.saveTheDate.services.ServiceServices;

public class WeddingServices {


	private final WeddingDAO weddingDAO;

	public WeddingServices(WeddingDAO weddingDAO) {
		this.weddingDAO = weddingDAO;
	}

	public boolean addWedding(Wedding wedding) {
		// Check if wedding under budget then check if all services available on that date 
		if (isWeddingValid(wedding) && isWeddingUnderBudget(wedding) && isWeddingVenueAvailable(wedding) && isWeddingCatererAvailable(wedding) && isWeddingFloristAvailable(wedding) &&
		isWeddingPhotographerAvailable(wedding) && isWeddingPhotographerAvailable(wedding)){
		weddingDAO.addWedding(wedding);
		return true;
		} else{
			return false;
		}
	}

	public List<Wedding> getAllWeddings() {
		return weddingDAO.getAllWeddings();

	}

	public Wedding getWeddingById(int id) {

		return weddingDAO.getWeddingById(id);
	}

	public void updateWeddingWithSessionMethod(Wedding wedding) {

		weddingDAO.updateWeddingWithSessionMethod(wedding);
	}
		
	
	public void deleteWeddingById (int id) {
		weddingDAO.deleteWeddingById(id);
	}

	public boolean isWeddingUnderBudget(Wedding wedding){
		if (ServiceServices.getServiceById(wedding.getVenueId()).getCost() + ServiceServices.getServiceById(wedding.getCatererId()).getCost() +
		ServiceServices.getServiceById(wedding.getFloristId()).getCost() + ServiceServices.getServiceById(wedding.getPhotographerId)().getCost() +
		ServiceServices.getServiceById(wedding.getMusicianId()).getCost() > wedding.getBudget()){
			return false;
		} else {
		return true;
		}
	}

	public boolean isWeddingVenueAvailable (Wedding wedding){
		for (Wedding weddingCheck : getAllWeddings()){
			if (weddingCheck.getDate().equals(wedding.getDate())){
				if (weddingCheck.getVenueId() == wedding.getVenueId()){
					return false;
				}
			}
		}
		return true;
	}

	public boolean isWeddingCatererAvailable (Wedding wedding){
		for (Wedding weddingCheck : getAllWeddings()){
			if (weddingCheck.getDate().equals(wedding.getDate())){
				if (weddingCheck.getCatererId() == wedding.getCatererId()){
					return false;
				}
			}
		}
		return true;
	}

	public boolean isWeddingFloristAvailable (Wedding wedding){
		for (Wedding weddingCheck : getAllWeddings()){
			if (weddingCheck.getDate().equals(wedding.getDate())){
				if (weddingCheck.getFloristId() == wedding.getFloristId()){
					return false;
				}
			}
		}
		return true;
	}

	public boolean isWeddingPhotographerAvailable (Wedding wedding){
		for (Wedding weddingCheck : getAllWeddings()){
			if (weddingCheck.getDate().equals(wedding.getDate())){
				if (weddingCheck.getPhotographerId() == wedding.getPhotographerId()){
					return false;
				}
			}
		}
		return true;
	}

	public boolean isWeddingMusicianAvailable (Wedding wedding){
		for (Wedding weddingCheck : getAllWeddings()){
			if (weddingCheck.getDate().equals(wedding.getDate())){
				if (weddingCheck.getMusicianId() == wedding.getMusicianId()){
					return false;
				}
			}
		}
		return true;
	}

	public boolean isWeddingValid (Wedding wedding){
		if (!(wedding.getDate().length() == 10)){
			return false;
		} else if (wedding.getBudget() <= 0){
			return false;
		} else{
			return true;
		}
		

	}

}
