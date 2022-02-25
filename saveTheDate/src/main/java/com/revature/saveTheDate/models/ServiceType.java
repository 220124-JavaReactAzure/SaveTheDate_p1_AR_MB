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
@Table(name="servicetypes")
@JsonIgnoreProperties(value = "service")
public class ServiceType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="service_type_id")
	private int id;
	
	@Column(name="serviceType")
	private String serviceType;
	
	@OneToMany(mappedBy = "serviceType", fetch = FetchType.EAGER)
	private List<Service> services;

	public ServiceType(int id, String serviceType) {
		super();
		this.id = id;
		this.serviceType = serviceType;
	}

	public ServiceType() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}


}
