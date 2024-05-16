package com.example.transportationolap.domain.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "VEHICLE")
@Data
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "VEHICLE_ID")
	private Integer id;

	@Column(name = "NUMBER_ID")
	private String numberId;

	@Column(name = "START_DATE")
	private Date startDate;

	@ManyToOne
	@JoinColumn(name = "TYPE_ID")
	private VehicleType type;
}
