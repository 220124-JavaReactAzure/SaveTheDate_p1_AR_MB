package com.revature.saveTheDate.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wedding")
public class Wedding {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="wedding_id")
	private int id;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="wedding_date")
	private String date;
	
	@Column(name="budget")
	private int budget;
	
	@Column(name="venue_id")
	private int venueId;
	
	@Column(name="caterer_id")
	private int catererId;
	
	@Column(name="florist_id")
	private int floristId;
	
	@Column(name="photographer_id")
	private int photographerId;
	
	@Column(name="musician_id")
	private int musicianId;

	public Wedding(int id, int userId, String date, int budget, int venueId, int catererId, int floristId,
			int photographerId, int musicianId) {
		super();
		this.id = id;
		this.userId = userId;
		this.date = date;
		this.budget = budget;
		this.venueId = venueId;
		this.catererId = catererId;
		this.floristId = floristId;
		this.photographerId = photographerId;
		this.musicianId = musicianId;
	}

	public Wedding() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getVenueId() {
		return venueId;
	}

	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}

	public int getCatererId() {
		return catererId;
	}

	public void setCatererId(int catererId) {
		this.catererId = catererId;
	}

	public int getFloristId() {
		return floristId;
	}

	public void setFloristId(int floristId) {
		this.floristId = floristId;
	}

	public int getPhotographerId() {
		return photographerId;
	}

	public void setPhotographerId(int photographerId) {
		this.photographerId = photographerId;
	}

	public int getMusicianId() {
		return musicianId;
	}

	public void setMusicianId(int musicianId) {
		this.musicianId = musicianId;
	}

	
}
