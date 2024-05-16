package com.example.transportationolap.domain.repositories.olap;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transportationolap.domain.entities.olap.DimDriver;

@Repository
public interface DimDriverRepository extends JpaRepository<DimDriver, Long> {
	
	Optional<DimDriver> findByName(String name);
}
