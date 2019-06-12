package com.ftn.accommodationservice.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ftn.accommodationservice.model.Address;

import com.ftn.accommodationservice.repository.CategoryRepository;
import com.ftn.accommodationservice.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private CategoryRepository crepo;
	
	 @Autowired
	 private RestTemplate template;
	
	@GetMapping //treba
	public ResponseEntity<List<Address>> getAllAddresses() {
		List<Address> addresses = addressService.getAddress();
		if(addresses != null) {
		return new ResponseEntity<List<Address>>(addresses, HttpStatus.OK);
		}
		return null;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Address> getOneAddress(@PathVariable Long id) {
		return new ResponseEntity<Address>(addressService.getOne(id), HttpStatus.OK);
	}
	
	@PostMapping //treba
	public ResponseEntity<Address> postNewAddress(@RequestBody Address address, HttpServletRequest request) {
		return new ResponseEntity<Address>(addressService.addAddress(address), HttpStatus.CREATED);
	}
	
	@GetMapping("/test")
	public String testa() {
		ResponseEntity<String> response = template.getForEntity("https://localhost:8761/hello",
				 String.class);
				 System.out.println(response.getBody());
				 return "a";
	}
	
	@GetMapping("/test2")
	public String test2() {
		return crepo.getOne(Long.valueOf(1)).getName();
	}
	
	@GetMapping("/test3")
	@PreAuthorize("hasAuthority('BlockComments')")
	public String test3() {
		return "test3";
	}
	
	@GetMapping("/testhttps")
	public String test5() {
		return "ssl ok";
		
	}


}
