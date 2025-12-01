package com.app.controller;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.FlightRequestSearch;
import com.app.dto.FlightResponse;
import com.app.dto.InventoryRequest;
import com.app.models.Flight;
import com.app.repo.FlightRepo;
import com.app.service.FlightService;

import jakarta.validation.Valid;




@RestController
@RequestMapping("flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepo frepo;
 
    @PostMapping("airline/inventory/add")
    public ResponseEntity<?> addInventory(@Valid @RequestBody InventoryRequest request) {
        try {
            FlightResponse response = flightService.addFlightInventory(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response); // 201
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()); // 400
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong"); // 500
        }
    }
    @GetMapping("/")
    public ResponseEntity<List<Flight>>getAllFlights(){
    	Iterable<Flight>list=frepo.findAll();
    	List<Flight>list2=new ArrayList<>();
    	for(Flight f:list) {
    		list2.add(f);
    	}
    	return new ResponseEntity<>(list2,HttpStatus.OK);
    }

    @PostMapping("search")
    public ResponseEntity<?> searchFlights(@Valid @RequestBody FlightRequestSearch searchRequest) {
        try {
            List<FlightResponse> flights = flightService.searchFlights(searchRequest);

            if (flights.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204
            }

            return ResponseEntity.ok(flights); // 200
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error searching flights"); // 500
        }
    }
    
    @GetMapping("get/{id}")
   public ResponseEntity<FlightResponse>getFlightById(@PathVariable Long id){
    	return new ResponseEntity<>(flightService.getFlightById(id),HttpStatus.OK);
    }

    @PutMapping("updateseats/{id}/{seats}")
    public void updateFlightSeats(@PathVariable Long id,@PathVariable int seats) {
    	
    	Flight f=frepo.findById(id).get();
    	f.setAvailableSeats(seats);
    	frepo.save(f);
    }


}
