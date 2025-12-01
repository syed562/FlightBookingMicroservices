package com.app.models;




import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "flights")
@Data

public class Flight {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long flightId;
    
    @Column(nullable = false)
  private String flightNumber;
    
    @ManyToOne
    @JoinColumn(name = "airline_id", nullable = false)
  private Airline airline;
    
    @Column(nullable = false)
  private String fromPlace;
    
    @Column(nullable = false)
  private String toPlace;
    
    @Column(nullable = false)
  private LocalDateTime departureTime;
    
    @Column(nullable = false)
  private LocalDateTime arrivalTime;
    
    @Column(nullable = false)
  private Double price;
    
    @Column(nullable = false)
 private Integer totalSeats;
    
    @Column(nullable = false)
 private Integer availableSeats;
private String tripType; 
}