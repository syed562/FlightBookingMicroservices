package com.app.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookingRequest {
	 @NotBlank(message = "Name is mandatory")
	    private String userName; 
	    @NotBlank(message = "Email is mandatory")
	    @Email(message = "Invalid email format")
	    private String emailId;
	    
	    @NotNull(message = "Number of seats is required")
	    @Min(value = 1, message = "Invalid Seat Number")
	    private Integer numberOfSeats;
	    
	    @NotEmpty(message = "Passenger details are required")
	    private List<PassengerDetails> passengers;
}
