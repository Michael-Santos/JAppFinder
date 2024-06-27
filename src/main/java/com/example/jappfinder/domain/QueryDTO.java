package com.example.jappfinder.domain;

import com.example.jappfinder.repositories.models.Status;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QueryDTO {
	private Long id;
	private Status status;
	private String city;
	private String state;
}
