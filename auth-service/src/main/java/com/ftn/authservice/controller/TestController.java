package com.ftn.authservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/test")
public class TestController {

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String test() {
		return "Protected by ADMIN";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public String test2() {
		return "Protected by USER";
	}
	
	@GetMapping("/test")
	@PreAuthorize("hasAuthority('AddContent')")
	public String test3() {
		return "From Auth service!";
	}
	
	@GetMapping("/testauth")
	public String test4() {
		RestTemplate rt = new RestTemplate();
		return rt.getForObject("http://localhost:8082/api/addresses/test2", String.class);
	}
	
}
