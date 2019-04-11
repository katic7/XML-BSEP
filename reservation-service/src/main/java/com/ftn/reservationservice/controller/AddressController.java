package com.ftn.reservationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ftn.reservationservice.model.Address;
import com.ftn.reservationservice.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping
	public ResponseEntity<List<Address>> getAllAddresses() {
		List<Address> addresses = addressService.getAllAdresses();
		if(addresses != null) {
		return new ResponseEntity<List<Address>>(addresses, HttpStatus.OK);
		}
		return null;
	}
	
	@GetMapping("/test")
	public String test() {
		RestTemplate rt = new RestTemplate();
		return rt.getForObject("https://localhost:8443/api/test/test", String.class);
	}

}
