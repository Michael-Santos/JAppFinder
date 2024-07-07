package com.example.jappfinder.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jappfinder.domain.PropertyDTO;
import com.example.jappfinder.domain.QueryDTO;
import com.example.jappfinder.domain.QueryFilter;
import com.example.jappfinder.services.PropertyService;
import com.example.jappfinder.services.QueryService;

@RestController
@RequestMapping("/Query")
public class QueryController {
	
	private final QueryService queryService;
	private final PropertyService propertyService;
	
	public QueryController(QueryService queryService, PropertyService propertyService) {
		this.queryService = queryService;
		this.propertyService = propertyService;
	}
	
	@GetMapping
	public ResponseEntity<List<QueryDTO>> getAll() {
		var result = queryService.getAll().stream().map(q -> q.mapToDTO()).toList();
		return new ResponseEntity<List<QueryDTO>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<PropertyDTO>> getProperties(@PathVariable Long id) {
		var query = queryService.getById(id);
		
		if (query.isEmpty()) {
			return new ResponseEntity<List<PropertyDTO>>(HttpStatus.NOT_FOUND);
		}
		
		var properties = propertyService.getByQueryId(id, 0, 50);
		var result = properties.stream().map(p -> p.mapToDTO()).collect(Collectors.toList());
		return new ResponseEntity<List<PropertyDTO>>(result, HttpStatus.OK);
	}
	
	@PostMapping
	public Long post(@RequestBody QueryFilter filter) {
		return queryService.add(filter);
	}
	
}
