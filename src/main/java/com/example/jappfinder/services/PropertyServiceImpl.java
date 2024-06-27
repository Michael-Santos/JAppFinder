package com.example.jappfinder.services;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.jappfinder.repositories.PropertyRepository;
import com.example.jappfinder.repositories.models.Property;

@Service
public class PropertyServiceImpl implements PropertyService {

	private final PropertyRepository propertyRepository;
	
	public PropertyServiceImpl(PropertyRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}
	
	@Override
	public void update(Property property) {
		propertyRepository.save(property);		
	}

	@Override
	public void update(List<Property> properties) {
		propertyRepository.saveAll(properties);
	}

	@Override
	public List<Property> getByQueryId(Long queryId, int pageNumber, int pageSize) {
		var pageable = PageRequest.of(pageNumber, pageSize);
		return propertyRepository.findByQueryId(queryId, pageable);
	}

	@Override
	public long countByQueryId(Long queryId) {
		return propertyRepository.countByQueryId(queryId);
	}
	
}
