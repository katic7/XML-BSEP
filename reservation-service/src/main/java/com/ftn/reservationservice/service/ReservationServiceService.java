package com.ftn.reservationservice.service;

import java.util.List;

import com.ftn.reservationservice.model.Address;
import com.ftn.reservationservice.model.Reservation;

public interface ReservationServiceService {
	
	List<Reservation> getAll();
	List<Address> getAllAdresses();

}
