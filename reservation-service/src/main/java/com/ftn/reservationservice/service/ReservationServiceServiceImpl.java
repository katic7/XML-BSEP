package com.ftn.reservationservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.reservationservice.model.Reservation;
import com.ftn.reservationservice.repository.AddressRepository;

@Service
public class ReservationServiceServiceImpl implements ReservationServiceService{

	@Autowired
	public AddressRepository reservationServiceRepository;

	@Override
	public List<Reservation> getAll() {		
		return null;
	}

	
}
