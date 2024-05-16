package com.example.transportationolap.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.transportationolap.domain.entities.Driver;
import com.example.transportationolap.domain.repositories.DriverRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverService {

	private final DriverRepository driverRepository;

	public Optional<Driver> getDriverById(Long id) {
		return driverRepository.findById(id);
	}

	public Driver saveDriver(Driver driver) {
		return driverRepository.save(driver);
	}
	
	public void deleteDriver(Long id) {
		driverRepository.deleteById(id);
	}
}
