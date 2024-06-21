package com.example.jappfinder.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jappfinder.domain.QueryFilter;
import com.example.jappfinder.repositories.models.Query;
import com.example.jappfinder.services.QueryService;

@RestController
@RequestMapping("/Query")
public class QueryController {
	
	private final QueryService queryService;
	
	public QueryController(QueryService queryService) {
		this.queryService = queryService;
	}
	
	@GetMapping
	public List<Query> getAll() {
		return queryService.getAll();
	}
	
	@PostMapping
	public Long post(@RequestBody QueryFilter filter) {
		return queryService.add(filter);
	}

}
