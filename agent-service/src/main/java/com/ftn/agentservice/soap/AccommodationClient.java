package com.ftn.agentservice.soap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.ftn.accommodationservice.xsd.GetAccommodationObjectRequest;
import com.ftn.accommodationservice.xsd.GetAccommodationObjectResponse;
import com.ftn.accommodationservice.xsd.GetCategoryRequest;
import com.ftn.accommodationservice.xsd.GetCategoryResponse;
import com.ftn.accommodationservice.xsd.GetTestRequest;
import com.ftn.accommodationservice.xsd.GetTestResponse;

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
	
}
