package com.ftn.reservationservice.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ftn.reservationservice.dto.ReservationDTO;
import com.ftn.reservationservice.dto.SearchFormDTO;
import com.ftn.reservationservice.dto.UserDTO;
import com.ftn.reservationservice.model.AccommodationUnit;
import com.ftn.reservationservice.model.Agent;
import com.ftn.reservationservice.model.Reservation;
import com.ftn.reservationservice.model.Role;
import com.ftn.reservationservice.model.RoleName;
import com.ftn.reservationservice.model.User;
import com.ftn.reservationservice.repository.AccommodationUnitRepository;
import com.ftn.reservationservice.repository.ReservationRepository;
import com.ftn.reservationservice.repository.AgentRepository;
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
	
	@Autowired
	public ReservationRepository reservationRepository;

	public static RoleName roleName;
	 
	@Autowired
	public AgentRepository agentRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

//	@GetMapping
//	public ResponseEntity<List<Reservation>> getAllReservation() {
//		List<Reservation> reservations = reservationService.getAll();
//		if(reservations != null) {
//			return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);
//		}
//		return null;
//	}
	
	
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
			logger.info("user: {}, beginDate: {}, endDate: {} | NAN0R3 | success", newRes.getUser().getId(), newRes.getBeginDate(), newRes.getEndDate());
			return new ResponseEntity<Reservation>(newRes, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PreAuthorize("hasAuthority('MakeReservation')")
	@GetMapping("/byUser/{id}")
	public ResponseEntity<List<ReservationDTO>> getReservationsByUser(@PathVariable Long id) {
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
	
	@PreAuthorize("hasAuthority('MakeReservation')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
		if(id != null) {
			reservationService.deleteReservation(id);
			logger.info("id: {} | 0BR3Z3 | success", id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		logger.error("id: {} | 0BR3Z3 | failed", id);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PreAuthorize("hasAuthority('CompleteReservation')")
	@PostMapping("/getForCompletion")
	public ResponseEntity<List<ReservationDTO>> getForCompletion(@RequestBody Date date) {
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
	
	@PreAuthorize("hasAuthority('CompleteReservation')")
	@GetMapping("/getObjectReservations/{id}")
	public ResponseEntity<List<ReservationDTO>> getObjectReservations(@PathVariable Long id){
		Date date = new Date();
		List<Reservation> lista = reservationRepository.getObjectReservations(id,date);
		List<ReservationDTO> povratna = new ArrayList<ReservationDTO>();
		for(Reservation r:lista) {
			ReservationDTO rDTO = new ReservationDTO(r);
			povratna.add(rDTO);
		}
		return new ResponseEntity<List<ReservationDTO>>(povratna, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('CompleteReservation')")
	@GetMapping("/getUpComingReservations/{id}")
	public ResponseEntity<List<ReservationDTO>> getUpComingReservations(@PathVariable Long id){
		Date date = new Date();
		List<Reservation> lista = reservationRepository.getUpComingReservations(id, date);
		List<ReservationDTO> povratna = new ArrayList<ReservationDTO>();
		for(Reservation r:lista) {
			ReservationDTO rDTO = new ReservationDTO(r);
			povratna.add(rDTO);
		}
		return new ResponseEntity<List<ReservationDTO>>(povratna, HttpStatus.OK);
	}
	
	@GetMapping("/getUnits")
	public ResponseEntity<List<AccommodationUnit>> getUnits() {
		List<AccommodationUnit> acus = reservationService.getUnits();
		if(acus != null) {
			return new ResponseEntity<List<AccommodationUnit>>(acus, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PreAuthorize("hasAuthority('CompleteReservation')")
	@GetMapping("/getAgentUnits/{id}")
	public ResponseEntity<List<AccommodationUnit>> getAgentUnits(@PathVariable Long id) {
		List<AccommodationUnit> acus = accommodationUnitRepository.getAgentUnits(id);
		
		if(acus != null) {
			return new ResponseEntity<List<AccommodationUnit>>(acus, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}