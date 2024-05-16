package com.example.transportationolap.domain.repositories.olap;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.transportationolap.domain.entities.olap.TransportationFacts;

@Repository
public interface TransportationFactsRepository extends JpaRepository<TransportationFacts, Long> {

//	@Query("SELECT AVG(tf.cost) " +
//			"FROM TransportationFacts tf " +
//			"WHERE tf.startDate >= :startDate AND tf.startDate <= :endDate")
//	Long calculateAverageCostByPeriod(Date startDate, Date endDate);

	@Query("SELECT tf.driver.name, COUNT(tf.driver) " +
			"FROM TransportationFacts tf " +
			"GROUP BY tf.driver")
	List<Object[]> countTransportationsByDriver();

	@Query("SELECT tf.vehicle.type, MAX(tf.distance) " +
			"FROM TransportationFacts tf " +
			"JOIN DimLocation start_loc ON tf.fromLocation.id = start_loc.id " +
			"WHERE start_loc.city = 'Berlin' " +
			"GROUP BY tf.vehicle.type")
	List<Object[]> maxDistanceDrivenByVehicleFromBerlin();

	@Query("SELECT start_loc.country, AVG(tf.cost) " +
			"FROM TransportationFacts tf " +
			"JOIN DimLocation start_loc ON tf.fromLocation.id = start_loc.id " +
			"JOIN DimDateInfo date_info ON tf.startDate.id = date_info.id " +
			"WHERE date_info.monthOfYear = '4' " +
			"GROUP BY start_loc.country")
	List<Object[]> averageCostOfTransportationByStartCountryInApril();

	@Query("SELECT dv.type, COUNT(tf.id) AS tripCount " +
			"FROM TransportationFacts tf " +
			"JOIN DimVehicle dv ON tf.vehicle.id = dv.id " +
			"WHERE tf.status = 'FAILED' " +
			"GROUP BY tf.vehicle.id, dv.type " +
			"ORDER BY tripCount DESC")
	List<Object[]> sortedListOfVehicleTypesWithHighestSuccessfulTrips();

	@Query("SELECT loc.city, COUNT(tf.id) AS tripCount " +
			"FROM TransportationFacts tf " +
			"JOIN DimLocation loc ON tf.toLocation.id = loc.id " +
			"GROUP BY loc.city " +
			"ORDER BY tripCount DESC")
	List<Object[]> sortedListOfPopularFinishCities();

	@Query("SELECT d.name, MAX(tf.distance) AS maxDistance " +
			"FROM TransportationFacts tf " +
			"JOIN DimDriver d ON tf.driver.id = d.id " +
			"GROUP BY d.name " +
			"ORDER BY maxDistance DESC")
	List<Object[]> sortedListOfDriverNamesByMaxDistanceFinished();
}
