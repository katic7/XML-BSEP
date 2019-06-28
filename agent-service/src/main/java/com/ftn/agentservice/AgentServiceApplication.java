package com.ftn.agentservice;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.ftn.agentservice.service.CustomChatService;
//import com.netflix.discovery.DiscoveryClient;
//import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl.EurekaJerseyClientBuilder;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl.EurekaJerseyClientBuilder;

import io.grpc.Server;
import io.grpc.ServerBuilder;


@SpringBootApplication
@EnableDiscoveryClient
public class AgentServiceApplication {
	
	@Bean
	public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs() throws NoSuchAlgorithmException {
		DiscoveryClient.DiscoveryClientOptionalArgs args = new DiscoveryClient.DiscoveryClientOptionalArgs();
		System.setProperty("javax.net.ssl.keyStore", "src/main/resources/agent.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "password");
		System.setProperty("javax.net.ssl.trustStore", "src/main/resources/agent.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "password");
		EurekaJerseyClientBuilder builder = new EurekaJerseyClientBuilder();
		builder.withClientName("agent");
		
		builder.withSystemSSLConfiguration();
		builder.withMaxTotalConnections(10);
		builder.withMaxConnectionsPerHost(10);
		args.setEurekaJerseyClient(builder.build());
		return args;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		System.setProperty("KEY_STORE_CLASSPATH", "classpath:agent.jks");
		System.setProperty("KEY_STORE_PASSWORD", "password");
		System.setProperty("KEY_ALIAS", "agent");
		System.setProperty("WEB_SERVICE_USERNAME", "nemanjica");
		System.setProperty("WEB_SERVICE_PASSWORD", "password");
		System.setProperty("EUREKA_INSTANCE_HOSTNAME", "localhost");
		System.setProperty("CLIENT_SERVICEURL_DEFAULTZONE", "https://localhost:8761/eureka/");
		System.setProperty("DATASOURCE_USERNAME", "root");
		System.setProperty("DATASOURCE_PASSWORD", "root");
		SpringApplication.run(AgentServiceApplication.class, args);
		Server server = ServerBuilder.forPort(9090).addService(new CustomChatService()).build();
		
		server.start();
		
		System.out.println("Server pokrenut na portu "+ server.getPort());
		server.awaitTermination();
	}
	
	
}
