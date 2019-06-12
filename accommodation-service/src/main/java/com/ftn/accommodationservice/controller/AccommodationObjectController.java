package com.ftn.accommodationservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.accommodationservice.dto.AccommodationObjectDTO;
import com.ftn.accommodationservice.model.AccUnitPrice;
import com.ftn.accommodationservice.model.AccommodationObject;
import com.ftn.accommodationservice.model.AccommodationUnit;
import com.ftn.accommodationservice.model.AdditionalService;
import com.ftn.accommodationservice.model.Reservation;
import com.ftn.accommodationservice.repository.AccommodationObjectRepository;
import com.ftn.accommodationservice.repository.AdditionalServiceRepository;
import com.ftn.accommodationservice.service.AccommodationObjectService;

@RestController
@RequestMapping("/api/accobject")
public class AccommodationObjectController {
	
	@Autowired
	private AccommodationObjectService accommodationObjectService;
	
	@Autowired
	private AccommodationObjectRepository accommodationObjectRepository;
	
	@Autowired
	private AdditionalServiceRepository additonalRepo;
	
	@PreAuthorize("hasAuthority('AddPrice')")
	@GetMapping("/getprices")
	public ResponseEntity<List<AccUnitPrice>> getAllPrices() {
		List<AccUnitPrice> prices = accommodationObjectService.getAllPrices();
		if(prices != null) {
			System.out.println("dosao");
			return new ResponseEntity<List<AccUnitPrice>>(prices, HttpStatus.OK);
		} 

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PreAuthorize("hasAuthority('AddPrice')")
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
	
	@GetMapping("/getOne/{id}")
	public AccommodationObjectDTO getObj(@PathVariable Long id){
		AccommodationObject acc = accommodationObjectService.getOneAccObj(id);
		AccommodationObjectDTO accDto = new AccommodationObjectDTO(acc.getId(),acc.getName(), acc.getAddressId(), acc.getDescription(), acc.getCategoryId(), acc.isFreeCancelation(), acc.getDaysToCancel(), acc.getTypeId());
		return accDto;
	}
	
	@GetMapping("/getAllwOutAgent")
	public List<AccommodationObjectDTO> getFreeAcc(){
		List<AccommodationObject> svi = accommodationObjectRepository.findAll();
		List<AccommodationObject> lista= accommodationObjectRepository.objWithAgents();
		List<AccommodationObjectDTO> povratnaLista = new ArrayList<AccommodationObjectDTO>();
		for(AccommodationObject acc : lista) {
			svi.remove(acc);
		}
		for(AccommodationObject acc : svi) {
			povratnaLista.add(new AccommodationObjectDTO(acc.getId(),acc.getName(), acc.getAddressId(), acc.getDescription(), acc.getCategoryId(), acc.isFreeCancelation(), acc.getDaysToCancel(), acc.getTypeId()));
		}
		
		
	return povratnaLista;
	}
	
	@PreAuthorize("hasAuthority('AddAccUnit')")
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
	
	/*@GetMapping("/getOneUnit/{id}")
	public ResponseEntity<AccommodationUnit> getOneAccUnit(@PathVariable Long id) {
		AccommodationUnit acu = accommodationObjectService.getOneAccUnit(id);
		if(acu != null) {
			return new ResponseEntity<AccommodationUnit>(acu, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}*/
	
	@PreAuthorize("hasAuthority('GetAdditionalServices')")
	@GetMapping("/additionalservices/{name}")
	public ResponseEntity<AdditionalService> getService(@PathVariable String name) {
		AdditionalService add = additonalRepo.findByName(name);
		if(add != null) {
			System.out.println(add.getName());
			return new ResponseEntity<AdditionalService>(add, HttpStatus.OK);
		} 
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}