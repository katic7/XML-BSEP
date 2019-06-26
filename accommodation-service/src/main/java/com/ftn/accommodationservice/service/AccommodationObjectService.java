package com.ftn.accommodationservice.service;

import java.util.List;

import com.ftn.accommodationservice.model.AccUnitPrice;
import com.ftn.accommodationservice.model.AccommodationObject;
import com.ftn.accommodationservice.model.AccommodationUnit;

public interface AccommodationObjectService {
	
	AccUnitPrice addNewPrice(AccUnitPrice acup);
	List<AccUnitPrice> getAllPrices();
	
	AccommodationUnit getOneAccUnit(Long id);
	AccommodationUnit AddNewAccUnit(AccommodationUnit acu);
	List<AccommodationUnit> getUnits();
	
	AccommodationObject getOneAccObj(Long id);
	
}