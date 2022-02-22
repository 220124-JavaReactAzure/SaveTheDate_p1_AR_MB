package com.revature.saveTheDate.services;

import java.util.List;

import com.revature.saveTheDate.daos.DinnerDAO;
import com.revature.saveTheDate.models.Dinner;

public class DinnerServices {

	private final DinnerDAO dinnerDAO;

	public DinnerServices(DinnerDAO dinnerDAO) {
		this.dinnerDAO = dinnerDAO;
	}

	public boolean addDinner(Dinner dinner) {

		return dinnerDAO.addDinner(dinner);
	}

	public List<Dinner> getAllDinners() {
		return dinnerDAO.getAllDinners();

	}

	public Dinner getDinnerById(int id) {

		return dinnerDAO.getDinnerById(id);
	}

	public void updateDinnerWithSessionMethod(Dinner dinner) {

		dinnerDAO.updateDinnerWithSessionMethod(dinner);
	}
	
	public void deleteDinner (int id) {
		dinnerDAO.deleteDinner(id);
	}

	
}
