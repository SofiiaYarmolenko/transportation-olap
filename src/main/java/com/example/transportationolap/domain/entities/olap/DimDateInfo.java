package com.example.transportationolap.domain.entities.olap;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DIM_DATE_INFO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DimDateInfo {

	@Id
	@Column(name = "DATE_INFO_ID")
	private Long id;

	@Column(name = "DAY_OF_MONTH")
	private String dayOfMonth;

	@Column(name = "MONTH_OF_YEAR")
	private String monthOfYear;

	@Column(name = "EXACT_YEAR")
	private String exactYear;

}
