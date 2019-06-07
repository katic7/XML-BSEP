package com.ftn.eurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServiceApplication {

	public static void main(String[] args) {
		System.setProperty("KEY_STORE_PASSWORD", "password");
		/*System.setProperty("javax.net.ssl.keyStore", "src/main/resources/key-eureka.jks");
	    System.setProperty("javax.net.ssl.keyStorePassword", "password");
		System.setProperty("javax.net.ssl.trustStore","src/main/resources/trust-eureka.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "password");*/
		SpringApplication.run(EurekaServiceApplication.class, args);
	}

}
