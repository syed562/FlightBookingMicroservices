package com.app.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

class DtoTest {

    @Test
    void testPassengerDetailsDto() {
        PassengerDetails pd = new PassengerDetails();
        pd.setPassenger_name("John");
        pd.setGender("Male");
        pd.setAge(30);
        pd.setSeatNo("12A");
        pd.setMealType("Veg");

        assertEquals("John", pd.getPassenger_name());
        assertEquals("Male", pd.getGender());
        assertEquals(30, pd.getAge());
        assertEquals("12A", pd.getSeatNo());
        assertEquals("Veg", pd.getMealType());
    }

    @Test
    void testBookingRequestDto() {
        PassengerDetails pd = new PassengerDetails();
        BookingRequest req = new BookingRequest();

        req.setUserName("Syed");
        req.setEmailId("test@gmail.com");
        req.setNumberOfSeats(2);
        req.setPassengers(List.of(pd));

        assertEquals("Syed", req.getUserName());
        assertEquals("test@gmail.com", req.getEmailId());
        assertEquals(2, req.getNumberOfSeats());
        assertEquals(1, req.getPassengers().size());
    }

    @Test
    void testFlightRequestSearchDto() {
        FlightRequestSearch search = new FlightRequestSearch();
        search.setFromPlace("HYD");
        search.setToPlace("DEL");
        search.setTripType("ONE-WAY");

        assertEquals("HYD", search.getFromPlace());
        assertEquals("DEL", search.getToPlace());
        assertEquals("ONE-WAY", search.getTripType());
    }

    @Test
    void testFlightResponseDto() {
        FlightResponse res = new FlightResponse();
        res.setFlightId(10L);
        res.setFlightNumber("AI505");
        res.setFromPlace("BLR");
        res.setToPlace("MUM");
        res.setPrice(4500.0);
        res.setAirlineName("Air India");
        res.setAvailableSeats(100);
        res.setDepartTime(LocalDateTime.now());

        assertEquals(10L, res.getFlightId());
        assertEquals("AI505", res.getFlightNumber());
        assertEquals("BLR", res.getFromPlace());
        assertEquals("MUM", res.getToPlace());
        assertEquals(4500.0, res.getPrice());
        assertEquals("Air India", res.getAirlineName());
        assertEquals(100, res.getAvailableSeats());
        assertNotNull(res.getDepartTime());
    }

    @Test
    void testBookingResponseDto() {
        BookingResponse br = new BookingResponse();
        br.setPnr("PNR001");
        br.setUserName("Syed");
        br.setFlightNumber("AI909");
        br.setFromPlace("HYD");
        br.setToPlace("DEL");
        br.setStatus("CONFIRMED");

        assertEquals("PNR001", br.getPnr());
        assertEquals("Syed", br.getUserName());
        assertEquals("AI909", br.getFlightNumber());
        assertEquals("HYD", br.getFromPlace());
        assertEquals("DEL", br.getToPlace());
        assertEquals("CONFIRMED", br.getStatus());
    }
}
