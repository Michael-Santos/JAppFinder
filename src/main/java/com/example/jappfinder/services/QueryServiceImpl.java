package com.example.jappfinder.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jappfinder.domain.QueryFilter;
import com.example.jappfinder.repositories.QueryRepository;
import com.example.jappfinder.repositories.models.Query;
import com.example.jappfinder.repositories.models.Status;

@Service
public class QueryServiceImpl implements QueryService {
	
	private final QueryRepository queryRepository;
	
	public QueryServiceImpl(QueryRepository queryRepository) {
		this.queryRepository = queryRepository;
	}

	@Override
	public Long createQuery(QueryFilter queryFilter) {
		var query = new Query();
		query.setStatus(Status.NOT_STARTED);
		query.setCity(queryFilter.getCity());
		query.setState(queryFilter.getState());
		var result = queryRepository.save(query);
		return result.getId();
	}
	
	@Override
	public List<Query> getAll() {
		var queries = new ArrayList<Query>();
		queryRepository.findAll().forEach(queries::add);
		return queries;
	}
	
}