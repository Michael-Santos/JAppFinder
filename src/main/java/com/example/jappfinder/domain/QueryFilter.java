package com.example.jappfinder.domain;

import com.example.jappfinder.repositories.models.Query;
import com.example.jappfinder.repositories.models.Status;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QueryFilter {
	private String city;
	private String state;
	private int minPrice = 0;
	private int maxPrice = Integer.MAX_VALUE;
	private int minDimension = 0;
	private int maxDimension = Integer.MAX_VALUE;
	
	public Query mapToQuery() {
		var query = new Query();
		query.setStatus(Status.NOT_STARTED);
		query.setCity(city);
		query.setState(state);
		query.setMinPrice(minPrice);
		query.setMaxPrice(maxPrice);
		query.setMinDimension(minDimension);
		query.setMaxDimension(maxDimension);
		return query;
	}
}
