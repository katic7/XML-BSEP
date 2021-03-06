package com.ftn.accommodationservice.soap;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ftn.accommodationservice.model.AccUnitPrice;
import com.ftn.accommodationservice.model.AccommodationObject;
import com.ftn.accommodationservice.model.AccommodationUnit;
import com.ftn.accommodationservice.model.AdditionalService;
import com.ftn.accommodationservice.model.Address;
import com.ftn.accommodationservice.model.Agent;
import com.ftn.accommodationservice.model.Category;
import com.ftn.accommodationservice.model.Reservation;
import com.ftn.accommodationservice.model.Type;
import com.ftn.accommodationservice.model.User;
import com.ftn.accommodationservice.repository.AccommodationObjectRepository;
import com.ftn.accommodationservice.repository.AccommodationRepository;
import com.ftn.accommodationservice.repository.AccommodationUnitPriceRepository;
import com.ftn.accommodationservice.repository.AccommodationUnitRepository;
import com.ftn.accommodationservice.repository.AdditionalServiceRepository;
import com.ftn.accommodationservice.repository.AddressRepository;
import com.ftn.accommodationservice.repository.AgentRepository;
import com.ftn.accommodationservice.repository.CategoryRepository;
import com.ftn.accommodationservice.repository.ReservationRepository;
import com.ftn.accommodationservice.repository.TypeRepository;
import com.ftn.accommodationservice.repository.UserRepository;
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
import com.ftn.accommodationservice.xsd.GetAllAccUnitPriceRequest;
import com.ftn.accommodationservice.xsd.GetAllAccUnitPriceResponse;
import com.ftn.accommodationservice.xsd.GetAllAdditionalServiceRequest;
import com.ftn.accommodationservice.xsd.GetAllAdditionalServiceResponse;
import com.ftn.accommodationservice.xsd.GetCategoryRequest;
import com.ftn.accommodationservice.xsd.GetCategoryResponse;
import com.ftn.accommodationservice.xsd.GetDataBaseResponse;
import com.ftn.accommodationservice.xsd.GetTestRequest;
import com.ftn.accommodationservice.xsd.GetTestResponse;
import com.ftn.accommodationservice.xsd.GetTypeRequest;
import com.ftn.accommodationservice.xsd.GetTypeResponse;
import com.ftn.accommodationservice.xsd.PostAccUnitPriceRequest;
import com.ftn.accommodationservice.xsd.PostAccUnitPriceResponse;
import com.ftn.accommodationservice.xsd.PostAccommodationObjectRequest;
import com.ftn.accommodationservice.xsd.PostAccommodationObjectResponse;
import com.ftn.accommodationservice.xsd.PostAddressRequest;
import com.ftn.accommodationservice.xsd.PostAddressResponse;
import com.ftn.accommodationservice.xsd.PostObjectUnitsRequest;
import com.ftn.accommodationservice.xsd.PostObjectUnitsResponse;
import com.ftn.accommodationservice.xsd.Test;

import io.micrometer.core.ipc.http.HttpSender.Response;



@Endpoint
public class AccommodationEndpoint {
	
	@Autowired
	private AccommodationObjectService aoservice;
	
	@Autowired
	private AccommodationObjectRepository accObjRepo;
	
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
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AgentRepository agentRepository;
	
	/*@PayloadRoot(namespace = "http://ftn.com/accommodationservice/xsd", localPart = "GetAccommodationObjectRequest")
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
	}*/
	

	
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
	
	@PayloadRoot(namespace = "http://ftn.com/accommodationservice/xsd", localPart = "PostAccommodationObjectRequest")
	@ResponsePayload
	@Transactional
	public PostAccommodationObjectResponse createObject(@RequestPayload PostAccommodationObjectRequest request) {
		
		User usr = userRepository.getOne(request.getUserId());
		
		Agent agent = agentRepository.getOne(usr.getId());
		AccommodationObject acc = new AccommodationObject();
		Address adr = addressrepo.getOne(request.getAccommodationObject().getAddress().getId());
		Category cat = catrepo.getOne(request.getAccommodationObject().getCategory().getId());
		Type tip = typerepo.getOne(request.getAccommodationObject().getType().getId());
		
		acc.setAddress(adr);
		acc.setCategory(cat);
		acc.setType(tip);
		acc.setDaysToCancel(request.getAccommodationObject().getDaysToCancel());
		acc.setDescription(request.getAccommodationObject().getDescription());
		acc.setName(request.getAccommodationObject().getName());
		acc.setFreeCancelation(request.getAccommodationObject().isFreeCancelation());
		acc = accObjRepo.save(acc);
		
		agent.setAccObj(acc);
		agentRepository.save(agent);
		
		PostAccommodationObjectResponse response = new PostAccommodationObjectResponse();
		response.setAccommodationObject(request.getAccommodationObject());
		response.getAccommodationObject().setId(acc.getId());
		return response;
	}
	
	@PayloadRoot(namespace = "http://ftn.com/accommodationservice/xsd", localPart = "PostObjectUnitsRequest")
	@ResponsePayload
	@Transactional
	public PostObjectUnitsResponse getUnits(@RequestPayload PostObjectUnitsRequest request) {
		List<AccommodationUnit> lista = aunitrepo.findAllByObject(request.getId());
		PostObjectUnitsResponse response = new PostObjectUnitsResponse();
		//List<com.ftn.accommodationservice.xsd.AccommodationUnit> povratna = new ArrayList<com.ftn.accommodationservice.xsd.AccommodationUnit>();
		for(AccommodationUnit uni : lista) {
			com.ftn.accommodationservice.xsd.AccommodationUnit unit = new com.ftn.accommodationservice.xsd.AccommodationUnit();
			com.ftn.accommodationservice.xsd.AccUnitPrice price = new com.ftn.accommodationservice.xsd.AccUnitPrice();
			price.setEndDate(uni.getPrice().getEndDate());
			price.setStartDate(uni.getPrice().getStartDate());
			price.setId(uni.getPrice().getId());
			price.setPrice(uni.getPrice().getPrice());
			
			unit.setPrice(price);
			unit.setBalcony(uni.isBalcony());
			unit.setDescription(uni.getDescription());
			unit.setId(uni.getId());
			unit.setNumberOfBeds(uni.getNumberOfBeds());
			unit.setRating(uni.getRating());
			//povratna.add(unit);
			response.getAccommodationUnit().add(unit);
		}
		
		return response;
	}
	
	@PayloadRoot(namespace = "http://ftn.com/accommodationservice/xsd", localPart = "GetDataBaseRequest")
	@ResponsePayload
	@Transactional
	public GetDataBaseResponse syncDataBase() {
		GetDataBaseResponse response = new GetDataBaseResponse();
		Converter conv = new Converter();
		List<Type> tipovi = typerepo.findAll();
		List<Category>kategorije = catrepo.findAll();
		List<Address>adrese = addressrepo.findAll();
		List<AdditionalService>addService = additionalservicerepo.findAll();
		List<AccUnitPrice>acPrice = acurepo.findAll();
		List<AccommodationObject> accObj = accObjRepo.findAll();
		List<AccommodationUnit> accUnit = aunitrepo.findAll();
		List<Reservation> reservations = res.findAll();
		for(Type tp : tipovi) {
			com.ftn.accommodationservice.xsd.Type tipp = new com.ftn.accommodationservice.xsd.Type();
			tipp = conv.covertType(tp);
			response.getType().add(tipp);
		}
		
		for(Category ct : kategorije) {
			com.ftn.accommodationservice.xsd.Category cat = new com.ftn.accommodationservice.xsd.Category();
			cat = conv.convertCategory(ct);
			response.getCategory().add(cat);
		}
		
		for(Address adr : adrese) {
			com.ftn.accommodationservice.xsd.Address adresa = new com.ftn.accommodationservice.xsd.Address();
			adresa = conv.convertAddress(adr);
			response.getAddress().add(adresa);
		}
		
		for(AdditionalService ads : addService) {
			com.ftn.accommodationservice.xsd.AdditionalService ad = new com.ftn.accommodationservice.xsd.AdditionalService();
			ad = conv.convertAdditionalService(ads);
			response.getAdditionalService().add(ad);
		}
		
		for(AccUnitPrice ac : acPrice) {
			com.ftn.accommodationservice.xsd.AccUnitPrice acp = new com.ftn.accommodationservice.xsd.AccUnitPrice();
			acp = conv.convertAccUnitPrice(ac);
			response.getAccUnitPrice().add(acp);
		}
		
		for(AccommodationObject aco : accObj) {
			com.ftn.accommodationservice.xsd.AccommodationObject acob = new com.ftn.accommodationservice.xsd.AccommodationObject();
			acob = conv.convertAccommodation(aco);
			
			response.getAccommodationObject().add(acob);
		}
		
		for(AccommodationUnit ac : accUnit) {
			com.ftn.accommodationservice.xsd.AccommodationUnit acu = new com.ftn.accommodationservice.xsd.AccommodationUnit();
			acu = conv.convertAccUnit(ac);
			response.getAccommodationUnit().add(acu);
		}
		
		for(Reservation ress : reservations) {
			com.ftn.accommodationservice.xsd.Reservation reser = new com.ftn.accommodationservice.xsd.Reservation();
			reser = conv.convertReservation(ress);
			response.getReservation().add(reser);
		}
		
		return response;
	}
	
	@PayloadRoot(namespace = "http://ftn.com/accommodationservice/xsd", localPart = "PostAddressRequest")
	@ResponsePayload
	@Transactional
	public PostAddressResponse createAddress(@RequestPayload PostAddressRequest request) {
		Address adr = new Address();
		adr.setLatitude(request.getAddress().getLatitude());
		adr.setLongitude(request.getAddress().getLongitude());
		adr.setPostalCode(request.getAddress().getPostalCode());
		adr.setState(request.getAddress().getState());
		adr.setStreet(request.getAddress().getStreet());
		adr.setStreetNumber(request.getAddress().getStreetNumber());
		adr.setTown(request.getAddress().getTown());
		adr = addressrepo.save(adr);
		PostAddressResponse response = new PostAddressResponse();
		response.setAddress(request.getAddress());
		response.getAddress().setId(adr.getId());
		return response;
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
	
	@PayloadRoot(namespace = "http://ftn.com/accommodationservice/xsd", localPart = "PostAccUnitPriceRequest")
	@ResponsePayload
	@Transactional
	public PostAccUnitPriceResponse createPrice(@RequestPayload PostAccUnitPriceRequest request) {
		AccUnitPrice price = new AccUnitPrice();
		price.setEndDate(request.getAccUnitPrice().getEndDate());
		price.setPrice(request.getAccUnitPrice().getPrice());
		price.setStartDate(request.getAccUnitPrice().getStartDate());
		price = acurepo.save(price);
		PostAccUnitPriceResponse response = new PostAccUnitPriceResponse();
		request.getAccUnitPrice().setId(price.getId());
		response.setAccUnitPrice(request.getAccUnitPrice());
		return response;
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
	
	@PayloadRoot(namespace = "http://ftn.com/accommodationservice/xsd", localPart = "GetAllAccUnitPriceRequest")
	@ResponsePayload
	@Transactional
	public GetAllAccUnitPriceResponse getAllAccUnitPrice(@RequestPayload GetAllAccUnitPriceRequest request) {
	
		List<AccUnitPrice> lista = acurepo.findAll();
		GetAllAccUnitPriceResponse response = new GetAllAccUnitPriceResponse();
		for(AccUnitPrice ac: lista) {
			com.ftn.accommodationservice.xsd.AccUnitPrice price = new com.ftn.accommodationservice.xsd.AccUnitPrice();
			price.setEndDate(ac.getEndDate());
			price.setId(ac.getId());
			price.setStartDate(ac.getStartDate());
			price.setPrice(ac.getPrice());
			response.getAccUnitPrice().add(price);
		}
		return response;
	}
	@PayloadRoot(namespace = "http://ftn.com/accommodationservice/xsd", localPart = "GetAllAdditionalServiceRequest")
	@ResponsePayload
	@Transactional
	public GetAllAdditionalServiceResponse getAllAdditionalService(@RequestPayload GetAllAdditionalServiceRequest request) {
		System.out.println("debug");
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
		
		
		System.out.println(request.getAccommodationUnit().getAdditionalServices().size() + " text");
		unitModel.setAccommodationObject(ao);
		List<AdditionalService> servisi = new ArrayList<>();
		for(com.ftn.accommodationservice.xsd.AdditionalService xsd : request.getAccommodationUnit().getAdditionalServices())  {
			System.out.println("usao34");
			unitModel.getAdditionalServices().add(additionalservicerepo.getOne(xsd.getId()));
		}
		System.out.println(unitModel.getAdditionalServices().size() + " text222");
		//unitModel.setAdditionalServices(servisi);
		
		unitModel.setId(request.getAccommodationUnit().getId());
		//unitModel.setImage(request.getAccommodationUnit().getImage());
		AccUnitPrice acup = new AccUnitPrice();
		acup.setEndDate(request.getAccommodationUnit().getPrice().getEndDate());
		acup.setStartDate(request.getAccommodationUnit().getPrice().getStartDate());
		acup.setPrice(request.getAccommodationUnit().getPrice().getPrice());
		acup.setAccommodationUnit(aunitrepo.getOne(request.getAccommodationUnit().getId()));
		unitModel.setPrice(acurepo.save(acup));
		unitModel.setNumberOfBeds(request.getAccommodationUnit().getNumberOfBeds());
		unitModel.setRating(request.getAccommodationUnit().getRating());
		List<Reservation> ress = new ArrayList<>();
		for(com.ftn.accommodationservice.xsd.Reservation rr : request.getAccommodationUnit().getReservations()) {
			ress.add(res.getOne(rr.getId()));
		}
		
		unitModel = aunitrepo.save(unitModel);
		
		com.ftn.accommodationservice.xsd.AccommodationUnit rspau = new com.ftn.accommodationservice.xsd.AccommodationUnit();
		rspau.setId(unitModel.getId());
		rspau.setAccObjectId(request.getAccommodationUnit().getAccObjectId());
		rspau.setBalcony(request.getAccommodationUnit().isBalcony());
		rspau.setDescription(request.getAccommodationUnit().getDescription());
		rspau.setNumberOfBeds(request.getAccommodationUnit().getNumberOfBeds());
		rspau.setPrice(request.getAccommodationUnit().getPrice());
		rspau.setRating(request.getAccommodationUnit().getRating());
		request.getAccommodationUnit().getAdditionalServices().forEach(r->{rspau.getAdditionalServices().add(r);});
		response.setAccommodationUnit(rspau);
		
		return response;
	}

}
