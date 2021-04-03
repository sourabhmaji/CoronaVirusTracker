package io.javaSpring.CoronaVirusTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.javaSpring.CoronaVirusTracker.model.LocationStats;
import io.javaSpring.CoronaVirusTracker.services.CoronaVirusDataService;

@Controller
public class CoronaController {
	
	@Autowired
	CoronaVirusDataService coronaService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(Model model) {
		List<LocationStats> allStat=coronaService.getLocationStat();
		long totalCases = allStat.stream().mapToLong(stat->stat.getLatestTotalCases()).sum();
		long increasedCases = allStat.stream().mapToLong(stat->stat.getDiffOfCasesFromPrevDay()).sum();
		model.addAttribute("locationStat",allStat);
		model.addAttribute("totalReportedCases",totalCases);
		model.addAttribute("increasedCases",increasedCases);
		return "Home";
		
	}
	

}
