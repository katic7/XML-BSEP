package com.ftn.accommodationservice.soap;

import com.ftn.accommodationservice.xsd.AccUnitPrice;
import com.ftn.accommodationservice.xsd.AccommodationObject;
import com.ftn.accommodationservice.xsd.AccommodationUnit;
import com.ftn.accommodationservice.xsd.AdditionalService;
import com.ftn.accommodationservice.xsd.Address;
import com.ftn.accommodationservice.xsd.Category;
import com.ftn.accommodationservice.xsd.Reservation;
import com.ftn.accommodationservice.xsd.Type;

public class Converter {
	
	public AccommodationObject convertAccommodation(com.ftn.accommodationservice.model.AccommodationObject acc) {
		AccommodationObject accObj = new AccommodationObject();
		Type tip = new Type();
		Category cat = new Category();
		Address adr = new Address();
		adr.setId(acc.getAddress().getId());
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
		accObj.setDaysToCancel(acc.getDaysToCancel());
		accObj.setDescription(acc.getDescription());
		accObj.setFreeCancelation(acc.isFreeCancelation());
		accObj.setName(acc.getName());
		accObj.setId(acc.getId());
		accObj.setType(tip);
		accObj.setCategory(cat);
		accObj.setAddress(adr);
		return accObj;
	}
	
	public AccommodationUnit convertAccUnit(com.ftn.accommodationservice.model.AccommodationUnit accU) {
		AccommodationUnit acc = new AccommodationUnit();
		AccUnitPrice acPrice = new AccUnitPrice();
		acPrice.setEndDate(accU.getPrice().getEndDate());
		acPrice.setStartDate(accU.getPrice().getStartDate());
		acPrice.setId(accU.getPrice().getId());
		acPrice.setPrice(accU.getPrice().getPrice());
		acc.setPrice(acPrice);
		acc.setBalcony(accU.isBalcony());
		acc.setDescription(accU.getDescription());
		acc.setNumberOfBeds(accU.getNumberOfBeds());
		acc.setId(accU.getId());
		acc.setRating(accU.getRating());
		acc.setAccObjectId(accU.getAccommodationObject().getId());
		return acc;
	}
	
	public Type covertType(com.ftn.accommodationservice.model.Type tip) {
		Type type = new Type();
		type.setId(tip.getId());
		type.setName(tip.getName());
		return type;
	}
	
	public Category convertCategory(com.ftn.accommodationservice.model.Category cat) {
		Category kat = new Category();
		kat.setId(cat.getId());
		kat.setName(cat.getName());
		return kat;
	}
	
	public Address convertAddress(com.ftn.accommodationservice.model.Address address) {
		Address adr = new Address();
		adr.setId(address.getId());
		adr.setLatitude(address.getLatitude());
		adr.setLongitude(address.getLongitude());
		adr.setPostalCode(address.getPostalCode());
		adr.setState(address.getState());
		adr.setStreet(address.getStreet());
		adr.setStreetNumber(address.getStreetNumber());
		adr.setTown(address.getTown());
		return adr;
	}
	
	public AdditionalService convertAdditionalService(com.ftn.accommodationservice.model.AdditionalService addit) {
		AdditionalService add = new AdditionalService();
		add.setId(addit.getId());
		add.setIncluded(addit.isIncluded());
		add.setName(addit.getName());
		add.setPrice(addit.getPrice());
		return add;
	}
	
	public AccUnitPrice convertAccUnitPrice(com.ftn.accommodationservice.model.AccUnitPrice accP) {
		AccUnitPrice acc = new AccUnitPrice();
		acc.setId(accP.getId());
		acc.setEndDate(accP.getEndDate());
		acc.setPrice(accP.getPrice());
		acc.setStartDate(accP.getStartDate());
		return acc;
	}
	
	public Reservation convertReservation(com.ftn.accommodationservice.model.Reservation reserv) {
		Reservation res = new Reservation();
		res.setId(reserv.getId());
		res.setActive(reserv.isActive());
		res.setAccUnitId(reserv.getAccommodationUnit().getId());
		res.setCompleted(reserv.isCompleted());
		res.setPrice(reserv.getPrice());
		res.setUserID(null);	//nema usera na agent bazi
		res.setBeginDate(reserv.getBeginDate());
		res.setEndDate(reserv.getEndDate());
		res.setReservationDate(reserv.getReservationDate());
		res.setAccUnitId(reserv.getAccommodationUnit().getId());
		return res;
	}
}
