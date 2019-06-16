package com.ftn.accommodationservice.dto;

import java.math.BigInteger;

import com.ftn.accommodationservice.model.Address;

public class AddressDTO {
	
	private Long id;
	private double longitude;
	private double latitude;
	private String state;
	private String town;
	private String street;
	private BigInteger streetNumber;
	private BigInteger postalCode;
	
	public AddressDTO(Address a) {
		this.id = a.getId();
		this.latitude = a.getLatitude();
		this.longitude =  a.getLongitude();
		this.postalCode = a.getPostalCode();
		this.state = a.getState();
		this.town = a.getTown();
		this.street = a.getStreet();
		this.streetNumber = a.getStreetNumber();
	}
	
	public AddressDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AddressDTO(Long id, double longitude, double latitude, String state, String town, String street,
			BigInteger streetNumber, BigInteger postalCode) {
		super();
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.state = state;
		this.town = town;
		this.street = street;
		this.streetNumber = streetNumber;
		this.postalCode = postalCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public BigInteger getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(BigInteger streetNumber) {
		this.streetNumber = streetNumber;
	}
	public BigInteger getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(BigInteger postalCode) {
		this.postalCode = postalCode;
	}
	
	
}
