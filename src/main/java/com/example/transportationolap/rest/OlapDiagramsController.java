package com.example.transportationolap.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.transportationolap.domain.repositories.olap.TransportationFactsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OlapDiagramsController {
	
	private final TransportationFactsRepository transportationFactsRepository;

	@GetMapping("/informationAboutAuthor")
	public String informationAboutAuthor() {
		return "informationAboutAuthor";
	}
	
	@GetMapping("/pie-chart/transportationsByDriver")
	public String pieChartTransportationByDriver(Model model) {
		List<Object[]> data = new ArrayList<>();
		data.add(new String[]{"Driver Name", "Transportation Number"});
		data.addAll(transportationFactsRepository.countTransportationsByDriver());
		
		model.addAttribute("initialData", data.toArray());
		model.addAttribute("chartName", "Transportation Numbers by Driver");
		return "pie_chart";
	}

	@GetMapping("/histogram/transportationsByDriver")
	public String histogramTransportationByDriver(Model model) {
		List<Object[]> data = new ArrayList<>();
		data.add(new String[]{"Driver Name", "Transportation Number"});
		data.addAll(transportationFactsRepository.countTransportationsByDriver());

		model.addAttribute("initialData", data.toArray());
		model.addAttribute("chartName", "Transportation Numbers by Driver");
		
		return "histogram";
	}

	@GetMapping("/pie-chart/maxDistanceByVehicleFromBerlin")
	public String pieChartMaxDistanceByVehicleFromBerlin(Model model) {
		List<Object[]> data = new ArrayList<>();
		data.add(new String[]{"Vehicle Type", "Max distance from Berlin"});
		data.addAll(transportationFactsRepository.maxDistanceDrivenByVehicleFromBerlin());

		model.addAttribute("initialData", data.toArray());
		model.addAttribute("chartName", "Max distance by Vehicle from Berlin");
		return "pie_chart";
	}

	@GetMapping("/histogram/maxDistanceByVehicleFromBerlin")
	public String histogramMaxDistanceByVehicleFromBerlin(Model model) {
		List<Object[]> data = new ArrayList<>();
		data.add(new String[]{"Vehicle Type", "Max distance from Berlin"});
		data.addAll(transportationFactsRepository.maxDistanceDrivenByVehicleFromBerlin());

		model.addAttribute("initialData", data.toArray());
		model.addAttribute("chartName", "Max distance by Vehicle from Berlin");

		return "histogram";
	}

	@GetMapping("/pie-chart/averageCostOfTransportationByStartCountryInApril")
	public String pieChartAverageCostOfTransportationByStartCountryInApril(Model model) {
		List<Object[]> data = new ArrayList<>();
		data.add(new String[]{"Start Country", "Average cost of transportation in April"});
		data.addAll(transportationFactsRepository.averageCostOfTransportationByStartCountryInApril());

		model.addAttribute("initialData", data.toArray());
		model.addAttribute("chartName", "Average cost of transportation from country in April");
		return "pie_chart";
	}

	@GetMapping("/histogram/averageCostOfTransportationByStartCountryInApril")
	public String histogramAverageCostOfTransportationByStartCountryInApril(Model model) {
		List<Object[]> data = new ArrayList<>();
		data.add(new String[]{"Start Country", "Average cost of transportation in April"});
		data.addAll(transportationFactsRepository.averageCostOfTransportationByStartCountryInApril());

		model.addAttribute("initialData", data.toArray());
		model.addAttribute("chartName", "Average cost of transportation from country in April");

		return "histogram";
	}
}
