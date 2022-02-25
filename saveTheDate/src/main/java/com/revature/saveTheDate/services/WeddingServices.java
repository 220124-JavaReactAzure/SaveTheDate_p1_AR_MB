package com.revature.saveTheDate.services;

import java.util.List;

import com.revature.saveTheDate.daos.WeddingDAO;
import com.revature.saveTheDate.models.Wedding;

public class WeddingServices {


	private final WeddingDAO weddingDAO;
	private ServiceServices serviceServices;
	private final Logger logger = LogManager.getLogger();

	public WeddingServices(WeddingDAO weddingDAO) {
		this.weddingDAO = weddingDAO;
		logger.info("Weddding Services created");
	}

	public boolean addWedding(Wedding wedding) {
		logger.info("WeddingService.addWedding was called for wedding: " + wedding);
		// Check if wedding under budget then check if all services available on that date 
		if (isWeddingValid(wedding) && isWeddingUnderBudget(wedding) && isWeddingVenueAvailable(wedding) && isWeddingCatererAvailable(wedding) && isWeddingFloristAvailable(wedding) &&
		isWeddingPhotographerAvailable(wedding) && isWeddingPhotographerAvailable(wedding) && isWeddingVenueAVenue(wedding) && isWeddingCatererACaterer(wedding) && isWeddingFloristAFlorist(wedding) 
		&& isWeddingPhotographerAPhotographer(wedding) && isWeddingMucicianAMucician (wedding)){
		weddingDAO.addWedding(wedding);
			return true;
		} else{
			return false;
		}
	}

	public List<Wedding> getAllWeddings() {
		logger.info("Called WeddingService.getAllWeddings to return a list of all existing weddings");
		return weddingDAO.getAllWeddings();
	}

	public Wedding getWeddingById(int id) {
		logger.info("WeddingService.getWeddingById was called using the id: " + id);
		return weddingDAO.getWeddingById(id);
	}

	public void updateWeddingWithSessionMethod(Wedding wedding) {
		weddingDAO.updateWeddingWithSessionMethod(wedding);
		logger.info("WeddingService.updateWeddingWithSessionMethod was called for the wedding: " + wedding);
	}
		
	public void deleteWeddingById (int id) {
		weddingDAO.deleteWeddingById(id);
		logger.info("WeddingService.deleteWeddingById was called for the id: " + id);
	}

	public boolean isWeddingUnderBudget(Wedding wedding){
		logger.info("WeddingService.isWeddingUnderBudget was called for wedding: " + wedding);
		if (serviceServices.getServiceById(wedding.getVenueId()).getCost() + serviceServices.getServiceById(wedding.getCatererId()).getCost() +
		serviceServices.getServiceById(wedding.getFloristId()).getCost() + serviceServices.getServiceById(wedding.getPhotographerId()).getCost() +
		serviceServices.getServiceById(wedding.getMusicianId()).getCost() > wedding.getBudget()){
			return false;
		} else {
			return true;
		}
	}

	public boolean isWeddingVenueAvailable (Wedding wedding){
		logger.info("WeddingService.isWeddingVenueAvailable was called for wedding: " + wedding);
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
		logger.info("WeddingService.isWeddingCatererAvailable was called for wedding: " + wedding);
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
		logger.info("WeddingService.isWeddingFloristAvailable was called for wedding: " + wedding);
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
		logger.info("WeddingService.isWeddingPhotographerAvailable was called for wedding: " + wedding);
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
		logger.info("WeddingService.isWeddingMusicianAvailable was called for wedding: " + wedding);
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
		logger.info("WeddingService.isWeddingValid was called for wedding: " + wedding);
		if (!(wedding.getDate().length() == 10)){
			return false;
		} else if (wedding.getBudget() <= 0){
			return false;
		} else{
			return true;
		}
	}

	public boolean isWeddingVenueAVenue (Wedding wedding){
		logger.info("WeddingService.isWeddingVenueAVenue was called for wedding: " + wedding);
		if(serviceServices.getServiceById(wedding.getVenueId()).getServiceTypeId() == 1){
			return true;
		} else{
			return false;
		}
	}

	public boolean isWeddingCatererACaterer (Wedding wedding){
		logger.info("WeddingService.isWeddingVenueAVenue was called for wedding: " + wedding);
		if(serviceServices.getServiceById(wedding.getCatererId()).getServiceTypeId() == 2){
			return true;
		} else{
			return false;
		}
	}

	public boolean isWeddingFloristAFlorist (Wedding wedding){
		logger.info("WeddingService.isWeddingVenueAVenue was called for wedding: " + wedding);
		if(serviceServices.getServiceById(wedding.getFloristId()).getServiceTypeId() == 3){
			return true;
		} else{
			return false;
		}
	}

	public boolean isWeddingPhotographerAPhotographer (Wedding wedding){
		logger.info("WeddingService.isWeddingVenueAVenue was called for wedding: " + wedding);
		if(serviceServices.getServiceById(wedding.getPhotographerId()).getServiceTypeId() == 4){
			return true;
		} else{
			return false;
		}
	}

	public boolean isWeddingMucicianAMucician (Wedding wedding){
		logger.info("WeddingService.isWeddingVenueAVenue was called for wedding: " + wedding);
		if(serviceServices.getServiceById(wedding.getMusicianId()).getServiceTypeId() == 5){
			return true;
		} else{
			return false;
		}
	}

}
