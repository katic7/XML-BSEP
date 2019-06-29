package com.ftn.accommodationservice;

import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl.EurekaJerseyClientBuilder;

@SpringBootApplication
@EnableDiscoveryClient
public class AccommodationServiceApplication {
	
	@Bean
	public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs() throws NoSuchAlgorithmException {
		DiscoveryClient.DiscoveryClientOptionalArgs args = new DiscoveryClient.DiscoveryClientOptionalArgs();
		System.setProperty("javax.net.ssl.keyStore", "src/main/resources/accommodation.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "password");
		System.setProperty("javax.net.ssl.trustStore", "src/main/resources/accommodation.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "password");
		EurekaJerseyClientBuilder builder = new EurekaJerseyClientBuilder();
		builder.withClientName("accommodation");
		builder.withSystemSSLConfiguration();
		builder.withMaxTotalConnections(10);
		builder.withMaxConnectionsPerHost(10);
		args.setEurekaJerseyClient(builder.build());
		return args;
	}
	

	 @Bean
	 public RestTemplate template() throws Exception{
		 RestTemplate template = new RestTemplate();
		 return template;
	 }

	 
	    private static final Logger logger = LoggerFactory.getLogger(AccommodationServiceApplication.class);
	public static void main(String[] args) {

		System.setProperty("KEY_STORE_CLASSPATH", "src/main/resources/accommodation.jks");
		System.setProperty("KEY_STORE_PASSWORD", "password");
		System.setProperty("KEY_ALIAS", "accommodation");
		System.setProperty("WEB_SERVICE_USERNAME", "nemanjica");
		System.setProperty("WEB_SERVICE_PASSWORD", "password");
		System.setProperty("EUREKA_INSTANCE_HOSTNAME", "localhost");
		System.setProperty("CLIENT_SERVICEURL_DEFAULTZONE", "https://localhost:8761/eureka/");
		System.setProperty("DATASOURCE_URL_JDBC_MYSQL", "//localhost:3306/megatravel?useSSL=false&createDatabaseIfNotExist=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		System.setProperty("DATASOURCE_USERNAME", "root");
		System.setProperty("DATASOURCE_PASSWORD", "root");
		SpringApplication.run(AccommodationServiceApplication.class, args);
	}
	


}
