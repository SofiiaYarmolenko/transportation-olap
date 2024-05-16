package com.example.transportationolap.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transportationolap.domain.entities.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
