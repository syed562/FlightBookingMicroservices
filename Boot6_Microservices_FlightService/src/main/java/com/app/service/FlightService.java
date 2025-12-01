package com.app.service;

import java.time.LocalDateTime;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.FlightRequestSearch;
import com.app.dto.FlightResponse;
import com.app.dto.InventoryRequest;
import com.app.models.Airline;
import com.app.models.Flight;
import com.app.repo.AirlineRepos;
import com.app.repo.FlightRepo;

@Service
public class FlightService implements FlighServiceInterface {
    
    @Autowired
    private FlightRepo flightRepository;
    
  
    @Autowired
    private AirlineRepos airlineRepository;
    
 
    @Transactional
    public FlightResponse addFlightInventory(InventoryRequest request) {
       
        Airline airline = airlineRepository.findById(request.getAirlineId())
            .orElseThrow(() -> new RuntimeException("Airline not found"));
        
        
        Flight flight = new Flight();
        flight.setFlightNumber(request.getFlightNumber());
        flight.setAirline(airline);
        flight.setFromPlace(request.getFromPlace());
        flight.setToPlace(request.getToPlace());
        flight.setDepartureTime(request.getDepartureTime());
        flight.setArrivalTime(request.getArrivalTime());
        flight.setPrice(request.getPrice());
        flight.setTotalSeats(request.getTotalSeats());
        flight.setAvailableSeats(request.getTotalSeats());
        flight.setTripType(request.getTripType());
        
        flight = flightRepository.save(flight);
        
        return mapToFlightResponse(flight);
    }
    
    public List<FlightResponse> searchFlights(FlightRequestSearch searchRequest) {
        LocalDateTime searchDate = searchRequest.getDepartDate().atStartOfDay();
        
        List<Flight> flights = flightRepository.searchFlights(
            searchRequest.getFromPlace(),
            searchRequest.getToPlace(),
            searchDate
        );
        
        return flights.stream()
            .map(this::mapToFlightResponse)
            .collect(Collectors.toList());
    }
    
 
    public FlightResponse getFlightById(Long id) {
    	Flight flight=flightRepository.findById(id).get();
    	  FlightResponse response = new FlightResponse();
          response.setFlightId(flight.getFlightId());
          response.setFlightNumber(flight.getFlightNumber());
          response.setAirlineName(flight.getAirline().getAirlineName());
          response.setAirlineLogo(flight.getAirline().getAirlineLogo());
          response.setFromPlace(flight.getFromPlace());
          response.setToPlace(flight.getToPlace());
          response.setDepartTime(flight.getDepartureTime());
          response.setArrivalTime(flight.getArrivalTime());
          response.setPrice(flight.getPrice());
          response.setAvailableSeats(flight.getAvailableSeats());
          response.setTripType(flight.getTripType());
          return response;
    	
    }
    
    
    private FlightResponse mapToFlightResponse(Flight flight) {
        FlightResponse response = new FlightResponse();
        response.setFlightId(flight.getFlightId());
        response.setFlightNumber(flight.getFlightNumber());
        response.setAirlineName(flight.getAirline().getAirlineName());
        response.setAirlineLogo(flight.getAirline().getAirlineLogo());
        response.setFromPlace(flight.getFromPlace());
        response.setToPlace(flight.getToPlace());
        response.setDepartTime(flight.getDepartureTime());
        response.setArrivalTime(flight.getArrivalTime());
        response.setPrice(flight.getPrice());
        response.setAvailableSeats(flight.getAvailableSeats());
        response.setTripType(flight.getTripType());
        return response;
    }
    
   
}