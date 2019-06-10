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
	public AccommodationClient accommodationClient(Jaxb2Marshaller marshaller) throws Exception {
		AccommodationClient client = new AccommodationClient();
		client.setDefaultUri("https://localhost:8082/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		//setUpInterceptors(client);
		ClientInterceptor [] interceptors= new ClientInterceptor [] {securityInterceptor()};
        client.setInterceptors(interceptors);
		return client;
	}
	
	@Bean
    public Wss4jSecurityInterceptor securityInterceptor() throws Exception {
        Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();

        // set security actions
        securityInterceptor.setSecurementActions("Timestamp Signature Encrypt");

        // sign the request
        securityInterceptor.setSecurementUsername("agent");
        securityInterceptor.setSecurementPassword("password");
        securityInterceptor.setSecurementSignatureCrypto(getCryptoFactoryBean().getObject());

        // encrypt the request
        securityInterceptor.setSecurementEncryptionUser("agent");
        securityInterceptor.setValidationActor("agent");
        securityInterceptor.setSecurementEncryptionCrypto(getCryptoFactoryBean().getObject());
        securityInterceptor.setSecurementEncryptionParts("{Content}{http://ftn.com/accommodationservice/xsd}GetAllAdditionalServiceRequest");
        securityInterceptor.setSecurementSignatureKeyIdentifier("DirectReference");

        return securityInterceptor;
    }

    @Bean
    public CryptoFactoryBean getCryptoFactoryBean() throws IOException {
        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        cryptoFactoryBean.setKeyStorePassword("password");
        cryptoFactoryBean.setKeyStoreLocation(new ClassPathResource("agent.jks"));
        return cryptoFactoryBean;
    }
}
