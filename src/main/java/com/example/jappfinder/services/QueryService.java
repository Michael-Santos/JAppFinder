package com.example.jappfinder.services;

import java.util.List;

import com.example.jappfinder.domain.QueryFilter;
import com.example.jappfinder.repositories.models.Query;

public interface QueryService {
	Long createQuery(QueryFilter queryFilter);
	List<Query> getAll();
}
