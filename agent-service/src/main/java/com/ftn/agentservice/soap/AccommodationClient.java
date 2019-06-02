package com.ftn.agentservice.soap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.ftn.accommodationservice.xsd.AccommodationUnit;
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

public class AccommodationClient extends WebServiceGatewaySupport  {

	private static final Logger log = LoggerFactory.getLogger(AccommodationClient.class);

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
		request.setId(1);
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
	
	public GetAccUnitPriceResponse getAccUnitPrice(Long id) {
		GetAccUnitPriceRequest request = new GetAccUnitPriceRequest();
		request.setId(1);
		return (GetAccUnitPriceResponse) getWebServiceTemplate().marshalSendAndReceive(request);
	}
	
	public GetAccommodationUnitResponse saveNewAcc(AccommodationUnit au) {
		GetAccommodationUnitRequest request = new GetAccommodationUnitRequest();
		request.setAccommodationUnit(au);
		return (GetAccommodationUnitResponse) getWebServiceTemplate().marshalSendAndReceive(request);
	}
}
