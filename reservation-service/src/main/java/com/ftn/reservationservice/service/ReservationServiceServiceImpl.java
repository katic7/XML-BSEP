package com.ftn.reservationservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.reservationservice.model.Address;
import com.ftn.reservationservice.model.Reservation;
import com.ftn.reservationservice.repository.ReservationServiceRepository;

@Service
public class ReservationServiceServiceImpl implements ReservationServiceService{

	@Autowired
	public ReservationServiceRepository reservationServiceRepository;

	@Override
	public List<Reservation> getAll() {		
		return null;
	}

	@Override
	public List<Address> getAllAdresses() {
		return reservationServiceRepository.findAll();
	}
	
	
}
