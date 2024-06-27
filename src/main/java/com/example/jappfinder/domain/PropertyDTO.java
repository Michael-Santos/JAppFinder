package com.example.jappfinder.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PropertyDTO {	
	private Long id;
	private String url;
	private String dimension;
	private String bathrooms;
	private String bedrooms;
	private String garage;
	private String price;
	private String address;
	private String latitude;
	private String longitude;
	private String publisher;
}
