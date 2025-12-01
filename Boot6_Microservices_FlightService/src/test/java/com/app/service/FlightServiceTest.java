package com.app.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.app.dto.FlightRequestSearch;
import com.app.dto.FlightResponse;
import com.app.dto.InventoryRequest;
import com.app.models.Airline;
import com.app.models.Flight;
import com.app.repo.AirlineRepos;
import com.app.repo.FlightRepo;

class FlightServiceTest {

    @InjectMocks
    private FlightService flightService;

    @Mock
    private FlightRepo flightRepository;

    @Mock
    private AirlineRepos airlineRepository;

    private Airline airline;
    private InventoryRequest inventoryRequest;
    private Flight flight;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        airline = new Airline();
        airline.setId(1L);
        airline.setAirlineName("Air India");
        airline.setAirlineLogo("logo.png");

        inventoryRequest = new InventoryRequest();
        inventoryRequest.setAirlineId(1L);
        inventoryRequest.setFlightNumber("AI123");
        inventoryRequest.setFromPlace("HYD");
        inventoryRequest.setToPlace("DEL");
        inventoryRequest.setDepartureTime(LocalDateTime.now().plusDays(2));
        inventoryRequest.setArrivalTime(LocalDateTime.now().plusDays(2).plusHours(2));
        inventoryRequest.setPrice(6000.0);
        inventoryRequest.setTotalSeats(100);
        inventoryRequest.setTripType("ONE-WAY");

        flight = new Flight();
        flight.setFlightId(10L);
        flight.setFlightNumber("AI123");
        flight.setAirline(airline);
        flight.setFromPlace("HYD");
        flight.setToPlace("DEL");
        flight.setDepartureTime(inventoryRequest.getDepartureTime());
        flight.setArrivalTime(inventoryRequest.getArrivalTime());
        flight.setPrice(6000.0);
        flight.setTotalSeats(100);
        flight.setAvailableSeats(100);
        flight.setTripType("ONE-WAY");
    }

    @Test
    void testAddFlightInventory_Success() {
        when(airlineRepository.findById(1L)).thenReturn(Optional.of(airline));
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);

        FlightResponse response = flightService.addFlightInventory(inventoryRequest);

        assertNotNull(response);
        assertEquals("AI123", response.getFlightNumber());
        verify(flightRepository, times(1)).save(any(Flight.class));
    }

    @Test
    void testAddFlightInventory_AirlineNotFound() {
        when(airlineRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> flightService.addFlightInventory(inventoryRequest));

        assertEquals("Airline not found", ex.getMessage());
    }

    @Test
    void testSearchFlights_Success() {
        when(flightRepository.searchFlights(eq("HYD"), eq("DEL"), any(LocalDateTime.class)))
                .thenReturn(List.of(flight));

        FlightRequestSearch search = new FlightRequestSearch();
        search.setFromPlace("HYD");
        search.setToPlace("DEL");
        search.setDepartDate(LocalDate.now());

        List<FlightResponse> responses = flightService.searchFlights(search);

        assertEquals(1, responses.size());
        assertEquals("AI123", responses.get(0).getFlightNumber());
        verify(flightRepository, times(1)).searchFlights(eq("HYD"), eq("DEL"), any(LocalDateTime.class));
    }

    @Test
    void testGetFlightById() {
        when(flightRepository.findById(10L)).thenReturn(Optional.of(flight));

        FlightResponse response = flightService.getFlightById(10L);

        assertEquals("AI123", response.getFlightNumber());
        assertEquals("Air India", response.getAirlineName());
        verify(flightRepository, times(1)).findById(10L);
    }
}
