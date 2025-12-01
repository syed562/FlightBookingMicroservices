package com.app.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.BookingRequest;
import com.app.dto.BookingResponse;
import com.app.dto.FlightRequestSearch;
import com.app.dto.FlightResponse;
import com.app.dto.PassengerDetails;
import com.app.feign.BookingInterface;
import com.app.models.Booking;
import com.app.models.Passenger;
import com.app.models.PnrGenerator;
import com.app.repo.BookingRepo;
import com.app.repo.PassengerRepo;

@Service
public class BookingService  implements BookingServiceInterface{
	  @Autowired
	    private BookingRepo bookingRepository;
	    
	  @Autowired
	    private PassengerRepo passengerRepository;
	  
	  @Autowired
	  BookingInterface bint;

	  public BookingResponse bookTicket(Long flightId, BookingRequest bookingRequest) {

		    FlightResponse flight = bint.getFlightById(flightId).getBody();

		    if (flight.getAvailableSeats() == null || flight.getAvailableSeats() < bookingRequest.getNumberOfSeats()) {
		        throw new RuntimeException("Not enough seats available");
		    }

		    if (bookingRequest.getPassengers().size() != bookingRequest.getNumberOfSeats()) {
		        throw new RuntimeException("Passenger count does not match number of seats");
		    }

		    Booking booking = new Booking();
		    booking.setPnr(PnrGenerator.generatePnr());
		    booking.setFlightId(flightId);
		    booking.setEmailId(bookingRequest.getEmailId());
		    booking.setUserName(bookingRequest.getUserName());
		    booking.setNumberOfSeats(bookingRequest.getNumberOfSeats());
		    booking.setBookingDate(LocalDateTime.now());
		    booking.setStatus("CONFIRMED");
		    booking.setTotalAmount(flight.getPrice() * bookingRequest.getNumberOfSeats());

		    booking = bookingRepository.save(booking);

		    List<Passenger> passengers = new ArrayList<>();
		    for (PassengerDetails pd : bookingRequest.getPassengers()) {
		        Passenger passenger = new Passenger();
		        passenger.setBooking(booking);
		        passenger.setPassenger_name(pd.getPassenger_name());
		        passenger.setGender(pd.getGender());
		        passenger.setAge(pd.getAge());
		        passenger.setMealPreference(pd.getMealPreference()); // fixed
		        passenger.setSeatNumber(pd.getSeatNo());
		        passengers.add(passenger);
		    }

		    passengerRepository.saveAll(passengers);
		    booking.setPassengers(passengers);
		    bookingRepository.save(booking); 

		    int updatedSeats = flight.getAvailableSeats() - bookingRequest.getNumberOfSeats();
		    bint.updateFlightSeats(flightId, updatedSeats);

		    return mapToBookingResponse(booking, flight);
		}
	  
	  public List<FlightResponse> searchFlights(FlightRequestSearch frs) {
		  List<FlightResponse> flights = bint.searchFlights(frs);
		  if (flights == null || flights.isEmpty()) {
		      throw new RuntimeException("No flights found");
		  }

	       return bint.searchFlights(frs);
	    }

	  public BookingResponse getTicketByPnr(String pnr) {
		    Booking booking = bookingRepository.findByPnr(pnr)
		        .orElseThrow(() -> new RuntimeException("Booking not found with PNR: " + pnr));

		    FlightResponse flight = bint.getFlightById(booking.getFlightId()).getBody();

		    return mapToBookingResponse(booking, flight);
		}


	  private BookingResponse mapToBookingResponse(Booking booking, FlightResponse flight) {
		    BookingResponse response = new BookingResponse();
		    
		    response.setPnr(booking.getPnr());
		    response.setUserName(booking.getUserName());
		    response.setEmailId(booking.getEmailId());
		    response.setNumberOfSeats(booking.getNumberOfSeats());
		    response.setBookingDate(booking.getBookingDate());
		    response.setStatus(booking.getStatus());
		    response.setTotalAmount(booking.getTotalAmount());
		    response.setFlightId(flight.getFlightId());
		    
		    
		    response.setAirlineName(flight.getAirlineName());
		    response.setFlightNumber(flight.getFlightNumber());
		    response.setFromPlace(flight.getFromPlace());
		    response.setToPlace(flight.getToPlace());
		    response.setDepartureTime(flight.getDepartTime());

		    
		    List<PassengerDetails> passengerDetailsList = booking.getPassengers().stream()
		        .map(p -> {
		            PassengerDetails pd = new PassengerDetails();
		            pd.setPassenger_name(p.getPassenger_name());
		            pd.setGender(p.getGender());
		            pd.setAge(p.getAge());
		            pd.setMealType(p.getMealPreference());
		            pd.setSeatNo(p.getSeatNumber());
		            return pd;
		        })
		        .collect(Collectors.toList());
		    response.setPassengers(passengerDetailsList);

		    return response;
		}

	  public String cancelBooking(String pnr) {
	        Booking booking = bookingRepository.findByPnr(pnr)
	            .orElseThrow(() -> new RuntimeException("Booking not found with PNR: " + pnr));
	        
	        FlightResponse flight = bint.getFlightById(booking.getFlightId()).getBody();

	       
	        if ("CANCELLED".equals(booking.getStatus())) {
	            throw new RuntimeException("Booking is already cancelled");
	        }
	        
	        
	        long hoursUntilDeparture = ChronoUnit.HOURS.between(
	                LocalDateTime.now(),
	                flight.getDepartTime()
	        );

	        if (hoursUntilDeparture < 24) {
	            throw new RuntimeException("Cannot cancel booking within 24 hours of departure");
	        }
	        
	        
	        booking.setStatus("CANCELLED");
	        bookingRepository.save(booking);
	        
	        int updatedSeats = flight.getAvailableSeats() + booking.getNumberOfSeats();
	        bint.updateFlightSeats(booking.getFlightId(), updatedSeats);

	        
	        return "Booking cancelled successfully. PNR: " + pnr;
	    }

}