package com.diceTech.Service;

import org.springframework.http.ResponseEntity;

public interface WeatherApiServiceIntr {
	
	public ResponseEntity<String> getForecastSummaryByLocationName(String location, String clientId, String clientSecret);
	
	public ResponseEntity<String> getHourlyForecastByLocationName(String location, String clientId, String clientSecret);

}
