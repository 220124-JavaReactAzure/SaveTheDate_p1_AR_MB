package com.revature.saveTheDate.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="attendee")
@JsonIgnoreProperties(value = "user")
public class Attendee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="attendee_id")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
	@JsonIgnoreProperties(value = { "user", "user_id" })
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wedding_id", referencedColumnName = "wedding_id")
	@JsonIgnoreProperties(value = { "wedding", "wedding_id" })
	private Wedding wedding;
	
	@Column(name="isattending")
	private boolean isAttending;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dinner_id")
	@JsonIgnoreProperties(value = { "attendee", "dinner_id" })
	private Dinner dinner;
	
	@Column(name="plus_one")
	private boolean plusOne;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plus_one_dinner_id")
	@JsonIgnoreProperties(value = { "attendees", "plus_one_dinner_id" })
	private Dinner plusOneDinner;

	public Attendee() {
		super();
	}

	public Attendee(int id, User user, Wedding wedding, boolean isAttending, Dinner dinner, boolean plusOne,
			Dinner plusOneDinner) {
		super();
		this.id = id;
		this.user = user;
		this.wedding = wedding;
		this.isAttending = isAttending;
		this.dinner = dinner;
		this.plusOne = plusOne;
		this.plusOneDinner = plusOneDinner;
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

	public Wedding getWedding() {
		return wedding;
	}

	public void setWedding(Wedding wedding) {
		this.wedding = wedding;
	}

	public boolean isAttending() {
		return isAttending;
	}

	public void setAttending(boolean isAttending) {
		this.isAttending = isAttending;
	}

	public Dinner getDinner() {
		return dinner;
	}

	public void setDinner(Dinner dinner) {
		this.dinner = dinner;
	}

	public boolean isPlusOne() {
		return plusOne;
	}

	public void setPlusOne(boolean plusOne) {
		this.plusOne = plusOne;
	}

	public Dinner getPlusOneDinner() {
		return plusOneDinner;
	}

	public void setPlusOneDinner(Dinner plusOneDinner) {
		this.plusOneDinner = plusOneDinner;
	}
	
}
