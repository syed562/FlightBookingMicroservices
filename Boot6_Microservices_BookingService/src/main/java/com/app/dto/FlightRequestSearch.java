package com.app.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FlightRequestSearch {

	  @NotBlank(message = "From place is required")
	    private String fromPlace;
	    
	    @NotBlank(message = "To place is required")
	    private String toPlace;
	    @Future(message = "Departure date must be valid one")
	    private LocalDate departDate;  
	    private String tripType; 
}
