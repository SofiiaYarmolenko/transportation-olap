package com.example.transportationolap.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "TRIP_STATUS")
public class TripStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TRIP_STATUS_ID")
	private Integer id;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "DESCRIPTION")
	private String description;
}
