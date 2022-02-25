package com.revature.saveTheDate.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="dinner")
@JsonIgnoreProperties(value = "attendee")
public class Dinner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="dinner_id")
	private int id;
	
	@Column(name="dinner_name")
	private String dinnername;
	
	@OneToMany(mappedBy = "dinner", fetch = FetchType.EAGER)
	private List<Attendee> attendee;
	
	@OneToMany(mappedBy = "plusOneDinner", fetch = FetchType.EAGER)
	private List<Attendee> plusOneattendee;

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
