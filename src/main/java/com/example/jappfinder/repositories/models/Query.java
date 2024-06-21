package com.example.jappfinder.repositories.models;

import java.util.ArrayList;
import java.util.List;
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
	
	public Query(Long id) {
		this.id = id;
	}
	
	@OneToMany(mappedBy = "query",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
	private List<Property> properties = new ArrayList<Property>();
}
