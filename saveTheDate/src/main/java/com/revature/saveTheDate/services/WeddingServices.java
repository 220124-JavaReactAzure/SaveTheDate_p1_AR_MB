package com.revature.saveTheDate.services;

import java.util.List;

import com.revature.saveTheDate.daos.WeddingDAO;
import com.revature.saveTheDate.models.Wedding;

public class WeddingServices {


	private final WeddingDAO weddingDAO;

	public WeddingServices(WeddingDAO weddingDAO) {
		this.weddingDAO = weddingDAO;
	}

	public boolean addWedding(Wedding wedding) {

		return weddingDAO.addWedding(wedding);
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

}
