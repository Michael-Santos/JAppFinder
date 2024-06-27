package com.example.jappfinder.repositories.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Property {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn
	private Query query;
	
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
