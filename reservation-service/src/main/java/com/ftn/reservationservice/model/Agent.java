package com.ftn.reservationservice.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "agents")
@PrimaryKeyJoinColumn(name="id", referencedColumnName="id")
public class Agent extends User{

	private String pib;
	
	@OneToOne
	private AccommodationObject accObj;
	

	public Agent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Agent(String email, String password, Set<Role> roles) {
		super(email, password, roles);
		// TODO Auto-generated constructor stub
	}

	
	
	public Agent(User usr) {
		this.setAddressId(usr.getAddressId());
		this.setEmail(usr.getEmail());
		this.setEnabled(usr.isEnabled());
		this.setId(usr.getId());
		this.setName(usr.getName());
		this.setNonLocked(usr.isNonLocked());
		this.setPassword(usr.getPassword());
		this.setSurname(usr.getSurname());
		this.setRoles(usr.getRoles());
	}
	
	
	public Agent(String pib, AccommodationObject accObj) {
		super();
		this.pib = pib;
		this.accObj = accObj;
	}

	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}

	public AccommodationObject getAccObj() {
		return accObj;
	}

	public void setAccObj(AccommodationObject accObj) {
		this.accObj = accObj;
	}
	
}