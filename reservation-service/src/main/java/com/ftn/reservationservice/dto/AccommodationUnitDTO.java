package com.ftn.reservationservice.dto;

import java.util.List;

import com.ftn.reservationservice.model.AccUnitPrice;
import com.ftn.reservationservice.model.AccommodationObject;
import com.ftn.reservationservice.model.AccommodationUnit;
import com.ftn.reservationservice.model.AdditionalService;
import com.ftn.reservationservice.model.Reservation;


public class AccommodationUnitDTO {

	protected long id;
    protected int numberOfBeds;
    protected boolean balcony;
    protected AccUnitPrice price;
    protected String description;
    protected double rating;
    protected String image;
    protected List<AdditionalService> additionalServices;
    private AccommodationObject accommodationObject;
    private List<Reservation> reservations;
    
    
	public AccommodationUnitDTO(long id, int numberOfBeds, boolean balcony, AccUnitPrice price, String description,
			double rating, String image, List<AdditionalService> additionalServices,
			AccommodationObject accommodationObject, List<Reservation> reservations) {
		super();
		this.id = id;
		this.numberOfBeds = numberOfBeds;
		this.balcony = balcony;
		this.price = price;
		this.description = description;
		this.rating = rating;
		this.image = image;
		this.additionalServices = additionalServices;
		this.accommodationObject = accommodationObject;
		this.reservations = reservations;
	}
    
	public AccommodationUnitDTO(AccommodationUnit au) {
		this.id = au.getId();
		this.numberOfBeds = au.getNumberOfBeds();
		this.balcony = au.isBalcony();
		this.price = au.getPrice();
		this.description = au.getDescription();
		this.rating = au.getRating();
		this.additionalServices = au.getAdditionalServices();
		this.accommodationObject = au.getAccommodationObject();
		this.reservations = au.getReservations();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public boolean isBalcony() {
		return balcony;
	}

	public void setBalcony(boolean balcony) {
		this.balcony = balcony;
	}

	public AccUnitPrice getPrice() {
		return price;
	}

	public void setPrice(AccUnitPrice price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<AdditionalService> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(List<AdditionalService> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public AccommodationObject getAccommodationObject() {
		return accommodationObject;
	}

	public void setAccommodationObject(AccommodationObject accommodationObject) {
		this.accommodationObject = accommodationObject;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
    
	
}
