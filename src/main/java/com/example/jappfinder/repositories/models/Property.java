package com.example.jappfinder.repositories.models;

import com.example.jappfinder.domain.PropertyDTO;

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
	
	public PropertyDTO mapToDTO() {
		var propertyDTO = new PropertyDTO();
		propertyDTO.setId(id);
		propertyDTO.setAddress(address);
		propertyDTO.setBathrooms(bathrooms);
		propertyDTO.setBedrooms(bedrooms);
		propertyDTO.setDimension(dimension);
		propertyDTO.setGarage(garage);
		propertyDTO.setLatitude(latitude);
		propertyDTO.setLongitude(longitude);
		propertyDTO.setPrice(price);
		propertyDTO.setPublisher(publisher);
		propertyDTO.setUrl(url);
		return propertyDTO;
	}
	
}
