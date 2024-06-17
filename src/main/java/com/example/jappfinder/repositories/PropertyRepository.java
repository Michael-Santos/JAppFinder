package com.example.jappfinder.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.jappfinder.repositories.models.Property;

public interface PropertyRepository extends CrudRepository<Property, Long> {
	
}
