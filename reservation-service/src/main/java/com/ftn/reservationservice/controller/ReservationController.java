package com.ftn.reservationservice.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.reservationservice.dto.ReservationDTO;
import com.ftn.reservationservice.dto.SearchFormDTO;
import com.ftn.reservationservice.model.AccommodationUnit;
import com.ftn.reservationservice.model.Reservation;
import com.ftn.reservationservice.repository.AccommodationUnitRepository;
import com.ftn.reservationservice.repository.UserRepository;
import com.ftn.reservationservice.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
	
	@Autowired
	public ReservationService reservationService;
	
	@Autowired
	public AccommodationUnitRepository accommodationUnitRepository;
	
	@Autowired
	public UserRepository userRepository;
	
	
//	@GetMapping
//	public ResponseEntity<List<Reservation>> getAllReservation() {
//		List<Reservation> reservations = reservationService.getAll();
//		if(reservations != null) {
//			return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);
//		}
//		return null;
//	}
	
	@GetMapping("/test2")
	public String test2() {
		return "test";
	}
	
	@GetMapping("/getOneUnit/{id}")
	public ResponseEntity<AccommodationUnit> getOneAccUnit(@PathVariable Long id) {
		AccommodationUnit acu = reservationService.getOneUnit(id);
		if(acu != null) {
			return new ResponseEntity<AccommodationUnit>(acu, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/getfreeunits")
	public ResponseEntity<List<AccommodationUnit>> getFreeAccUnits(@RequestBody SearchFormDTO info) {
		System.out.println("``````````````````USAO JE GDE TREBA`````````````````");
		List<AccommodationUnit> acu = reservationService.getAvailableAccUnits(info.getDestination(), info.getCheckin(), info.getCheckout());
		if(acu != null) {
			return new ResponseEntity<List<AccommodationUnit>>(acu, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasAuthority('MakeReservation')")
	@PostMapping 
	public ResponseEntity<Reservation> makeAReservation(@RequestBody ReservationDTO res) {
		Reservation newRes = new Reservation();
		if(res != null) {
			newRes.setAccommodationUnit(accommodationUnitRepository.getOne(res.getAccommodationUnitId()));
			newRes.setActive(false);
			newRes.setCompleted(false);
			newRes.setBeginDate(res.getBeginDate());
			newRes.setEndDate(res.getEndDate());
			newRes.setReservationDate(new Date());
			newRes.setPrice(res.getPrice());
			newRes.setUser(userRepository.getOne(res.getUserId()));
			
			reservationService.makeAReservation(newRes);
			
			return new ResponseEntity<Reservation>(newRes, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/byUser/{id}")
	public ResponseEntity<List<ReservationDTO>> getReservationsByUser(@PathVariable Long id) {
		System.out.println("usao u contr");
		List<Reservation> res = reservationService.findReservationsByUserId(id);
		if(res != null) {
			List<ReservationDTO> r = new ArrayList<>();
			for(Reservation re : res) {
				r.add(new ReservationDTO(re));
			}
			return new ResponseEntity<List<ReservationDTO>>(r, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
		if(id != null) {
			reservationService.deleteReservation(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/getForCompletion")
	public ResponseEntity<List<ReservationDTO>> getForCompletion(@RequestBody Date date) {
		System.out.println("A BRAO");
		List<Reservation> res = new ArrayList<>();
		if(date != null) {
			res = reservationService.getForCompletion(date);
			ArrayList<ReservationDTO> ress = new ArrayList<ReservationDTO>();
			for(Reservation r : res) {
				ress.add(new ReservationDTO(r));
			}
			return new ResponseEntity<List<ReservationDTO>>(ress, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getUnits")
	public ResponseEntity<List<AccommodationUnit>> getUnits() {
		List<AccommodationUnit> acus = reservationService.getUnits();
		if(acus != null) {
			return new ResponseEntity<List<AccommodationUnit>>(acus, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}