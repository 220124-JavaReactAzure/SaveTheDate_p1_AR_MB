package com.revature.saveTheDate.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="wedding")
public class Wedding {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="wedding_id")
	private int id;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	private User user;
	
	@Column(name="wedding_date")
	private String date;
	
	@Column(name="budget")
	private int budget;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "venue_id", referencedColumnName = "service_id")
	@JsonIgnoreProperties(value = { "serviceType", "venue_id" })
	private Service venue;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "caterer_id", referencedColumnName = "service_id")
	@JsonIgnoreProperties(value = { "serviceType", "caterer_id" })
	private Service caterer;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "florist_id", referencedColumnName = "service_id")
	@JsonIgnoreProperties(value = { "serviceType", "florist_id" })
	private Service florist;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "photographer_id", referencedColumnName = "service_id")
	@JsonIgnoreProperties(value = { "serviceType", "photographer_id" })
	private Service photographer;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "musician_id", referencedColumnName = "service_id")
	@JsonIgnoreProperties(value = { "serviceType", "musician_id" })
	private Service musician;
	
	
	@OneToMany(mappedBy = "wedding", fetch = FetchType.EAGER)
	private List<Attendee> attendee;


	public Wedding() {
		super();
	}
	
	public Wedding(int id, User user, String date, int budget, Service venue, Service caterer, Service florist,
			Service photographer, Service musician) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.budget = budget;
		this.venue = venue;
		this.caterer = caterer;
		this.florist = florist;
		this.photographer = photographer;
		this.musician = musician;
	}

	public Wedding(int id, String date, int budget) {
		super();
		this.id = id;
		this.date = date;
		this.budget = budget;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	

	public Service getVenue() {
		return venue;
	}

	public void setVenue(Service venue) {
		this.venue = venue;
	}

	public Service getCaterer() {
		return caterer;
	}

	public void setCaterer(Service caterer) {
		this.caterer = caterer;
	}

	public Service getFlorist() {
		return florist;
	}

	public void setFlorist(Service florist) {
		this.florist = florist;
	}

	public Service getPhotographer() {
		return photographer;
	}

	public void setPhotographer(Service photographer) {
		this.photographer = photographer;
	}

	public Service getMusician() {
		return musician;
	}

	public void setMusician(Service musician) {
		this.musician = musician;
	}
}
