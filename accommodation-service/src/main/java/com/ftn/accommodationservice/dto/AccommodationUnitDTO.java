package com.ftn.accommodationservice.dto;

import java.util.List;
import com.ftn.accommodationservice.model.AccUnitPrice;
import com.ftn.accommodationservice.model.AccommodationObject;
import com.ftn.accommodationservice.model.AdditionalService;
import com.ftn.accommodationservice.model.Reservation;

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
	
}
