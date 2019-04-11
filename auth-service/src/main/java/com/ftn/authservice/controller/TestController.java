package com.ftn.authservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

	@GetMapping("/admin")
	@PreAuthorize("hasRole('SYSTEMADMIN')")
	public String test() {
		return "Protected by ADMIN";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public String test2() {
		return "Protected by USER";
	}
	
	@GetMapping("/test")
	public String test3() {
		return "From Auth service!";
	}
	
}
