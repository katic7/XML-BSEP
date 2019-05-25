package com.ftn.accommodationservice.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ftn.accommodationservice.model.AccommodationObject;
import com.ftn.accommodationservice.repository.AccommodationRepository;
import com.ftn.accommodationservice.repository.AddressRepository;
import com.ftn.accommodationservice.service.AccommodationObjectService;
import com.ftn.accommodationservice.xsd.GetAccommodationObjectRequest;
import com.ftn.accommodationservice.xsd.GetAccommodationObjectResponse;
import com.ftn.accommodationservice.xsd.GetTestRequest;
import com.ftn.accommodationservice.xsd.GetTestResponse;
import com.ftn.accommodationservice.xsd.Test;


@Endpoint
public class AccommodationEndpoint {
	
	@Autowired
	private AccommodationObjectService aoservice;
	
	@Autowired
	private AccommodationRepository arepo;
	
	@Autowired
	private AccommodationRepository aorepo;
	
	@Autowired
	private AddressRepository addressrepo;
	
	
	@PayloadRoot(namespace = "http://ftn.com/accommodationservice/xsd", localPart = "GetAccommodationObjectRequest")
	@ResponsePayload
	public GetAccommodationObjectResponse getAccommodationById(@RequestPayload GetAccommodationObjectRequest request) {
		System.out.println("Usao u endpoint");
		
		AccommodationObject ao = aorepo.getOne(request.getId());
		return mapAOR(ao);
	}
	
	private GetAccommodationObjectResponse mapAOR(AccommodationObject ao) {
		GetAccommodationObjectResponse n = new GetAccommodationObjectResponse();
		n.setName(ao.getName());
		//dalje mapirati
		return n;
	}
	
	@PayloadRoot(namespace = "http://ftn.com/accommodationservice/xsd", localPart = "GetTestRequest")
	@ResponsePayload
	public GetTestResponse test(@RequestPayload GetTestRequest request) {
		System.out.println("Usao u test");
		GetTestResponse e = new GetTestResponse();
		Test t = new Test();
		t.setName("Nemanja");
		e.setTest(t);
		return e;
	}

}
