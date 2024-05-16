package com.example.transportationolap.services;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.transportationolap.domain.entities.TripInformation;
import com.example.transportationolap.domain.entities.olap.DimDateInfo;
import com.example.transportationolap.domain.entities.olap.DimDriver;
import com.example.transportationolap.domain.entities.olap.DimLocation;
import com.example.transportationolap.domain.entities.olap.DimVehicle;
import com.example.transportationolap.domain.entities.olap.TransportationFacts;
import com.example.transportationolap.domain.repositories.TripInformationRepository;
import com.example.transportationolap.domain.repositories.olap.DimDateInfoRepository;
import com.example.transportationolap.domain.repositories.olap.DimDriverRepository;
import com.example.transportationolap.domain.repositories.olap.DimLocationRepository;
import com.example.transportationolap.domain.repositories.olap.DimVehicleRepository;
import com.example.transportationolap.domain.repositories.olap.TransportationFactsRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OlapSynchronizationService {

	private LocalDateTime lastProcessedDate = LocalDateTime.of(2020, Month.APRIL, 4, 0, 0);
	private final TransportationFactsRepository transportationFactsRepository;
	private final DimDriverRepository dimDriverRepository;
	private final DimLocationRepository dimLocationRepository;
	private final DimVehicleRepository dimVehicleRepository;
	private final DimDateInfoRepository dimDateInfoRepository;
	private final TripInformationRepository tripInformationRepository;

	@Scheduled(fixedRate = 10000)
	@Transactional
	public void synchronizeOlapWithOperationalData() {
		log.info("Start synchronization of olap data with operational data");
		List<TripInformation> finalizedTripsToSynchronize = tripInformationRepository.findByLastUpdateDateAfterAndStatus_StatusIn(
				lastProcessedDate, List.of("FAILED", "SUCCESSFUL")
		);
		List<TransportationFacts> transportationFactsToSave = new ArrayList<>();
		
		for (TripInformation trip : finalizedTripsToSynchronize) {
			DimDriver dimDriver = getDimDriver(trip.getDriver().getUser().getName() + " " + trip.getDriver().getUser().getSurname());

			DimDateInfo dateInfo = getDateInfo(String.valueOf(trip.getStartDate().getMonth()), String.valueOf(trip.getStartDate().getYear()),
					String.valueOf(trip.getStartDate().getDay()));

			DimVehicle vehicle = getVehicle(trip.getVehicle().getType().getVehicleType());
			
			DimLocation toLocation = getLocation(getCity(), getCountryCode(), getRegion());

			DimLocation fromLocation = getLocation(getCity(), getCountryCode(), getRegion());

			TransportationFacts transportationFacts = TransportationFacts.builder()
					.fromLocation(fromLocation)
					.toLocation(toLocation)
					.cost(getCost())
					.distance(getDistance())
					.driver(dimDriver)
					.vehicle(vehicle)
					.duration(getDuration())
					.status(trip.getStatus().getStatus())
					.startDate(dateInfo)
					.id(getId())
					.build();
			
			transportationFactsToSave.add(transportationFacts);
		}
		
		transportationFactsRepository.saveAll(transportationFactsToSave);
		lastProcessedDate = LocalDateTime.now();
		log.info("Finish synchronization of olap data with operational data. Saved facts: {}", finalizedTripsToSynchronize.size());
	}
	
	private Long dCounter = 0L;
	private Long dateCounter = 0L;
	private Long vCounter = 0L;
	private Long lCounter = 0L;
	
	private DimDriver getDimDriver(String name){
		Optional<DimDriver> dimDriver = dimDriverRepository.findByName(name);
		if(dimDriver.isPresent()){
			return dimDriver.get();
		}
		DimDriver newDimDriver = DimDriver.builder()
				.name(name)
				.id(dCounter++)
				.build();
		return dimDriverRepository.save(newDimDriver);
	}
	
	private DimDateInfo getDateInfo(String monthOfYear, String year, String dayOfMonth) {
		Optional<DimDateInfo> dimDateInfo = dimDateInfoRepository.findByDayOfMonthAndExactYearAndMonthOfYear(dayOfMonth, year, monthOfYear);
		if (dimDateInfo.isPresent()) {
			return dimDateInfo.get();
		}
		DimDateInfo dateInfo =
				DimDateInfo.builder().monthOfYear(monthOfYear)
						.exactYear(year)
						.dayOfMonth(dayOfMonth)
						.id(dateCounter++)
						.build();
		return dimDateInfoRepository.save(dateInfo);
	}

	private DimVehicle getVehicle(String type) {
		Optional<DimVehicle> dimVehicle = dimVehicleRepository.findDimVehicleByType(type);
		if (dimVehicle.isPresent()) {
			return dimVehicle.get();
		}
		DimVehicle vehicle = DimVehicle.builder().type(type).id(vCounter++).build();
		return dimVehicleRepository.save(vehicle);
	}

	private DimLocation getLocation(String city, String country, String region) {
		Optional<DimLocation> dimLocation = dimLocationRepository.findByCityAndCountryAndRegion(city, country, region);
		if (dimLocation.isPresent()) {
			return dimLocation.get();
		}
		DimLocation location = DimLocation.builder()
				.city(city)
				.country(country)
				.region(region)
				.id(lCounter++)
				.build();
		
		return dimLocationRepository.save(location);
	}

	private String getCity() {
		List<String> cityList =
				Arrays.asList("London", "Paris", "Tokyo", "New York City", "Berlin", "Los Angeles", "Sydney", "Moscow", "Rio de Janeiro",
						"Mumbai", "Istanbul", "Seoul", "Rome", "Toronto", "Buenos Aires", "Dubai", "Bangkok", "Amsterdam", "Cairo",
						"Beijing");
		Random rand = new Random();
		return cityList.get(rand.nextInt(cityList.size()));
	}

	private String getCountryCode() {
		List<String> countryCodes =
				Arrays.asList("C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10");
		Random rand = new Random();
		return countryCodes.get(rand.nextInt(countryCodes.size()));
	}

	private String getRegion() {
		List<String> regions =
				Arrays.asList("R1", "R2", "R3", "R4", "R5", "R6", "R7", "R8", "R9", "R10");
		Random rand = new Random();
		return regions.get(rand.nextInt(regions.size()));
	}

	private int getCost() {
		Random random = new Random();
		int randomCost = (int) (1000 + (1000000 - 1000) * random.nextDouble());
		randomCost = (int) (Math.round(randomCost * 100.0) / 100.0);
		return randomCost;
	}

	private int getDistance() {
		Random random = new Random();
		int randomCost = (int) (10 + (10000 - 10) * random.nextDouble());
		randomCost = (int) (Math.round(randomCost * 100.0) / 100.0);
		return randomCost;
	}

	private int getDuration() {
		Random random = new Random();
		int randomCost = (int) (1000 + (1000000 - 1000) * random.nextDouble());
		randomCost = (int) (Math.round(randomCost * 100.0) / 100.0);
		return randomCost;
	}

	private Long counter = 0L;

	private Long getId() {
		return counter++;
	}
}
