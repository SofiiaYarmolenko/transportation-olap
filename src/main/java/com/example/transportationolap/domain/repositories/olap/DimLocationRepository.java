package com.example.transportationolap.domain.repositories.olap;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transportationolap.domain.entities.olap.DimLocation;

@Repository
public interface DimLocationRepository extends JpaRepository<DimLocation, Long> {
	
	Optional<DimLocation> findByCityAndCountryAndRegion(String city, String country, String region);
}
