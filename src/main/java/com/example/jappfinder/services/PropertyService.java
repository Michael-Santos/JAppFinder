package com.example.jappfinder.services;

import java.util.List;

import com.example.jappfinder.repositories.models.Property;

public interface PropertyService {
	void update(Property property);
	void update(List<Property> properties);
	List<Property> getByQueryId(Long queryId, int pageNumber, int pageSize);
	long countByQueryId(Long queryId); 
	List<Property> getAll(); 
}
