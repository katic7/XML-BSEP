package com.ftn.agentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.accommodationservice.xsd.GetTestResponse;
import com.ftn.agentservice.soap.ReservationClinet;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
	
	@Autowired
	private ReservationClinet client;
	
	
	@GetMapping
	public GetTestResponse test() {
		return client.test(1);
	}

}
