package com.ftn.reservationservice.service;

import java.util.Date;
import java.util.List;

import com.ftn.reservationservice.dto.SearchFormDTO;
import com.ftn.reservationservice.model.AccommodationUnit;
import com.ftn.reservationservice.model.Reservation;

public interface ReservationService {
	
	List<Reservation> getAll();
	List<AccommodationUnit> getAvailableAccUnits(String dest, Date startDate, Date endDate);
<<<<<<< HEAD
	List<AccommodationUnit> getAvailableAccUnitsWithDistance(SearchFormDTO form, double distance);
=======
	AccommodationUnit getOneUnit(Long id);
>>>>>>> 4659e4313b82f4a767ec34290d3efb7558317bea
	Reservation makeAReservation(Reservation reservation);
}
