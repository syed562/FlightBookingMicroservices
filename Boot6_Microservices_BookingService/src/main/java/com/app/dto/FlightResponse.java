package com.app.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FlightResponse {
    private Long flightId;
    private String flightNumber;
    private String airlineName;
    private String airlineLogo;
    private String fromPlace;
    private String toPlace;
    private LocalDateTime departTime;
    private LocalDateTime arrivalTime;
    private Double price;
    private Integer availableSeats;
    private String tripType;
}