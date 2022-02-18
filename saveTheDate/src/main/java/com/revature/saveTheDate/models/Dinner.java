package com.revature.saveTheDate.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dinner")
public class Dinner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="dinner_id")
	private int id;
	
	@Column(name="dinner_name")
	private String dinnername;

	public Dinner(int id, String dinnername) {
		super();
		this.id = id;
		this.dinnername = dinnername;
	}

	public Dinner() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDinnername() {
		return dinnername;
	}

	public void setDinnername(String dinnername) {
		this.dinnername = dinnername;
	}
	

}
