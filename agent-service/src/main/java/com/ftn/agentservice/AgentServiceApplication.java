package com.ftn.agentservice;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ftn.accommodationservice.xsd.GetAccommodationObjectResponse;
import com.ftn.accommodationservice.xsd.GetCategoryResponse;
import com.ftn.agentservice.soap.AccommodationClient;


@SpringBootApplication
public class AgentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentServiceApplication.class, args);
	}
	
	@Autowired
	private AccommodationClient client;
	
	@PostConstruct
	public void init() {
		GetAccommodationObjectResponse response = client.getAccommodation(Long.valueOf(1));
		System.out.println(response.getAccommodationObject().getDescription());
	}

}
