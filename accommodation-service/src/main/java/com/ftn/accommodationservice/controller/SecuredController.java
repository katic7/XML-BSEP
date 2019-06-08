package com.ftn.accommodationservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SecuredController {

	@GetMapping("/hello")
	public String secureHello() {
		RestTemplate template = new RestTemplate();
		String fromReservation = template.getForObject("https://localhost:8083/api/addresses/test3", String.class);
		System.out.println(fromReservation);
		String fromAuth = template.getForObject("https://localhost:8085/api/test/testing", String.class);
		System.out.println(fromAuth);
		String fromZuul = template.getForObject("https://localhost:8081/reservationservice/api/addresses/test3", String.class);
		System.out.println(fromZuul);
		return fromReservation + " " + fromAuth + " preko zuula: "+fromZuul;
	}
	
}