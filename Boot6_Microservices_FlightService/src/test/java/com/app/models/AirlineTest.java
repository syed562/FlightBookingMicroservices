package com.app.models;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AirlineTest {

    @Test
    void testAirlineModel() {
        Airline airline = new Airline();
        airline.setId(1L);
        airline.setAirlineName("Air India");
        airline.setAirlineLogo("airindia.png");

        assertEquals(1L, airline.getId());
        assertEquals("Air India", airline.getAirlineName());
        assertEquals("airindia.png", airline.getAirlineLogo());

        // Lombok @Data → equals & hashCode
        Airline airline2 = new Airline();
        airline2.setId(1L);
        airline2.setAirlineName("Air India");
        airline2.setAirlineLogo("airindia.png");

        assertEquals(airline, airline2);
        assertEquals(airline.hashCode(), airline2.hashCode());
        assertTrue(airline.toString().contains("Air India"));
    }
}
