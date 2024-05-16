package com.example.transportationolap.domain.entities.olap;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "TRANSPORTATION_FACTS")
public class TransportationFacts {

	@Id
	@Column(name = "FACT_ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "START_DATE_ID")
	private DimDateInfo startDate;

	@ManyToOne
	@JoinColumn(name = "VEHICLE_ID")
	private DimVehicle vehicle;

	@ManyToOne
	@JoinColumn(name = "DRIVER_ID")
	private DimDriver driver;

	@ManyToOne
	@JoinColumn(name = "FROM_LOCATION_ID")
	private DimLocation fromLocation;

	@ManyToOne
	@JoinColumn(name = "TO_LOCATION_ID")
	private DimLocation toLocation;

	@Column(name = "DISTANCE")
	private Integer distance;

	@Column(name = "COST")
	private Integer cost;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "DURATION")
	private Integer duration;
}