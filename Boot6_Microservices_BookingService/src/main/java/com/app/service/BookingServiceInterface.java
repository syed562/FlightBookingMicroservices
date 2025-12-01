package com.app.service;

import java.util.List;

import com.app.dto.BookingRequest;
import com.app.dto.BookingResponse;
import com.app.dto.FlightRequestSearch;
import com.app.dto.FlightResponse;
import com.app.models.Booking;

public interface BookingServiceInterface {
	 public BookingResponse bookTicket(Long flightId, BookingRequest bookingRequest);
	 public List<FlightResponse> searchFlights(FlightRequestSearch frs);
	 public BookingResponse getTicketByPnr(String pnr);
	 public String cancelBooking(String pnr);
}
