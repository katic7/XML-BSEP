package com.ftn.accommodationservice.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ftn.accommodationservice.model.Address;
import com.ftn.accommodationservice.model.TestRating;
import com.ftn.accommodationservice.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@Value("${keystore.file}")
	public String keyStorefile;
	@Value("${keystore.password}")
	public String password;
	
	
	@GetMapping
	public ResponseEntity<List<Address>> getAllAddresses() {
		List<Address> addresses = addressService.getAddress();
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
	
	@GetMapping("/test2")
	public String test2() {
		return "test";
	}
	
	@PostMapping("/testRating")
	public String testRating(@Valid @RequestBody TestRating testRating) {
		return "Uspesno promenjen rating!";
	}


}
