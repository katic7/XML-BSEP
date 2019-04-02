package com.ftn.reservationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.reservationservice.model.Address;
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
	
	@GetMapping
	public ResponseEntity<List<Address>> getAllReservation() {
		List<Address> reservations = reservationServiceService.getAllAdresses();
		if(reservations != null) {
			return new ResponseEntity<List<Address>>(reservations, HttpStatus.OK);
		}
		return null;
	}

}
