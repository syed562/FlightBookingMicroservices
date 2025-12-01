package com.app.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.models.Passenger;

@Repository
public interface PassengerRepo extends CrudRepository<Passenger,Long> {

	
}
