package com.example.jappfinder.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jappfinder.domain.QueryDTO;
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
	public List<QueryDTO> getAll() {
		return queryService.getAll().stream().map(q -> mapToDOT(q)).toList();
	}
	
	@PostMapping
	public Long post(@RequestBody QueryFilter filter) {
		return queryService.add(filter);
	}
	
	private QueryDTO mapToDOT(Query query) {
		var queryDTO = new QueryDTO();
		queryDTO.setId(query.getId());
		queryDTO.setCity(query.getCity());
		queryDTO.setState(query.getState());
		queryDTO.setStatus(query.getStatus());
		return queryDTO;
	}

}
