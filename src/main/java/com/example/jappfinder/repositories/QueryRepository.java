package com.example.jappfinder.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.jappfinder.repositories.models.Query;
import com.example.jappfinder.repositories.models.Status;

public interface QueryRepository extends CrudRepository<Query, Long>{
	Optional<Query> findTopByStatus(Status status);
}
