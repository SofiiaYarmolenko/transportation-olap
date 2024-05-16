package com.example.transportationolap.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "DRIVER")
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DRIVER_ID")
	private Long id;

	@OneToOne
	@JoinColumn(name = "USER_ID")
	private UserInformation user;

	@Column(name = "LICENCE_NUMBER")
	private String licenceNumber;
}
