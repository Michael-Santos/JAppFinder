package com.example.jappfinder.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jappfinder.domain.PropertyDTO;
import com.example.jappfinder.driver.PropertyInfo;
import com.example.jappfinder.driver.SearchFilter;
import com.example.jappfinder.repositories.PropertyRepository;
import com.example.jappfinder.repositories.models.Property;
import com.example.jappfinder.services.PropertyService;
import com.example.jappfinder.services.Scrapper;


@RestController
@RequestMapping("/Property")
public class PropertyController {
	
	private final PropertyService propertyService;
	
	public PropertyController (PropertyService propertyService) {
		this.propertyService = propertyService;
	}

	@GetMapping
	public List<PropertyDTO> getProperties() {				
		return propertyService.getAll().stream().map(p -> mapToDTO(p)).toList();
	}
	
	@GetMapping("/AdditionalInfo")
	public List<PropertyInfo> getPropertiesInfo() {
		return null;
	}
	
	private PropertyDTO mapToDTO(Property property) {
		var propertyDTO = new PropertyDTO();
		propertyDTO.setId(property.getId());
		propertyDTO.setAddress(property.getAddress());
		propertyDTO.setBathrooms(property.getBathrooms());
		propertyDTO.setBedrooms(property.getBedrooms());
		propertyDTO.setDimension(property.getDimension());
		propertyDTO.setGarage(property.getGarage());
		propertyDTO.setLatitude(property.getLatitude());
		propertyDTO.setLongitude(property.getLongitude());
		propertyDTO.setPrice(property.getPrice());
		propertyDTO.setPublisher(property.getPublisher());
		propertyDTO.setUrl(property.getUrl());
		return propertyDTO;
	}
}
