package com.example.jappfinder.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jappfinder.domain.PropertyDTO;
import com.example.jappfinder.driver.PropertyInfo;
import com.example.jappfinder.services.PropertyService;


@RestController
@RequestMapping("/Property")
public class PropertyController {
	
	private final PropertyService propertyService;
	
	public PropertyController (PropertyService propertyService) {
		this.propertyService = propertyService;
	}

	@GetMapping
	public List<PropertyDTO> getProperties() {				
		return propertyService.getAll().stream().map(p -> p.mapToDTO()).toList();
	}
	
	@GetMapping("/AdditionalInfo")
	public List<PropertyInfo> getPropertiesInfo() {
		return null;
	}
	
}
