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
@Table(name="service")
@JsonIgnoreProperties(value = "wedding")
public class Service {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="service_id")
	private int id;
	
	@Column(name="service_name")
	private String name;
	
	@Column(name="service_cost")
	private int cost;
	
	@ManyToOne
    @JoinColumn(name = "service_type_id")
	@JsonIgnoreProperties(value = { "service", "service_type_id" })
	private ServiceType serviceType;
	
	@OneToMany(mappedBy = "venue", fetch = FetchType.EAGER)
	private List<Wedding> weddingVenue;
	
	@OneToMany(mappedBy = "caterer", fetch = FetchType.EAGER)
	private List<Wedding> weddingCaterer;
	
	@OneToMany(mappedBy = "florist", fetch = FetchType.EAGER)
	private List<Wedding> weddingFlorist;
	
	@OneToMany(mappedBy = "photographer", fetch = FetchType.EAGER)
	private List<Wedding> weddingPhotographer;
	
	@OneToMany(mappedBy = "musician", fetch = FetchType.EAGER)
	private List<Wedding> weddingMusician;

	public Service(int id, String name, int cost, ServiceType serviceType) {
		super();
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.serviceType = serviceType;
	}
	
	

	public Service(int id, String name, int cost) {
		super();
		this.id = id;
		this.name = name;
		this.cost = cost;
	}



	public Service() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}


	

}
