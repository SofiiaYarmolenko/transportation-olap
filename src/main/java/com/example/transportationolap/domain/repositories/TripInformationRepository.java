package com.example.transportationolap.domain.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transportationolap.domain.entities.TripInformation;

@Repository
public interface TripInformationRepository extends JpaRepository<TripInformation, Long> {
	
	List<TripInformation> findByLastUpdateDateAfterAndStatus_StatusIn(LocalDateTime lastUpdateDate, List<String> status);
	
}
