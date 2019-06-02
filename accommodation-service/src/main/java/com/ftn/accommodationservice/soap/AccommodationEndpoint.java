package com.ftn.accommodationservice.soap;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ftn.accommodationservice.model.AccUnitPrice;
import com.ftn.accommodationservice.model.AccommodationObject;
import com.ftn.accommodationservice.model.AccommodationUnit;
import com.ftn.accommodationservice.model.AdditionalService;
import com.ftn.accommodationservice.model.Address;
import com.ftn.accommodationservice.model.Category;
import com.ftn.accommodationservice.model.Reservation;
import com.ftn.accommodationservice.model.Type;
import com.ftn.accommodationservice.repository.AccommodationRepository;
import com.ftn.accommodationservice.repository.AccommodationUnitPriceRepository;
import com.ftn.accommodationservice.repository.AccommodationUnitRepository;
import com.ftn.accommodationservice.repository.AdditionalServiceRepository;
import com.ftn.accommodationservice.repository.AddressRepository;
import com.ftn.accommodationservice.repository.CategoryRepository;
import com.ftn.accommodationservice.repository.ReservationRepository;
import com.ftn.accommodationservice.repository.TypeRepository;
import com.ftn.accommodationservice.service.AccommodationObjectService;
import com.ftn.accommodationservice.xsd.GetAccUnitPriceRequest;
import com.ftn.accommodationservice.xsd.GetAccUnitPriceResponse;
import com.ftn.accommodationservice.xsd.GetAccommodationObjectRequest;
import com.ftn.accommodationservice.xsd.GetAccommodationObjectResponse;
import com.ftn.accommodationservice.xsd.GetAccommodationUnitRequest;
import com.ftn.accommodationservice.xsd.GetAccommodationUnitResponse;
import com.ftn.accommodationservice.xsd.GetAdditionalServiceRequest;
import com.ftn.accommodationservice.xsd.GetAdditionalServiceResponse;
import com.ftn.accommodationservice.xsd.GetAddressRequest;
import com.ftn.accommodationservice.xsd.GetAddressResponse;
import com.ftn.accommodationservice.xsd.GetAllAdditionalServiceRequest;
import com.ftn.accommodationservice.xsd.GetAllAdditionalServiceResponse;
import com.ftn.accommodationservice.xsd.GetCategoryRequest;
import com.ftn.accommodationservice.xsd.GetCategoryResponse;
import com.ftn.accommodationservice.xsd.GetTestRequest;
import com.ftn.accommodationservice.xsd.GetTestResponse;
import com.ftn.accommodationservice.xsd.GetTypeRequest;
import com.ftn.accommodationservice.xsd.GetTypeResponse;
import com.ftn.accommodationservice.xsd.Test;


@Endpoint
public class AccommodationEndpoint {
	
	@Autowired
	private AccommodationObjectService aoservice;
	
	@Autowired
	private AccommodationRepository aorepo;
	
	@Autowired
	private AddressRepository addressrepo;
	
	@Autowired
	private CategoryRepository catrepo;
	
	@Autowired
	private TypeRepository typerepo;
	
	@Autowired
	private AdditionalServiceRepository additionalservicerepo;
	
	@Autowired
	private AccommodationUnitPriceRepository acurepo;
	
	@Autowired
	private AccommodationUnitRepository aunitrepo;
	
	@Autowired
	private ReservationRepository res;
	
	@PayloadRoot(namespace = "http://ftn.com/accommodationservice/xsd", localPart = "GetAccommodationObjectRequest")
	@ResponsePayload
	@Transactional
	public GetAccommodationObjectResponse getAccommodationById(@RequestPayload GetAccommodationObjectRequest request) {
		System.out.println("Usao u endpoint");
		AccommodationObject ao = aorepo.getOne(request.getId());
		Address add = addressrepo.getOne(ao.getAddressId());
		Category c = catrepo.getOne(ao.getCategoryId());
		com.ftn.accommodationservice.xsd.AccommodationObject s = new com.ftn.accommodationservice.xsd.AccommodationObject();
		com.ftn.accommodationservice.xsd.Address aaa = new com.ftn.accommodationservice.xsd.Address();
		com.ftn.accommodationservice.xsd.Category ccc = new com.ftn.accommodationservice.xsd.Category();
		s.setAddress(aaa);
		s.setCategory(ccc);
		s.setDaysToCancel(ao.getDaysToCancel());
		s.setDescription(ao.getDescription());
		s.setName(ao.getName());
		s.setId(ao.getId());
		s.setFreeCancelation(ao.isFreeCancelation());
		GetAccommodationObjectResponse e = new GetAccommodationObjectResponse();
		// fali addition services i accu niti
		
		e.setAccommodationObject(s);
		return e;
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
	
	@PayloadRoot(namespace = "http://ftn.com/accommodationservice/xsd", localPart = "GetAccUnitPriceRequest")
	@ResponsePayload
	@Transactional
	public GetAccUnitPriceResponse getAccUnitPrice(@RequestPayload GetAccUnitPriceRequest request) {
		AccUnitPrice c = acurepo.getOne(request.getId());
		GetAccUnitPriceResponse e = new GetAccUnitPriceResponse();
		com.ftn.accommodationservice.xsd.AccUnitPrice s = new com.ftn.accommodationservice.xsd.AccUnitPrice();
		s.setId(c.getId());
		s.setPrice(c.getPrice());
		s.setStartDate(c.getStartDate());
		s.setEndDate(c.getEndDate());
		e.setAccUnitPrice(s);
		return e;
	}
	
	@PayloadRoot(namespace = "http://ftn.com/accommodationservice/xsd", localPart = "GetTypeRequest")
	@ResponsePayload
	@Transactional
	public GetTypeResponse getType(@RequestPayload GetTypeRequest request) {
		Type t = typerepo.getOne(request.getId());
		GetTypeResponse e = new GetTypeResponse();
		com.ftn.accommodationservice.xsd.Type s = new com.ftn.accommodationservice.xsd.Type();
		s.setId(t.getId());
		s.setName(t.getName());
		e.setType(s);
		return e;
	}
	
	@PayloadRoot(namespace = "http://ftn.com/accommodationservice/xsd", localPart = "GetAdditionalServiceRequest")
	@ResponsePayload
	@Transactional
	public GetAdditionalServiceResponse getAdditionalService(@RequestPayload GetAdditionalServiceRequest request) {
		AdditionalService as = additionalservicerepo.getOne(request.getId());
		GetAdditionalServiceResponse e = new GetAdditionalServiceResponse();
		com.ftn.accommodationservice.xsd.AdditionalService s = new com.ftn.accommodationservice.xsd.AdditionalService();
		s.setId(as.getId());
		s.setName(as.getName());
		s.setIncluded(as.isIncluded());
		s.setPrice(as.getPrice());
		e.setAdditionalService(s);
		return e;
	}
	
	@PayloadRoot(namespace = "http://ftn.com/accommodationservice/xsd", localPart = "GetAllAdditionalServiceRequest")
	@ResponsePayload
	@Transactional
	public GetAllAdditionalServiceResponse getAllAdditionalService(@RequestPayload GetAllAdditionalServiceRequest request) {
		List<AdditionalService> as = additionalservicerepo.findAll();
		GetAllAdditionalServiceResponse e = new GetAllAdditionalServiceResponse();
		for(AdditionalService a : as) {
			com.ftn.accommodationservice.xsd.AdditionalService asxsd = new com.ftn.accommodationservice.xsd.AdditionalService();
			asxsd.setId(a.getId());
			asxsd.setIncluded(a.isIncluded());
			asxsd.setName(a.getName());
			asxsd.setPrice(a.getPrice());
			e.getAdditionalServices().add(asxsd);
		}
		
		return e;
	}
	
	@PayloadRoot(namespace = "http://ftn.com/accommodationservice/xsd", localPart = "GetAddressRequest")
	@ResponsePayload
	@Transactional
	public GetAddressResponse getAddress(@RequestPayload GetAddressRequest request) {
		Address as = addressrepo.getOne(request.getId());
		GetAddressResponse e = new GetAddressResponse();
		com.ftn.accommodationservice.xsd.Address s = new com.ftn.accommodationservice.xsd.Address();
		s.setId(as.getId());
		s.setLatitude(as.getLatitude());
		s.setLongitude(as.getLongitude());
		s.setPostalCode(as.getPostalCode());
		s.setState(as.getState());
		s.setStreet(as.getStreet());
		s.setStreetNumber(as.getStreetNumber());
		s.setTown(as.getTown());		
		e.setAddress(s);
		return e;
	}
	
	@PayloadRoot(namespace = "http://ftn.com/accommodationservice/xsd", localPart = "GetAccommodationUnitRequest")
	@ResponsePayload
	@Transactional
	public GetAccommodationUnitResponse saveAccUnit(@RequestPayload GetAccommodationUnitRequest request) {
		GetAccommodationUnitResponse response = new GetAccommodationUnitResponse();
		AccommodationUnit unitModel = new AccommodationUnit();
		unitModel.setBalcony(request.getAccommodationUnit().isBalcony());
		unitModel.setDescription(request.getAccommodationUnit().getDescription());
		AccommodationObject ao = aorepo.getOne(request.getAccommodationUnit().getAccObjectId());
		
		
		
		unitModel.setAccommodationObject(ao);
		List<AdditionalService> servisi = new ArrayList<>();
		for(com.ftn.accommodationservice.xsd.AdditionalService xsd : request.getAccommodationUnit().getAdditionalServices())  {
			servisi.add(additionalservicerepo.getOne(xsd.getId()));
		}
		unitModel.setAdditionalServices(servisi);
		
		unitModel.setId(request.getAccommodationUnit().getId());
		//unitModel.setImage(request.getAccommodationUnit().getImage());
		unitModel.setPrice(acurepo.getOne(request.getAccommodationUnit().getPrice().getId()));
		unitModel.setNumberOfBeds(request.getAccommodationUnit().getNumberOfBeds());
		unitModel.setRating(request.getAccommodationUnit().getRating());
		List<Reservation> ress = new ArrayList<>();
		for(com.ftn.accommodationservice.xsd.Reservation rr : request.getAccommodationUnit().getReservations()) {
			ress.add(res.getOne(rr.getId()));
		}
		
		aunitrepo.save(unitModel);
		
		com.ftn.accommodationservice.xsd.AccommodationUnit rspau = new com.ftn.accommodationservice.xsd.AccommodationUnit();
		rspau.setAccObjectId(request.getAccommodationUnit().getAccObjectId());
		rspau.setBalcony(request.getAccommodationUnit().isBalcony());
		rspau.setId(request.getAccommodationUnit().getId());
		rspau.setDescription(request.getAccommodationUnit().getDescription());
		rspau.setNumberOfBeds(request.getAccommodationUnit().getNumberOfBeds());
		rspau.setPrice(request.getAccommodationUnit().getPrice());
		rspau.setRating(request.getAccommodationUnit().getRating());
		
		response.setAccommodationUnit(rspau);
		
		return response;
	}

}
