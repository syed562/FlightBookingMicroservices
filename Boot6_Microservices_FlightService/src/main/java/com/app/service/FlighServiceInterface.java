package com.app.service;

import java.util.List;

import com.app.dto.FlightRequestSearch;
import com.app.dto.FlightResponse;
import com.app.dto.InventoryRequest;

public interface FlighServiceInterface {

	 public FlightResponse addFlightInventory(InventoryRequest request);
	 public List<FlightResponse> searchFlights(FlightRequestSearch searchRequest);
	 public FlightResponse getFlightById(Long id);
}
