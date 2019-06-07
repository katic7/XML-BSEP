package com.ftn.reservationservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.reservationservice.dto.SearchFormDTO;
import com.ftn.reservationservice.model.AccommodationObject;
import com.ftn.reservationservice.model.AccommodationUnit;
import com.ftn.reservationservice.model.Address;
import com.ftn.reservationservice.model.Reservation;
import com.ftn.reservationservice.repository.AccommodationUnitRepository;
import com.ftn.reservationservice.repository.AddressRepository;
import com.ftn.reservationservice.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	public ReservationRepository reservationRepository;
	
	@Autowired
	public AddressRepository addressRepository;
	
	@Autowired
	public AccommodationUnitRepository accommodationUnitRepository;
	
	@Autowired
	public AddressService addressService;

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
		//List<Reservation> reservations = reservationRepository.findReservationsInInterval(startDate, endDate);		
		List<Reservation> reservations = reservationRepository.findAll();
		List<AccommodationUnit> acu = new ArrayList<>();
		List<AccommodationUnit> allAcu = accommodationUnitRepository.findAll();
		
		for(AccommodationUnit au : allAcu) {
			if(addressRepository.getOne(au.getAccommodationObject().getAddressId()).getTown().equals(dest)) {
				acu.add(au);
			}
		}
		
		for(Reservation r : reservations) {
			if(acu.contains(r.getAccommodationUnit())) {				
				if((r.getBeginDate().compareTo(startDate) > 0 && r.getBeginDate().compareTo(endDate) < 0) || (r.getEndDate().compareTo(startDate) > 0 && r.getEndDate().compareTo(endDate) < 0)) {
					acu.remove(r.getAccommodationUnit());
				}
			}
		}
		
		/*for(Reservation r : reservations) {
			if(!acu.contains(r.getAccommodationUnit())) {
				if(addressRepository.getOne(r.getAccommodationUnit().getAccommodationObject().getAddressId()).getTown().equals(dest)) {
					if(r.getBeginDate().compareTo(startDate) < 0 || r.getBeginDate().compareTo(endDate) > 0) {
						if(r.getEndDate().compareTo(startDate) < 0 || r.getEndDate().compareTo(endDate) > 0) {
							acu.add(r.getAccommodationUnit());
						}
					}						
				}
			}
		}*/
		
		//		 not((r.begin_date between ?1 and ?2) or (r.end_date between ?1 and ?2))
		
		return acu;
	}

	@Override
	public Reservation makeAReservation(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	@Override
	public List<AccommodationUnit> getAvailableAccUnitsWithDistance(SearchFormDTO form, double distance) {
		String placeToGo = form.getDestination();
		RestTemplate template = new RestTemplate();
		ResponseEntity<List<AccommodationObject>> response = template.exchange(
				"http://localhost:8082/api/accobject",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<AccommodationObject>>(){});
		List<AccommodationObject> accs = response.getBody();
		for(AccommodationObject ao : accs) {
			Address addr = addressService.getOne(ao.getAddressId());
			double latitude = addr.getLatitude();
			double longitude = addr.getLongitude();
			
			//way to get lat/long from placeToGo either from Google API or discover some other way
			
		}
		return null;
	}

	
}