package com.ftn.agentservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import com.ftn.accommodationservice.xsd.AccUnitPrice;
import com.ftn.accommodationservice.xsd.AccommodationObject;
import com.ftn.accommodationservice.xsd.AccommodationUnit;
import com.ftn.accommodationservice.xsd.AdditionalService;
import com.ftn.accommodationservice.xsd.Address;
import com.ftn.accommodationservice.xsd.GetAccommodationUnitResponse;
import com.ftn.accommodationservice.xsd.GetAddressResponse;
import com.ftn.accommodationservice.xsd.GetAllAccUnitPriceRequest;
import com.ftn.accommodationservice.xsd.GetAllAccUnitPriceResponse;
import com.ftn.accommodationservice.xsd.GetAllAdditionalServiceResponse;
import com.ftn.accommodationservice.xsd.PostAccUnitPriceRequest;
import com.ftn.accommodationservice.xsd.PostAccUnitPriceResponse;
import com.ftn.accommodationservice.xsd.PostAccommodationObjectResponse;
import com.ftn.accommodationservice.xsd.PostAddressRequest;
import com.ftn.accommodationservice.xsd.PostAddressResponse;
import com.ftn.accommodationservice.xsd.PostObjectUnitsResponse;
import com.ftn.agentservice.dto.AccommodationObjectDTO;
import com.ftn.agentservice.model.User;
import com.ftn.agentservice.repository.UserRepository;
import com.ftn.agentservice.soap.AccommodationClient;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
	
	@Autowired
	private AccommodationClient client;
	
	@Autowired
	private UserRepository userRepository;
	
	@PreAuthorize("hasAuthority('AddAccUnit')")
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
	
	@GetMapping("/getObjectUnits/{id}")
	public List<AccommodationUnit> getUnits(@PathVariable Long id){
		PostObjectUnitsResponse response = client.getUnits(id);
		return response.getAccommodationUnit();
	}
	
	@GetMapping("/allAdditionalServices")
	public List<AdditionalService> getAllAdditionalServices(){
		GetAllAdditionalServiceResponse as = client.getAllAdditionalServiceResponse();
		return as.getAdditionalServices();
	}
	
	@GetMapping("/getAddress/{id}")
	public Address getAddress(@PathVariable Long id) {
		GetAddressResponse response = client.getAddress(id);
		return response.getAddress();
	}
	
	/*@GetMapping("/getAllPrices")
	public List<AccUnitPrice> gelAllPrices(){
		GetAllAccUnitPriceResponse response = client.getAllPrices();
		return response.getAccUnitPrice();
	}*/
	
	@PostMapping("/createPrice")
	public AccUnitPrice createPrice(@RequestBody AccUnitPrice acc) {
		PostAccUnitPriceResponse response = client.createPrice(acc);
		return response.getAccUnitPrice();
	}
	
	@PostMapping("/createAddress")
	public Address createAddress(@RequestBody Address adr) {
		PostAddressResponse address = client.createAddress(adr);
		adr.setId(address.getAddress().getId());
		return adr;
	}
	
	@PostMapping("/createAccObject")
	public AccommodationObjectDTO createObject(@RequestBody AccommodationObjectDTO accObj, HttpServletRequest request) {
		String token = (request.getHeader("Authorization")).substring(7, request.getHeader("Authorization").length());
		System.out.println(token + "TOKEEEEEN");
		RestTemplate template = new RestTemplate();
		String username = template.getForObject("https://localhost:8085/api/auth/check/{token}/username", String.class, token);
		
		User usr = userRepository.findByEmail(username).get();
		System.out.println(username + "AAAAAAAAAA");
		PostAccommodationObjectResponse acc = client.createAccObject(accObj, usr);
		accObj.setId(acc.getAccommodationObject().getId());
		return accObj;
	}
	
	@PreAuthorize("hasAuthority('AddAccUnit')")
	@PostMapping("/addAccUnit")
	public AccommodationUnit addNewAccUnit(@RequestBody AccommodationUnit accUnit) {
		GetAccommodationUnitResponse r = client.saveNewAcc(accUnit);
		return r.getAccommodationUnit();
		
	}
}
