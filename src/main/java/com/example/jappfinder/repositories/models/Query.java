package com.example.jappfinder.repositories.models;

import java.util.ArrayList;
import java.util.List;

import com.example.jappfinder.domain.QueryDTO;
import com.example.jappfinder.driver.SearchFilter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Query {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Status status;
	private String city;
	private String state;
	private int minPrice;
	private int maxPrice;
	private int minDimension;
	private int maxDimension;
	
	public Query(Long id) {
		this.id = id;
	}
	
	@OneToMany(mappedBy = "query",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
	private List<Property> properties = new ArrayList<Property>();
	
	public QueryDTO mapToDTO() {
		var queryDTO = new QueryDTO();
		queryDTO.setId(id);
		queryDTO.setCity(city);
		queryDTO.setState(state);
		queryDTO.setStatus(status);
		queryDTO.setMinPrice(minPrice);
		queryDTO.setMaxPrice(maxPrice);
		queryDTO.setMinDimension(minDimension);
		queryDTO.setMaxDimension(maxDimension);
		return queryDTO;
	}
	
	public SearchFilter mapToSearchFilter() {
		var filter = new SearchFilter();
		filter.setState(state);
		filter.setCity(city);
		filter.setMinPrice(minPrice);
		filter.setMaxPrice(maxPrice);
		filter.setMinDimension(minDimension);
		filter.setMaxDimension(maxDimension);
		return filter;
	}
}
