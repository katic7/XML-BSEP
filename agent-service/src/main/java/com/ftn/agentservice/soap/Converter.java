package com.ftn.agentservice.soap;

import com.ftn.accommodationservice.xsd.AccUnitPrice;
import com.ftn.accommodationservice.xsd.AccommodationObject;
import com.ftn.accommodationservice.xsd.AccommodationUnit;
import com.ftn.accommodationservice.xsd.AdditionalService;
import com.ftn.accommodationservice.xsd.Address;
import com.ftn.accommodationservice.xsd.Category;
import com.ftn.accommodationservice.xsd.Reservation;
import com.ftn.accommodationservice.xsd.Type;
import com.ftn.agentservice.model.User;

public class Converter {

	public com.ftn.agentservice.model.Type covertType(Type tip) {
		com.ftn.agentservice.model.Type type = new com.ftn.agentservice.model.Type();
		type.setName(tip.getName());
		return type;
	}
	
	public com.ftn.agentservice.model.Category convertCategory(Category cat) {
		com.ftn.agentservice.model.Category kat = new com.ftn.agentservice.model.Category();
		kat.setName(cat.getName());
		return kat;
	}
	
	public com.ftn.agentservice.model.Address convertAddress(Address address) {
		com.ftn.agentservice.model.Address adr = new com.ftn.agentservice.model.Address();
		adr.setLatitude(address.getLatitude());
		adr.setLongitude(address.getLongitude());
		adr.setPostalCode(address.getPostalCode());
		adr.setState(address.getState());
		adr.setStreet(address.getStreet());
		adr.setStreetNumber(address.getStreetNumber());
		adr.setTown(address.getTown());
		return adr;
	}
	
	public com.ftn.agentservice.model.AdditionalService convertAdditionalService(AdditionalService addit) {
		com.ftn.agentservice.model.AdditionalService add = new com.ftn.agentservice.model.AdditionalService();
		add.setIncluded(addit.isIncluded());
		add.setName(addit.getName());
		add.setPrice(addit.getPrice());
		return add;
	}
	
	public com.ftn.agentservice.model.AccUnitPrice convertAccUnitPrice(AccUnitPrice accP) {
		com.ftn.agentservice.model.AccUnitPrice acc = new com.ftn.agentservice.model.AccUnitPrice();
		acc.setEndDate(accP.getEndDate());
		acc.setPrice(accP.getPrice());
		acc.setStartDate(accP.getStartDate());
		return acc;
	}
	
	public com.ftn.agentservice.model.AccommodationObject convertAccommodation(AccommodationObject acc) {
		com.ftn.agentservice.model.AccommodationObject accObj = new com.ftn.agentservice.model.AccommodationObject();
		com.ftn.agentservice.model.Type tip = new com.ftn.agentservice.model.Type();
		com.ftn.agentservice.model.Category cat = new com.ftn.agentservice.model.Category();
		com.ftn.agentservice.model.Address adr = new com.ftn.agentservice.model.Address();
		
		adr.setId(acc.getAddress().getId());
		System.out.println(acc.getAddress().getId() + "ADR ID AGE");
		adr.setLatitude(acc.getAddress().getLatitude());
		adr.setLongitude(acc.getAddress().getLongitude());
		adr.setPostalCode(acc.getAddress().getPostalCode());
		adr.setState(acc.getAddress().getState());
		adr.setStreet(acc.getAddress().getStreet());
		adr.setStreetNumber(acc.getAddress().getStreetNumber());
		adr.setTown(acc.getAddress().getTown());
		cat.setName(acc.getName());
		cat.setId(acc.getCategory().getId());
		tip.setName(acc.getType().getName());
		tip.setId(acc.getType().getId());
		accObj.setAddress(adr);
		accObj.setDaysToCancel(acc.getDaysToCancel());
		accObj.setDescription(acc.getDescription());
		accObj.setFreeCancelation(acc.isFreeCancelation());
		accObj.setName(acc.getName());
		accObj.setType(tip);
		accObj.setCategory(cat);
		return accObj;
	}
	
	public com.ftn.agentservice.model.AccommodationUnit convertAccUnit(AccommodationUnit accU) {
		com.ftn.agentservice.model.AccommodationUnit acc = new com.ftn.agentservice.model.AccommodationUnit();
		com.ftn.agentservice.model.AccUnitPrice acPrice = new com.ftn.agentservice.model.AccUnitPrice();
		com.ftn.agentservice.model.AccommodationObject ao = new com.ftn.agentservice.model.AccommodationObject();
		ao.setId(accU.getAccObjectId());
		acPrice.setEndDate(accU.getPrice().getEndDate());
		acPrice.setStartDate(accU.getPrice().getStartDate());
		acPrice.setId(accU.getPrice().getId());
		acPrice.setPrice(accU.getPrice().getPrice());
		acc.setPrice(acPrice);
		acc.setBalcony(accU.isBalcony());
		acc.setDescription(accU.getDescription());
		acc.setNumberOfBeds(accU.getNumberOfBeds());
		acc.setRating(accU.getRating());
		acc.setAccommodationObject(ao);
		return acc;
	}
	
	public com.ftn.agentservice.model.Reservation convertReservation(Reservation reserv) {
		com.ftn.agentservice.model.Reservation res = new com.ftn.agentservice.model.Reservation();
		com.ftn.agentservice.model.AccommodationUnit accU = new com.ftn.agentservice.model.AccommodationUnit();
		User us = new User();
		us.setId(Long.parseLong("1"));
		accU.setId(reserv.getAccUnitId());
		res.setActive(reserv.isActive());
		res.setAccommodationUnit(accU);
		res.setCompleted(reserv.isCompleted());
		res.setPrice(reserv.getPrice());
		res.setUser(null);	//nema usera na agent bazi
		res.setBeginDate(reserv.getBeginDate());
		res.setEndDate(reserv.getEndDate());
		res.setReservationDate(reserv.getReservationDate());
		return res;
	}
}
