package com.app.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.dto.FlightRequestSearch;
import com.app.dto.FlightResponse;

import jakarta.validation.Valid;

@FeignClient("FLIGHTSERVICE")
public interface BookingInterface {
    @GetMapping("flight/get/{id}")
   public ResponseEntity<FlightResponse>getFlightById(@PathVariable Long id);
   
    @PutMapping("flight/updateseats/{id}/{seats}")
    public void updateFlightSeats(@PathVariable Long id,@PathVariable int seats);
    
    @PostMapping("flight/search")
    public List<FlightResponse> searchFlights(@Valid @RequestBody FlightRequestSearch searchRequest);

}
