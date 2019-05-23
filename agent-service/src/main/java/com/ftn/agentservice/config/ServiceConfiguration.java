package com.ftn.agentservice.config;

import javax.xml.ws.soap.MTOMFeature;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ftn.agentservice.soap.AccommodationWS;
import com.ftn.agentservice.soap.AccommodationWSSetup;

@Configuration
public class ServiceConfiguration {
	
	@Bean
	public AccommodationWS getAccomodationWebServiceSoap() {
		AccommodationWSSetup service = new AccommodationWSSetup();
		AccommodationWS sservice = service.getAccomodationWebServicePort(new MTOMFeature());
		return sservice;
	}

}
