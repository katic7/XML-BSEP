package com.ftn.accommodationservice;

import java.security.NoSuchAlgorithmException;

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
	    System.setProperty("javax.net.ssl.keyStore", "src/main/resources/MyClient.jks");
	    System.setProperty("javax.net.ssl.keyStorePassword", "password");
	    System.setProperty("javax.net.ssl.trustStore", "src/main/resources/MyClient.jks");
	    System.setProperty("javax.net.ssl.trustStorePassword", "password");
	    EurekaJerseyClientBuilder builder = new EurekaJerseyClientBuilder();
	    builder.withClientName("MyClient");
	    builder.withSystemSSLConfiguration();
	    builder.withMaxTotalConnections(10);
	    builder.withMaxConnectionsPerHost(10);
	    args.setEurekaJerseyClient(builder.build());
	    return args;
	}
	
	static
	 {
	
	 javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
		 new javax.net.ssl.HostnameVerifier() {
		 
		 public boolean verify(String hostname,
		 javax.net.ssl.SSLSession sslSession) {
			 if (hostname.equals("localhost")) {
			 return true;
			 }
			 return false;
			 }
		 });
	 }
	 
	 @Bean
	 public RestTemplate template() throws Exception{
		 RestTemplate template = new RestTemplate();
		 return template;
	 }

	public static void main(String[] args) {
		SpringApplication.run(AccommodationServiceApplication.class, args);
	}
	


}
