package com.ftn.zuulserver;

import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.ftn.zuulserver.filter.ZuulPreFilter;

@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class ZuulServerApplication {

	@Bean
	public CorsFilter corsFilter() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    final CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.addAllowedOrigin("*");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("OPTIONS");
	    config.addAllowedMethod("HEAD");
	    config.addAllowedMethod("GET");
	    config.addAllowedMethod("PUT");
	    config.addAllowedMethod("POST");
	    config.addAllowedMethod("DELETE");
	    config.addAllowedMethod("PATCH");
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}
	
	 @Bean
	 public RestTemplate template() throws Exception{
		 RestTemplate template = new RestTemplate();
		 return template;
	 }

	public static void main(String[] args) {
		System.setProperty("KEY_STORE_CLASSPATH", "src/main/resources/zuul.jks");
		System.setProperty("KEY_STORE_PASSWORD", "password");
		System.setProperty("KEY_ALIAS", "zuul");
		System.setProperty("EUREKA_INSTANCE_HOSTNAME", "localhost");
		System.setProperty("CLIENT_SERVICEURL_DEFAULTZONE", "https://localhost:8761/eureka/");
		System.setProperty("ZUUL_ROUTES_RESERVATIONSERVICE_SERVICEID", "reservation-service");
		System.setProperty("ZUUL_ROUTES_ACCOMMODATIONSERVICE_SERVICEID", "accommodation-service");
		System.setProperty("ZUUL_ROUTES_BEZBEDNOST_SERVICEID", "pki-service");
		System.setProperty("ZUUL_ROUTES_AGENTSERVICE_SERVICEID", "agent-service");
		System.setProperty("ZUUL_ROUTES_AUTHSERVICE_SERVICEID", "auth-service");
		System.setProperty("TRUST_STORE_CLASSPATH", "zuul.jks");
		System.setProperty("TRUST_STORE_PASSWORD", "classpath:password");
		SpringApplication.run(ZuulServerApplication.class, args);
	}

	@Bean
    public ZuulPreFilter simpleFilter() {
      return new ZuulPreFilter();
    }
}
