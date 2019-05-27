package com.ftn.accommodationservice.soap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ftn.accommodationservice.model.AccommodationObject;
import com.ftn.accommodationservice.model.Category;
import com.ftn.accommodationservice.repository.AccommodationRepository;
import com.ftn.accommodationservice.repository.AddressRepository;
import com.ftn.accommodationservice.repository.CategoryRepository;
import com.ftn.accommodationservice.service.AccommodationObjectService;
import com.ftn.accommodationservice.xsd.GetAccommodationObjectRequest;
import com.ftn.accommodationservice.xsd.GetAccommodationObjectResponse;
import com.ftn.accommodationservice.xsd.GetCategoryRequest;
import com.ftn.accommodationservice.xsd.GetCategoryResponse;
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
	
	@Autowired
	private CategoryRepository catrepo;
	
	
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
	
	@PayloadRoot(namespace = "http://ftn.com/accommodationservice/xsd", localPart = "GetCategoryRequest")
	@ResponsePayload
	@Transactional
	public GetCategoryResponse getCategory(@RequestPayload GetCategoryRequest request) {
		Category c = catrepo.getOne(request.getId());
		GetCategoryResponse e = new GetCategoryResponse();
		com.ftn.accommodationservice.xsd.Category s = new com.ftn.accommodationservice.xsd.Category();
		s.setId(c.getId());
		s.setName(c.getName());
		e.setCategory(s);
		return e;
	}

}
