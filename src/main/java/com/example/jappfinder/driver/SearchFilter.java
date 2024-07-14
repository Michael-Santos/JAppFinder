package com.example.jappfinder.driver;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SearchFilter {
	private String state = "sp";
	private String city = "sorocaba"; 
	private int minPrice = 0;
	private int maxPrice = Integer.MAX_VALUE;
	private int minDimension = 0;
	private int maxDimension = Integer.MAX_VALUE;
	private int maxPages = 1;
	private OperationType operationType = OperationType.SELL;
	
	public SearchFilter(String state, String city) {
		this.state = state;
		this.city = city;
	}
}
