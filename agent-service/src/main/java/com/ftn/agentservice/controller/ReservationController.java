package com.ftn.agentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.accommodationservice.xsd.GetTestResponse;
import com.ftn.accommodationservice.xsd.PostReservationBusynessRequest;
import com.ftn.accommodationservice.xsd.PostReservationBusynessResponse;
import com.ftn.accommodationservice.xsd.PostReservationCompletionRequest;
import com.ftn.accommodationservice.xsd.PostReservationCompletionResponse;
import com.ftn.agentservice.soap.ReservationClinet;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
	
	@Autowired
	private ReservationClinet client;
	
	
	
	@PreAuthorize("hasAuthority('ModifyContent')")
	@PostMapping("/completeReservation")
	public PostReservationCompletionResponse completeReservation(@RequestBody PostReservationCompletionRequest request) {
		return client.completeReservation(request);
	}
	
	@PreAuthorize("hasAuthority('ModifyContent')")
	@PostMapping("/makeUnitBusy")
	public PostReservationBusynessResponse makeUnitBusy(@RequestBody PostReservationBusynessRequest request) {
		return client.makeUnitBusy(request);
	}

}
