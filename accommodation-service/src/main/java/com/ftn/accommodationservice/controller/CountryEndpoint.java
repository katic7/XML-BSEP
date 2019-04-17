package com.ftn.accommodationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ftn.accommodationservice.repository.CountryRepository;
import com.ftn.accomodation_service.model.GetCountryRequest;
import com.ftn.accomodation_service.model.GetCountryResponse;

@Endpoint
public class CountryEndpoint {

	private static final String NAMESPACE_URI = "www.ftn.com/accomodation-service/model";
	 
    private CountryRepository countryRepository;
 
    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
 
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));
 
        return response;
    }
}
