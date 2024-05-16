package com.example.transportationolap.domain.entities.olap;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DIM_DRIVER")
public class DimDriver {

	@Id
	@Column(name = "DRIVER_ID")
	private Long id;

	@Column(name = "NAME")
	private String name;
}
