package com.example.jappfinder.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.jappfinder.repositories.models.Property;

public interface PropertyRepository extends CrudRepository<Property, Long> {
	List<Property> findByQueryId(Long queryId, Pageable pageable);
	long countByQueryId(Long queryId);
	List<Property> findAll();
}
