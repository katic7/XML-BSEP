package com.ftn.authservice.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

	@GetMapping
	@Secured(value = { "" }) 
	public String test() {
		return "Protected by ADMIN";
	}
	
}
