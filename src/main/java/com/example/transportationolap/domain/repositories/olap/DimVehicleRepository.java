package com.example.transportationolap.domain.repositories.olap;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transportationolap.domain.entities.olap.DimVehicle;

@Repository
public interface DimVehicleRepository extends JpaRepository<DimVehicle, Long> {
	
	Optional<DimVehicle> findDimVehicleByType(String type);
}
