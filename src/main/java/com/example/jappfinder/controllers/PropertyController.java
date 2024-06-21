package com.example.jappfinder.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jappfinder.driver.PropertyInfo;
import com.example.jappfinder.driver.SearchFilter;
import com.example.jappfinder.services.Scrapper;


@RestController
@RequestMapping("/Property")
public class PropertyController {

	private final Scrapper scrapper;
	
	public PropertyController (Scrapper scrapper) {
		this.scrapper = scrapper;
	}

	@GetMapping
	public List<PropertyInfo> getProperties() {
		return scrapper.getProperties(new SearchFilter());
	}
	
	@GetMapping("/AdditionalInfo")
	public List<PropertyInfo> getPropertiesInfo() {
		return null;
	}
}
