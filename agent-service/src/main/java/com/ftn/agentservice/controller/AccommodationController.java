package com.ftn.agentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.accommodationservice.xsd.AccommodationUnit;
import com.ftn.accommodationservice.xsd.GetAccommodationUnitResponse;
import com.ftn.agentservice.soap.AccommodationClient;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
	
	@Autowired
	private AccommodationClient client;
	
	@PostMapping
	public String newAcc(@RequestBody AccommodationUnit request) {
		GetAccommodationUnitResponse r = client.saveNewAcc(request);
		System.out.println(r.getAccommodationUnit().getDescription());
		return r.getAccommodationUnit().getDescription();	
	}
	
	@GetMapping
	public String test() {
		return "nemanjica";
	}
}