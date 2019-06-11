package com.ftn.agentservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.ftn.agentservice.soap.AccommodationClient;


@Configuration
public class ServiceConfiguration {


	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the  specified in
		// pom.xml
		//String [] packagesToScan = {"com.ftn.accommodationservice.xsd",""};
		//marshaller.setPackagesToScan(packagesToScan);
		marshaller.setContextPath("com.ftn.accommodationservice.xsd");
		return marshaller;
	}

	@Bean
	public AccommodationClient movieClient(Jaxb2Marshaller marshaller) {
		AccommodationClient client = new AccommodationClient();
		client.setDefaultUri("https://localhost:8082/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}