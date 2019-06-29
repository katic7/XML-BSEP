package com.ftn.eurekaservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServiceApplication {

	public static void main(String[] args) {
		
		File file = new File("/eureka-service/src/main/resources/test.txt");
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Nema pristup test.txt fajlu!!");
		}
		
		System.setProperty("KEY_STORE_CLASSPATH", "src/main/resources/eureka.jks");
		System.setProperty("KEY_STORE_PASSWORD", "password");
		System.setProperty("KEY_ALIAS", "eureka");
		System.setProperty("TRUST_STORE_CLASSPATH", "src/main/resources/eureka.jks");
		System.setProperty("TRUST_STORE_PASSWORD", "password");
		/*System.setProperty("javax.net.ssl.keyStore", "src/main/resources/key-eureka.jks");
	    System.setProperty("javax.net.ssl.keyStorePassword", "password");
		System.setProperty("javax.net.ssl.trustStore","src/main/resources/trust-eureka.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "password");*/
		SpringApplication.run(EurekaServiceApplication.class, args);
		
	}

}
