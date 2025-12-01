package com.app.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.BookingRequest;
import com.app.dto.BookingResponse;
import com.app.dto.FlightRequestSearch;
import com.app.dto.FlightResponse;
import com.app.models.Booking;
import com.app.repo.BookingRepo;
import com.app.service.BookingService;

import jakarta.validation.Valid;
import jakarta.ws.rs.Path;

@RestController
@RequestMapping("booking")
public class BookingController {
	
	@Autowired
	BookingService bser;
	
	@Autowired
	BookingRepo bookingRepo;
	
	//1.to get flights by giving details from place, to place and departure date 
	
	@PostMapping("getFlights")
	public List<FlightResponse> getFlightsByDetails(@RequestBody FlightRequestSearch frs){
		return bser.searchFlights(frs);
	}
	
	
	//2.to book tickets
	 @PostMapping("book/{flightid}")
	    public ResponseEntity<?> bookTicket(
	            @PathVariable("flightid") Long flightId,
	            @Valid @RequestBody BookingRequest bookingRequest) {
	        try {
	            BookingResponse response = bser.bookTicket(flightId, bookingRequest);
	            return ResponseEntity.status(HttpStatus.CREATED).body(response); // 201
	        } catch (IllegalStateException ex) {
	        	 ex.printStackTrace();
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage()); // 409
	        } catch (NoSuchElementException ex) {
	        	 ex.printStackTrace();
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()); // 404
	        } catch (Exception ex) {
	        	 ex.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Booking failed"); // 500
	        }
	    }
	 
	 //3.to get Booking ticket by pnr
	 @GetMapping("getByPnr/{pnr}")
	 public ResponseEntity<BookingResponse>getTicketByPnr(@PathVariable  String pnr){
		 BookingResponse bb=bser.getTicketByPnr(pnr);
		 return new ResponseEntity<>(bb,HttpStatus.OK);
	 }


	 //4.to cancel ticket
	 @DeleteMapping("delete/{pnr}")
	 public String cancelTicketByPnr(@PathVariable String pnr){
		 return  bser.cancelBooking(pnr);
	 }
	   
}
