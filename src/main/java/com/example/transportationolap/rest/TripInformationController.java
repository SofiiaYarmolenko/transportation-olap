package com.example.transportationolap.rest;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.transportationolap.domain.entities.TripInformation;
import com.example.transportationolap.services.TripInformationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/trip-informations")
@RequiredArgsConstructor
public class TripInformationController {

	private final TripInformationService tripInformationService;

	@GetMapping("/{id}")
	public ResponseEntity<TripInformation> getTripInformationById(@PathVariable Integer id) {
		Optional<TripInformation> tripInformation = tripInformationService.getTripInformationById(id);
		return tripInformation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<TripInformation> createTripInformation(@RequestBody TripInformation tripInformation) {
		TripInformation savedTripInformation = tripInformationService.saveTripInformation(tripInformation);
		return new ResponseEntity<>(savedTripInformation, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TripInformation> updateTripInformation(@PathVariable Integer id, @RequestBody TripInformation tripInformation) {
		Optional<TripInformation> existingTripInformation = tripInformationService.getTripInformationById(id);
		if (existingTripInformation.isPresent()) {
			tripInformation.setId(id);
			TripInformation updatedTripInformation = tripInformationService.saveTripInformation(tripInformation);
			return new ResponseEntity<>(updatedTripInformation, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTripInformation(@PathVariable Integer id) {
		Optional<TripInformation> existingTripInformation = tripInformationService.getTripInformationById(id);
		if (existingTripInformation.isPresent()) {
			tripInformationService.deleteTripInformation(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}