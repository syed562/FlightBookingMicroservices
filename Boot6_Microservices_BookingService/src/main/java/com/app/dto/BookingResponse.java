package com.app.dto;


	import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

	@Data
	public class BookingResponse {
	    private String pnr;
	   
	
	    private String userName;
	    private String emailId;
	    private Integer numberOfSeats;
	    private List<PassengerDetails> passengers;
	    private LocalDateTime bookingDate;
	    private String status;
	    private Double totalAmount;
	    
	    private Long flightId;
	    private String flightNumber;
	    private String airlineName;
	    private String fromPlace;
	    private String toPlace;
	    private LocalDateTime departureTime;

	}


