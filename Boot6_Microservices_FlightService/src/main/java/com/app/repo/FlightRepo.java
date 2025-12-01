package com.app.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.models.Flight;

@Repository
public interface FlightRepo extends CrudRepository<Flight, Long> {

    @Query("SELECT f FROM Flight f " +
           "WHERE f.fromPlace = :fromPlace " +
           "AND f.toPlace = :toPlace " +
           "AND DATE(f.departureTime) = DATE(:searchDate) " +
           "AND f.availableSeats > 0")
    List<Flight> searchFlights(
            @Param("fromPlace") String fromPlace,
            @Param("toPlace") String toPlace,
            @Param("searchDate") LocalDateTime searchDate
    );
}
