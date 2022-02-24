package com.revature.saveTheDate.services;

import java.util.List;

import com.revature.saveTheDate.daos.DinnerDAO;
import com.revature.saveTheDate.models.Dinner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DinnerServices {

	private final DinnerDAO dinnerDAO;
	private final Logger logger = LogManager.getLogger();

	public DinnerServices(DinnerDAO dinnerDAO) {
		this.dinnerDAO = dinnerDAO;
		logger.info("DinnerServices created");
	}

	public boolean addDinner(Dinner dinner) {
		logger.info("DinnerServices.addDinner was called for Dinner: " + dinner);
		return dinnerDAO.addDinner(dinner);
	}

	public List<Dinner> getAllDinners() {
		logger.info("DinnerServices.getAllDinners called");
		return dinnerDAO.getAllDinners();
	}

	public Dinner getDinnerById(int id) {
		logger.info("DinnerServices.getDinnerById was called for id: " + id);
		return dinnerDAO.getDinnerById(id);
	}

	public void updateDinnerWithSessionMethod(Dinner dinner) {
		logger.info("DinnerServices.updateDinnerWithSessionMethod was called for Dinner: " + dinner);
		dinnerDAO.updateDinnerWithSessionMethod(dinner);
	}
	
	public void deleteDinnerById (int id) {
		logger.info("DinnerServices.deleteDinnerById was called for id: " + id);
		dinnerDAO.deleteDinnerById(id);
	}

	
}
