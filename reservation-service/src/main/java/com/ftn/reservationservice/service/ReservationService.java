package com.ftn.reservationservice.service;

import java.util.Date;
import java.util.List;

import com.ftn.reservationservice.dto.SearchFormDTO;
import com.ftn.reservationservice.model.AccommodationUnit;
import com.ftn.reservationservice.model.Reservation;

public interface ReservationService {
	
	List<Reservation> getAll();
	List<AccommodationUnit> getAvailableAccUnits(String dest, Date startDate, Date endDate);
	List<AccommodationUnit> getAvailableAccUnitsWithDistance(SearchFormDTO form, double distance);
	Reservation makeAReservation(Reservation reservation);
}
