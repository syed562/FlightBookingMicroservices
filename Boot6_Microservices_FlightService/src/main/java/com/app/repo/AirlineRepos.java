package com.app.repo;

import org.springframework.data.repository.CrudRepository;

import com.app.models.Airline;

public interface AirlineRepos  extends  CrudRepository<Airline,Long>{

}
