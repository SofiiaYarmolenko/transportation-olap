package com.example.transportationolap.domain.entities;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TRIP_INFORMATION")
public class TripInformation {

	@Id
	@SequenceGenerator(name = "trip_information_SEQ", allocationSize = 1, sequenceName = "trip_information_SEQ", initialValue = 500)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trip_information_SEQ")
	@Column(name = "TRIP_ID")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "DRIVER_ID")
	private Driver driver;

	@Column(name = "START_COORDINATES")
	private String startCoordinates;

	@Column(name = "END_COORDINATES")
	private String endCoordinates;

	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "END_DATE")
	private Date endDate;

	@OneToOne
	@JoinColumn(name = "STATUS_ID")
	private TripStatus status;

	@OneToOne
	@JoinColumn(name = "VEHICLE_ID")
	private Vehicle vehicle;

	@LastModifiedDate
	@Column(name = "LAST_UPDATE_DATE")
	private LocalDateTime lastUpdateDate;
}
