package com.example.transportationolap.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "VEHICLE_TYPE")
public class VehicleType {

	@Id
	@Column(name = "VEHICLE_TYPE_ID")
	private Integer id;

	@Column(name = "VEHICLE_TYPE")
	private String vehicleType;

	@Column(name = "DESCRIPTION")
	private String description;
}
