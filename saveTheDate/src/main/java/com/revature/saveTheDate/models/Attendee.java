package com.revature.saveTheDate.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Attendee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="attendee_id")
	private int id;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="wedding_id")
	private int weddingId;
	
	@Column(name="isattending")
	private boolean isAttending;
	
	@Column(name="dinner_id")
	private int dinnerId;
	
	@Column(name="plus_one")
	private boolean plusOne;
	
	@Column(name="pluse_one_dinner_id")
	private int plusOneDinnerId;

}
