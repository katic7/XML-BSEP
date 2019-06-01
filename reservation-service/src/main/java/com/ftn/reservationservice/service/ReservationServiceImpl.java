package com.ftn.reservationservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.reservationservice.model.AccommodationUnit;
import com.ftn.reservationservice.model.Reservation;
import com.ftn.reservationservice.repository.AddressRepository;
import com.ftn.reservationservice.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	public ReservationRepository reservationRepository;
	
	@Autowired
	public AddressRepository addressRepository;

	@Override
	public List<Reservation> getAll() {		
		return null;
	}

	/*@Override
	public List<AccommodationUnit> getAvailableAccUnits(String dest, Date startDate, Date endDate) {
		System.out.println("SDATEEEEEEEEEEEEE: " + startDate);
		System.out.println("EDATEEEEEEEEEEEEE: " + endDate);
		List<BigInteger> res = reservationRepository.findReservationsInInterval(dest, startDate, endDate);
		//reservationRepository.findReservationsInInterval(dest, startDate, endDate).forEach(l -> res.add(reservationRepository.getOne(l.longValue())));
		System.out.println("``````````````````RES SIZE: " + res.size());
		List<AccommodationUnit> acu = new ArrayList<>();
		for(BigInteger r : res) {
			Reservation rr = reservationRepository.getOne(Long.valueOf(r.longValue()));
			System.out.println("```````````````````````````````````````````RR = " + rr.toString());
			AccommodationUnit acuu = rr.getAccommodationUnit();
			System.out.println("========== " + acuu.getId());
			acu.add(rr.getAccommodationUnit());
		}
		return acu;
	}*/
	
	@Override
	public List<AccommodationUnit> getAvailableAccUnits(String dest, Date startDate, Date endDate) {		
		List<Reservation> reservations = reservationRepository.findReservationsInInterval(startDate, endDate);		
		List<AccommodationUnit> acu = new ArrayList<>();
		
		for(Reservation r : reservations) {
			if(!acu.contains(r.getAccommodationUnit())) {
				if(addressRepository.getOne(r.getAccommodationUnit().getAccommodationObject().getAddressId()).getTown().equals(dest)) {
					acu.add(r.getAccommodationUnit());
				}
			}
		}
		
		return acu;
	}

	
}
