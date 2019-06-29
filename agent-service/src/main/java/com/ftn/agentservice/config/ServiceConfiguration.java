package com.ftn.agentservice.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.support.CryptoFactoryBean;

import com.ftn.agentservice.soap.AccommodationClient;
import com.ftn.agentservice.soap.ReservationClinet;


@Configuration
public class ServiceConfiguration {


	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		//marshaller.setSupportDtd(false);
		//marshaller.setProcessExternalEntities(false);
		// this package must match the package in the  specified in
		// pom.xml
		String [] packagesToScan = {"com.ftn.accommodationservice.xsd"};
		marshaller.setPackagesToScan(packagesToScan);
//		marshaller.setContextPath("com.ftn.accommodationservice.xsd");
		return marshaller;
	}

	@Bean
	public AccommodationClient accClient(Jaxb2Marshaller marshaller) throws Exception {
		AccommodationClient client = new AccommodationClient();
		client.setDefaultUri("https://localhost:8082/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
	@Bean
	public ReservationClinet resClient(Jaxb2Marshaller marshaller) {
		ReservationClinet client = new ReservationClinet();
		client.setDefaultUri("https://localhost:8083/ws");
		client.setMarshaller(marshaller);
		/*ClientInterceptor[] interceptors = new ClientInterceptor[] {securityInterceptor()};
		client.setInterceptors(interceptors);*/
		client.setUnmarshaller(marshaller);
		return client;
	}
	

}