package com.example.jappfinder.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jappfinder.driver.PropertyInfo;
import com.example.jappfinder.driver.SearchFilter;
import com.example.jappfinder.services.Scrapper;


@RestController
public class PropertyController {

	private final Scrapper scrapper;
	
	public PropertyController (Scrapper scrapper) {
		this.scrapper = scrapper;
	}
	
	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping("/Property")
	public List<PropertyInfo> getProperties() {
		return scrapper.getProperties(new SearchFilter());
	}
	
	@GetMapping("/Property/AdditionalInfo")
	public List<PropertyInfo> getPropertiesInfo() {
		return null;
	}
}
