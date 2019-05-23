package com.ftn.agentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ftn.agentservice.soap.AccommodationWS;

@Controller
@RequestMapping("/api/test")
public class TestController {
	
	@Autowired
	private AccommodationWS accws;
	
	@GetMapping
	public String secured(){
		System.out.println("AGENT SERVICE");
		
		System.out.println(accws.testMethod());
		
		
		return "";
	}

}
