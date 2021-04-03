package io.javaSpring.CoronaVirusTracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.javaSpring.CoronaVirusTracker.model.LocationStats;

@Service
public class CoronaVirusDataService {
   
	private static String VIRUS_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private static final Logger LOGGER = LoggerFactory.getLogger(CoronaVirusDataService.class);
	List<LocationStats> lstLocationStat = new ArrayList<>();
	public List<LocationStats> getLocationStat() {
		return lstLocationStat;
	}
	
	@PostConstruct
	@Scheduled(cron="* * * * * *")
	public void fetchVirusdata() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request =  HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
	   HttpResponse<String> httpResponse =client.send(request, HttpResponse.BodyHandlers.ofString());
	  //LOGGER.info(httpResponse.body());
	   StringReader in = new StringReader(httpResponse.body());
	   Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
	   List<LocationStats> newLocationStats= new ArrayList<>();
	   for (CSVRecord record : records) {
		   LocationStats lstats = new LocationStats();
		   lstats.setState( record.get("Province/State"));
		   lstats.setCountry(record.get("Country/Region"));
	       lstats.setLatestTotalCases(Long.parseLong(record.get(record.size()-1)));
	       lstats.setDiffOfCasesFromPrevDay(Long.parseLong(record.get(record.size()-1))-Long.parseLong(record.get(record.size()-2)));
	       newLocationStats.add(lstats);
	       System.out.println(lstats);
	   }
	   lstLocationStat= newLocationStats;
	 // System.out.println(httpResponse.body());		   
	}	
}
