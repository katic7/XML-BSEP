package com.ftn.reservationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.reservationservice.dto.SearchFormDTO;
import com.ftn.reservationservice.model.AccommodationUnit;
import com.ftn.reservationservice.service.ReservationServiceService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationServiceController {
	
	@Autowired
	public ReservationServiceService reservationServiceService;
	
//	@GetMapping
//	public ResponseEntity<List<Reservation>> getAllReservation() {
//		List<Reservation> reservations = reservationServiceService.getAll();
//		if(reservations != null) {
//			return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);
//		}
//		return null;
//	}
	
	@GetMapping("/test2")
	public String test2() {
		return "test";
	}
	
	@PostMapping("/getfreeunits")
	public ResponseEntity<List<AccommodationUnit>> getFreeAccUnits(@RequestBody SearchFormDTO info) {
		System.out.println("``````````````````USAO JE GDE TREBA`````````````````");
		List<AccommodationUnit> acu = reservationServiceService.getAvailableAccUnits(info.getDestination(), info.getCheckin(), info.getCheckout());
		if(acu != null) {
			return new ResponseEntity<List<AccommodationUnit>>(acu, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
