package com.example.transportationolap.domain.repositories.olap;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transportationolap.domain.entities.olap.DimDateInfo;

@Repository
public interface DimDateInfoRepository extends JpaRepository<DimDateInfo, Long> {
	
	Optional<DimDateInfo> findByDayOfMonthAndExactYearAndMonthOfYear(String dayOfMonth, String exactYear, String monthOfYear);
}
