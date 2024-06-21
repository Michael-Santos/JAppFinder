package com.example.jappfinder.services;

import java.util.List;
import java.util.Optional;

import com.example.jappfinder.domain.QueryFilter;
import com.example.jappfinder.repositories.models.Query;

public interface QueryService {
	Long add(QueryFilter queryFilter);
	List<Query> getAll();
	Optional<Query> getFirstNotStarted();
	Query update(Query query);
}
