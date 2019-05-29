package com.ftn.accommodationservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.accommodationservice.model.AccUnitPrice;
import com.ftn.accommodationservice.model.AccommodationObject;
import com.ftn.accommodationservice.model.AccommodationUnit;
import com.ftn.accommodationservice.model.Reservation;
import com.ftn.accommodationservice.service.AccommodationObjectService;

@RestController
@RequestMapping("/api/accobject")
public class AccommodationObjectController {
	
	@Autowired
	private AccommodationObjectService accommodationObjectService;
	
	@GetMapping("/getprices")
	public ResponseEntity<List<AccUnitPrice>> getAllPrices() {
		List<AccUnitPrice> prices = accommodationObjectService.getAllPrices();
		if(prices != null) {
			return new ResponseEntity<List<AccUnitPrice>>(prices, HttpStatus.OK);
		} 
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/addprice")
	public ResponseEntity<AccUnitPrice> addNewPrice(@RequestBody AccUnitPrice acup) {
		AccUnitPrice price = new AccUnitPrice();
		if(acup != null) {
			price.setStartDate(acup.getStartDate());
			price.setEndDate(acup.getEndDate());
			price.setPrice(acup.getPrice());
			
			//price.setAccommodationUnit(new AccommodationUnit());
			
			accommodationObjectService.addNewPrice(price);
			
			return new ResponseEntity<AccUnitPrice>(price, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/addunit/{accobject_id}")
	public ResponseEntity<AccommodationUnit> addNewAccUnit(@PathVariable Long accobject_id, @RequestBody AccommodationUnit acu) {
		AccommodationUnit newAcu = new AccommodationUnit();
		if(acu != null) {
			AccommodationObject aco = accommodationObjectService.getOneAccObj(accobject_id);
			newAcu.setAccommodationObject(aco);
			
			newAcu.setAdditionalServices(acu.getAdditionalServices());
			newAcu.setBalcony(acu.isBalcony());
			newAcu.setDescription(acu.getDescription());
			newAcu.setNumberOfBeds(acu.getNumberOfBeds());
			newAcu.setReservations(new ArrayList<Reservation>());
			newAcu.setPrice(acu.getPrice());
			newAcu.setRating(0);
			
			accommodationObjectService.AddNewAccUnit(newAcu);
			
			return new ResponseEntity<AccommodationUnit>(newAcu, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
