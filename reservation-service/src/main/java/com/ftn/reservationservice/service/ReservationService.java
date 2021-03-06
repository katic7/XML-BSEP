package com.ftn.reservationservice.service;

import java.util.Date;
import java.util.List;

import com.ftn.reservationservice.model.AccommodationUnit;
import com.ftn.reservationservice.model.Reservation;

public interface ReservationService {
	
	List<Reservation> getAll();
	List<AccommodationUnit> getAvailableAccUnits(String dest, Date startDate, Date endDate);
	AccommodationUnit getOneUnit(Long id);
	Reservation makeAReservation(Reservation reservation);
	List<Reservation> findReservationsByUserId(Long id);
	void deleteReservation(Long id);
	List<Reservation> getForCompletion(Date date);
	
	List<AccommodationUnit> getUnits();
}