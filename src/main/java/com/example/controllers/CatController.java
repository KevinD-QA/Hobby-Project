package com.example.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cat")
public class CatController {

	public CatController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/hello")
	public String greeting() {
		return "Hello cat";
	}

}
