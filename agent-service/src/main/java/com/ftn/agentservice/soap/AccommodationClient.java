package com.ftn.agentservice.soap;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.ftn.accommodationservice.xsd.AccUnitPrice;
import com.ftn.accommodationservice.xsd.AccommodationUnit;
import com.ftn.accommodationservice.xsd.Address;
import com.ftn.accommodationservice.xsd.Category;
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
import com.ftn.accommodationservice.xsd.Type;
import com.ftn.agentservice.dto.AccommodationObjectDTO;
import com.ftn.agentservice.model.AccommodationObject;
import com.ftn.agentservice.model.AdditionalService;
import com.ftn.agentservice.model.Reservation;
import com.ftn.agentservice.model.User;
import com.ftn.agentservice.repository.AccommodationObjectRepository;
import com.ftn.agentservice.repository.AccommodationUnitPriceRepository;
import com.ftn.agentservice.repository.AccommodationUnitRepository;
import com.ftn.agentservice.repository.AdditionalServiceRepository;
import com.ftn.agentservice.repository.AddressRepository;
import com.ftn.agentservice.repository.CategoryRepository;
import com.ftn.agentservice.repository.TypeRepository;

public class AccommodationClient extends WebServiceGatewaySupport  {

	private static final Logger log = LoggerFactory.getLogger(AccommodationClient.class);
	
	@Autowired
	private AddressRepository addressRepo;

	@Autowired
	private AccommodationUnitRepository aunitrepo;
	
	@Autowired
	private AdditionalServiceRepository additionalservicerepo;
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private AccommodationUnitPriceRepository acurepo;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private AccommodationObjectRepository accObjRepo;
	
	public GetAccommodationObjectResponse getAccommodation(Long id) {
		GetAccommodationObjectRequest request = new GetAccommodationObjectRequest();
		request.setId(id);
		log.info("Requesting Accommodation By id = " + id);
		return (GetAccommodationObjectResponse) getWebServiceTemplate().marshalSendAndReceive(request);

	}
	
	public GetTestResponse test(int id) {
		GetTestRequest req = new GetTestRequest();
		req.setId(1);
		return (GetTestResponse) getWebServiceTemplate().marshalSendAndReceive(req);
	}
	
	public PostObjectUnitsResponse getUnits(Long id) {
		PostObjectUnitsRequest request = new PostObjectUnitsRequest();
		request.setId(id);
		return (PostObjectUnitsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
	}
	
	public GetCategoryResponse getCategory(Long id) {
		GetCategoryRequest request = new GetCategoryRequest();
		request.setId(1);
		return (GetCategoryResponse) getWebServiceTemplate().marshalSendAndReceive(request);
	}
	
	public GetTypeResponse getType(Long id) {
		GetTypeRequest request = new GetTypeRequest();
		request.setId(1);
		return (GetTypeResponse) getWebServiceTemplate().marshalSendAndReceive(request);
	}
	
	public GetAddressResponse getAddress(Long id) {
		GetAddressRequest request = new GetAddressRequest();
		request.setId(id);
		return (GetAddressResponse) getWebServiceTemplate().marshalSendAndReceive(request);
	}
	
	public GetAdditionalServiceResponse getAdditionalService(Long id) {
		GetAdditionalServiceRequest request = new GetAdditionalServiceRequest();
		request.setId(1);
		return (GetAdditionalServiceResponse) getWebServiceTemplate().marshalSendAndReceive(request);
	}
	
	public GetAllAdditionalServiceResponse getAllAdditionalServiceResponse() {
		GetAllAdditionalServiceRequest request = new GetAllAdditionalServiceRequest();
		return (GetAllAdditionalServiceResponse) getWebServiceTemplate().marshalSendAndReceive(request);
	}
	
	public GetAllAccUnitPriceResponse getAllPrices() {
		GetAllAccUnitPriceRequest request = new GetAllAccUnitPriceRequest();
		return (GetAllAccUnitPriceResponse) getWebServiceTemplate().marshalSendAndReceive(request);
	}
	
	public GetAccUnitPriceResponse getAccUnitPrice(Long id) {
		GetAccUnitPriceRequest request = new GetAccUnitPriceRequest();
		request.setId(1);
		return (GetAccUnitPriceResponse) getWebServiceTemplate().marshalSendAndReceive(request);
	}
	
	public GetAccommodationUnitResponse saveNewAcc(AccommodationUnit au) {
		GetAccommodationUnitRequest request = new GetAccommodationUnitRequest();
		request.setAccommodationUnit(au);
		com.ftn.agentservice.model.AccommodationUnit unitModel = new com.ftn.agentservice.model.AccommodationUnit();
		unitModel.setBalcony(request.getAccommodationUnit().isBalcony());
		unitModel.setDescription(request.getAccommodationUnit().getDescription());
		AccommodationObject ao = accObjRepo.getOne(request.getAccommodationUnit().getAccObjectId());
		
		
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
		com.ftn.agentservice.model.AccUnitPrice acup = new com.ftn.agentservice.model.AccUnitPrice();
		acup.setEndDate(request.getAccommodationUnit().getPrice().getEndDate());
		acup.setStartDate(request.getAccommodationUnit().getPrice().getStartDate());
		acup.setPrice(request.getAccommodationUnit().getPrice().getPrice());
		acup.setAccommodationUnit(aunitrepo.getOne(request.getAccommodationUnit().getId()));
		unitModel.setPrice(acurepo.save(acup));
		unitModel.setNumberOfBeds(request.getAccommodationUnit().getNumberOfBeds());
		unitModel.setRating(request.getAccommodationUnit().getRating());
		List<Reservation> ress = new ArrayList<>();
		
		
		unitModel = aunitrepo.save(unitModel);
		
		com.ftn.accommodationservice.xsd.AccommodationUnit rspau = new com.ftn.accommodationservice.xsd.AccommodationUnit();
		rspau.setId(unitModel.getId());
		rspau.setAccObjectId(request.getAccommodationUnit().getAccObjectId());
		rspau.setBalcony(request.getAccommodationUnit().isBalcony());
		rspau.setDescription(request.getAccommodationUnit().getDescription());
		rspau.setNumberOfBeds(request.getAccommodationUnit().getNumberOfBeds());
		rspau.setPrice(request.getAccommodationUnit().getPrice());
		rspau.setRating(request.getAccommodationUnit().getRating());
		return (GetAccommodationUnitResponse) getWebServiceTemplate().marshalSendAndReceive(request);
	}
	
	public PostAccommodationObjectResponse createAccObject(AccommodationObjectDTO acc, User usr) {
		System.out.println(acc.getName() + "NAMEEE");
		com.ftn.accommodationservice.xsd.AccommodationObject aco = new com.ftn.accommodationservice.xsd.AccommodationObject();
		PostAccommodationObjectRequest request = new PostAccommodationObjectRequest();
		request.setAccommodationObject(aco);
		request.getAccommodationObject().setName(acc.getName());
		request.getAccommodationObject().setDescription(acc.getDescription());
		request.getAccommodationObject().setFreeCancelation(acc.isFreeCacelation());
		request.getAccommodationObject().setDaysToCancel(acc.getDaystoCancel());
		request.setUserId(usr.getId());
		
		Type tip = new Type();
		com.ftn.agentservice.model.Type type = typeRepository.getOne(acc.getTypeId());
		tip.setId(acc.getTypeId());
		tip.setName(type.getName());
		
		Category cat = new Category();
		com.ftn.agentservice.model.Category kateg = categoryRepository.getOne(acc.getCategoryId());
		cat.setId(acc.getCategoryId());
		cat.setName(kateg.getName());
		
		Address adr = new Address();
		com.ftn.agentservice.model.Address address = addressRepo.getOne(acc.getAddressId());
		adr.setId(address.getId());
		adr.setLatitude(address.getLatitude());
		adr.setLongitude(address.getLongitude());
		adr.setPostalCode(address.getPostalCode());
		adr.setStreet(address.getStreet());
		adr.setStreetNumber(address.getStreetNumber());
		adr.setTown(address.getTown());
		
		request.getAccommodationObject().setAddress(adr);
		request.getAccommodationObject().setCategory(cat);
		request.getAccommodationObject().setType(tip);
		
		AccommodationObject accObj = new AccommodationObject();
		accObj.setAddress(address);
		accObj.setCategoryId(kateg);
		accObj.setTypeId(type);
		accObj.setName(acc.getName());
		accObj.setDescription(acc.getDescription());
		System.out.println(acc.isFreeCacelation() + "ADSADSD");
		accObj.setFreeCancelation(acc.isFreeCacelation());
		accObj.setDaysToCancel(acc.getDaystoCancel());
		accObjRepo.save(accObj);
		return (PostAccommodationObjectResponse) getWebServiceTemplate().marshalSendAndReceive(request);
	}
	
	public PostAddressResponse createAddress(Address adr) {
		PostAddressRequest request = new PostAddressRequest();
		com.ftn.agentservice.model.Address adresa = new com.ftn.agentservice.model.Address();
		adresa.setLatitude(adr.getLatitude());
		adresa.setLongitude(adr.getLongitude());
		adresa.setPostalCode(adr.getPostalCode());
		adresa.setStreet(adr.getStreet());
		adresa.setStreetNumber(adr.getStreetNumber());
		adresa.setTown(adr.getTown());
		addressRepo.save(adresa);
		request.setAddress(adr);
		return (PostAddressResponse) getWebServiceTemplate().marshalSendAndReceive(request);
	}

	public PostAccUnitPriceResponse createPrice(AccUnitPrice acc) {
		// TODO Auto-generated method stub
		PostAccUnitPriceRequest request = new PostAccUnitPriceRequest();
		request.setAccUnitPrice(acc);
		return (PostAccUnitPriceResponse) getWebServiceTemplate().marshalSendAndReceive(request);
	}
	
	/*public PostAccommodationObjectResponse createObject(AccommodationObject acc) {
		PostAccommodationObjectRequest requst = new PostAccommodationObjectRequest();
		Address adr = new Address();
	}*/
}
