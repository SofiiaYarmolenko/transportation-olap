package com.example.transportationolap.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.transportationolap.domain.entities.TripInformation;
import com.example.transportationolap.domain.repositories.TripInformationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TripInformationService {

	private final TripInformationRepository tripInformationRepository;
	
	public Optional<TripInformation> getTripInformationById(Integer id) {
		return tripInformationRepository.findById(Integer.toUnsignedLong(id));
	}

	public TripInformation saveTripInformation(TripInformation tripInformation) {
		return tripInformationRepository.save(tripInformation);
	}
	
	public void deleteTripInformation(Integer id) {
		tripInformationRepository.deleteById(Integer.toUnsignedLong(id));
	}
}
