package com.example.transportationolap.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.transportationolap.domain.repositories.olap.TransportationFactsRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TransportationFactsController {

	private final TransportationFactsRepository transportationFactsRepository;
	
	@GetMapping("/sortedVehiclesWithSuccessfullyTripsNumber")
	public ResponseEntity<List<Object[]>> getSortedListOfVehicleTypesWithHighestSuccessfulTrips() {
		return ResponseEntity.ok(transportationFactsRepository.sortedListOfVehicleTypesWithHighestSuccessfulTrips());
	}

	@GetMapping("/sortedListOfPopularFinishCities")
	public ResponseEntity<List<Object[]>> getSortedListOfPopularFinishCities() {
		return ResponseEntity.ok(transportationFactsRepository.sortedListOfPopularFinishCities());
	}

	@GetMapping("/sortedListOfDriverNamesByMaxDistanceFinished")
	public ResponseEntity<List<Object[]>> getSortedListOfDriverNamesByMaxDistanceFinished() {
		return ResponseEntity.ok(transportationFactsRepository.sortedListOfDriverNamesByMaxDistanceFinished());
	}
}
