package com.ftn.authservice;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl.EurekaJerseyClientBuilder;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthServiceApplication {
		
	@Bean
	public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs() throws NoSuchAlgorithmException {
		DiscoveryClient.DiscoveryClientOptionalArgs args = new DiscoveryClient.DiscoveryClientOptionalArgs();
		System.setProperty("javax.net.ssl.keyStore", "src/main/resources/auth.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "password");
		System.setProperty("javax.net.ssl.trustStore", "src/main/resources/auth.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "password");
		EurekaJerseyClientBuilder builder = new EurekaJerseyClientBuilder();
		builder.withClientName("auth");
		builder.withSystemSSLConfiguration();
		builder.withMaxTotalConnections(10);
		builder.withMaxConnectionsPerHost(10);
		args.setEurekaJerseyClient(builder.build());
		return args;
	}

	public static void main(String[] args) {
		System.setProperty("KEY_STORE_CLASSPATH", "src/main/resources/auth.jks");
		System.setProperty("KEY_STORE_PASSWORD", "password");
		System.setProperty("KEY_ALIAS", "auth");
		System.setProperty("EUREKA_INSTANCE_HOSTNAME", "localhost");
		System.setProperty("CLIENT_SERVICEURL_DEFAULTZONE", "https://localhost:8761/eureka/");
		System.setProperty("DATASOURCE_URL_JDBC_MYSQL", "jdbc:mysql://localhost:3306/megatravel?useSSL=false&createDatabaseIfNotExist=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		System.setProperty("DATASOURCE_USERNAME", "root");
		System.setProperty("DATASOURCE_PASSWORD", "root");
		System.setProperty("MAIL_HOST", "smtp.gmail.com");
		System.setProperty("MAIL_USERNAME", "af083085@gmail.com");
		System.setProperty("MAIL_PASSWORD", "ovojetest123");
		System.setProperty("AUTH_APP_JWTSECRET", "jwtAuthSecretKey");
		SpringApplication.run(AuthServiceApplication.class, args);
	}
	

}
