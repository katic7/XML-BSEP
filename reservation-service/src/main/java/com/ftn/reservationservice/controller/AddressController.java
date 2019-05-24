package com.ftn.reservationservice.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("hasAuthority('READ')")
	public String test() throws NoSuchAlgorithmException {
		RestTemplate rt = new RestTemplate();
		return rt.getForObject("https://localhost:8082/api/reservations/test2", String.class);
	}
	
	@GetMapping("/test2")
	@PreAuthorize("hasAuthority('CREATE')")
	public String test2() {
		return "test2";
	}
	
	@GetMapping("/test3")
	@PreAuthorize("hasAuthority('addContent')")
	public String test3() {
		return "test3";
	}

}
