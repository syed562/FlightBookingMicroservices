package com.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class FlightTest {

    @Test
    void testFlightModel() {
        Airline airline = new Airline();
        airline.setId(10L);
        airline.setAirlineName("Vistara");

        LocalDateTime depart = LocalDateTime.now().plusDays(1);
        LocalDateTime arrive = depart.plusHours(2);

        Flight flight = new Flight();
        flight.setFlightId(101L);
        flight.setFlightNumber("VT101");
        flight.setAirline(airline);
        flight.setFromPlace("HYD");
        flight.setToPlace("DEL");
        flight.setDepartureTime(depart);
        flight.setArrivalTime(arrive);
        flight.setPrice(6500.00);
        flight.setTotalSeats(180);
        flight.setAvailableSeats(120);
        flight.setTripType("ONEWAY");

        assertEquals(101L, flight.getFlightId());
        assertEquals("VT101", flight.getFlightNumber());
        assertEquals(airline, flight.getAirline());
        assertEquals("HYD", flight.getFromPlace());
        assertEquals("DEL", flight.getToPlace());
        assertEquals(depart, flight.getDepartureTime());
        assertEquals(arrive, flight.getArrivalTime());
        assertEquals(6500.00, flight.getPrice());
        assertEquals(180, flight.getTotalSeats());
        assertEquals(120, flight.getAvailableSeats());
        assertEquals("ONEWAY", flight.getTripType());

        // Lombok methods
        Flight flight2 = new Flight();
        flight2.setFlightId(101L);
        flight2.setFlightNumber("VT101");
        flight2.setAirline(airline);
        flight2.setFromPlace("HYD");
        flight2.setToPlace("DEL");
        flight2.setDepartureTime(depart);
        flight2.setArrivalTime(arrive);
        flight2.setPrice(6500.00);
        flight2.setTotalSeats(180);
        flight2.setAvailableSeats(120);
        flight2.setTripType("ONEWAY");

        assertEquals(flight, flight2);
        assertEquals(flight.hashCode(), flight2.hashCode());
        assertTrue(flight.toString().contains("VT101"));
    }
}
