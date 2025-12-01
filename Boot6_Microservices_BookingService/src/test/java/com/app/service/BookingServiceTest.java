package com.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.app.dto.BookingRequest;
import com.app.dto.BookingResponse;
import com.app.dto.FlightRequestSearch;
import com.app.dto.FlightResponse;
import com.app.dto.PassengerDetails;
import com.app.feign.BookingInterface;
import com.app.models.Booking;
import com.app.repo.BookingRepo;
import com.app.repo.PassengerRepo;

class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepo bookingRepo;

    @Mock
    private PassengerRepo passengerRepo;

    @Mock
    private BookingInterface bint;

    private FlightResponse flightResponse;
    private BookingRequest bookingRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        flightResponse = new FlightResponse();
        flightResponse.setFlightId(1L);
        flightResponse.setFlightNumber("AI101");
        flightResponse.setFromPlace("HYD");
        flightResponse.setToPlace("DEL");
        flightResponse.setPrice(5000.0);
        flightResponse.setAirlineName("Air India");
        flightResponse.setAvailableSeats(50);
        flightResponse.setDepartTime(LocalDateTime.now().plusDays(2));

        PassengerDetails pd = new PassengerDetails();
        pd.setPassenger_name("John");
        pd.setGender("Male");
        pd.setAge(30);
        pd.setSeatNo("12A");
        pd.setMealType("Veg");

        bookingRequest = new BookingRequest();
        bookingRequest.setUserName("TestUser");
        bookingRequest.setEmailId("test@gmail.com");
        bookingRequest.setNumberOfSeats(1);
        bookingRequest.setPassengers(List.of(pd));
    }

    @Test
    void testBookTicket_Success() {
        Booking savedBooking = new Booking();
        savedBooking.setBookingId(1L);
        savedBooking.setPnr("PNR123");
        savedBooking.setFlightId(1L);
        savedBooking.setNumberOfSeats(1);

        when(bint.getFlightById(1L)).thenReturn(ResponseEntity.ok(flightResponse));
        when(bookingRepo.save(any(Booking.class))).thenReturn(savedBooking);

        BookingResponse response = bookingService.bookTicket(1L, bookingRequest);

        assertNotNull(response);
        assertEquals("PNR123", response.getPnr());
        verify(passengerRepo, times(1)).saveAll(anyList());
        verify(bint, times(1)).updateFlightSeats(eq(1L), anyInt());
    }

    @Test
    void testSearchFlights() {
        List<FlightResponse> list = List.of(flightResponse);
        when(bint.searchFlights(any())).thenReturn(list);

        FlightRequestSearch search = new FlightRequestSearch();
        search.setFromPlace("HYD");
        search.setToPlace("DEL");

        List<FlightResponse> result = bookingService.searchFlights(search);

        assertEquals(1, result.size());
        verify(bint, times(1)).searchFlights(any());
    }

    @Test
    void testGetTicketByPnr() {
        Booking booking = new Booking();
        booking.setFlightId(1L);
        booking.setPnr("PNR123");
        booking.setPassengers(new ArrayList<>());

        when(bookingRepo.findByPnr("PNR123")).thenReturn(Optional.of(booking));
        when(bint.getFlightById(1L)).thenReturn(ResponseEntity.ok(flightResponse));

        BookingResponse response = bookingService.getTicketByPnr("PNR123");

        assertEquals("PNR123", response.getPnr());
        verify(bint, times(1)).getFlightById(1L);
    }

    @Test
    void testCancelBookingSuccess() {
        Booking booking = new Booking();
        booking.setFlightId(1L);
        booking.setNumberOfSeats(1);
        booking.setPnr("PNR123");
        booking.setStatus("CONFIRMED");

        when(bookingRepo.findByPnr("PNR123")).thenReturn(Optional.of(booking));
        when(bint.getFlightById(1L)).thenReturn(ResponseEntity.ok(flightResponse));

        String response = bookingService.cancelBooking("PNR123");

        assertTrue(response.contains("successfully"));
        verify(bint, times(1)).updateFlightSeats(eq(1L), anyInt());
        verify(bookingRepo, times(1)).save(any());
    }
    
    @Test
    void testBookTicket_NoSeatsAvailable() {
        flightResponse.setAvailableSeats(0);
        when(bint.getFlightById(1L)).thenReturn(ResponseEntity.ok(flightResponse));
        assertThrows(RuntimeException.class,
                () -> bookingService.bookTicket(1L, bookingRequest));
    }

    @Test
    void testCancelBooking_AlreadyCancelled() {
        Booking booking = new Booking();
        booking.setFlightId(1L);
        booking.setStatus("CANCELLED");
        when(bookingRepo.findByPnr("PNR123")).thenReturn(Optional.of(booking));
        assertThrows(RuntimeException.class,
                () -> bookingService.cancelBooking("PNR123"));
    }

}
