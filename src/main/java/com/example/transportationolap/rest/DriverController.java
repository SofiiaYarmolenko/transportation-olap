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

import com.example.transportationolap.domain.entities.Driver;
import com.example.transportationolap.services.DriverService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {
	
	private final DriverService driverService;

	@GetMapping("/{id}")
	public ResponseEntity<Driver> getDriverById(@PathVariable Long id) {
		Optional<Driver> driver = driverService.getDriverById(id);
		return driver.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
		Driver savedDriver = driverService.saveDriver(driver);
		return new ResponseEntity<>(savedDriver, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Driver> updateDriver(@PathVariable Long id, @RequestBody Driver driver) {
		Optional<Driver> existingDriver = driverService.getDriverById(id);
		if (existingDriver.isPresent()) {
			driver.setId(id);
			Driver updatedDriver = driverService.saveDriver(driver);
			return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
		Optional<Driver> existingDriver = driverService.getDriverById(id);
		if (existingDriver.isPresent()) {
			driverService.deleteDriver(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
