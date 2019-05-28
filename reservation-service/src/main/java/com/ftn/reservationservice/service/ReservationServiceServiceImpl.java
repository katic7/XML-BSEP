package com.ftn.reservationservice.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.reservationservice.model.AccommodationUnit;
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
	public List<AccommodationUnit> getAvailableAccUnits(String dest, Date startDate, Date endDate) {
		System.out.println("SDATEEEEEEEEEEEEE: " + startDate);
		System.out.println("EDATEEEEEEEEEEEEE: " + endDate);
		List<BigInteger> res = reservationServiceRepository.findReservationsInInterval(dest, startDate, endDate);
		//reservationServiceRepository.findReservationsInInterval(dest, startDate, endDate).forEach(l -> res.add(reservationServiceRepository.getOne(l.longValue())));
		System.out.println("``````````````````RES SIZE: " + res.size());
		List<AccommodationUnit> acu = new ArrayList<>();
		for(BigInteger r : res) {
			Reservation rr = reservationServiceRepository.getOne(Long.valueOf(r.longValue()));
			System.out.println("```````````````````````````````````````````RR = " + rr.toString());
			AccommodationUnit acuu = rr.getAccommodationUnit();
			System.out.println("========== " + acuu.getId());
			acu.add(rr.getAccommodationUnit());
		}
		return acu;
	}

	
}
