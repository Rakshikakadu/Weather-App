package com.diceTech.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diceTech.Service.WeatherApiServiceImpl;

@RestController
public class WeatherAppController {
	
	@Autowired
    private WeatherApiServiceImpl weatherApiService;

	 /**
     * Endpoint to get the forecast summary for a specific location.
     * 
     * @param location The location for which the forecast summary is requested.
     * @param clientId The client ID for authentication.
     * @param clientSecret The client secret for authentication.
     * @return ResponseEntity containing the forecast summary data or an error message if authentication fails.
     */
    @GetMapping("/forecast-summary")
    public ResponseEntity<String> getForecastSummaryByLocationName(@RequestParam  String location, @RequestHeader("clientId") String clientId, @RequestHeader("clientSecret") String clientSecret) {
        if (clientId == null || clientSecret == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing client ID or client secret.");
        }

        return weatherApiService.getForecastSummaryByLocationName(location, clientId, clientSecret);
    }

    /**
     * Endpoint to get the hourly forecast for a specific location.
     * 
     * @param location The location for which the hourly forecast is requested.
     * @param clientId The client ID for authentication.
     * @param clientSecret The client secret for authentication.
     * @return ResponseEntity containing the hourly forecast data or an error message if authentication fails.
     */
    @GetMapping("/hourly-forecast")
    public ResponseEntity<String> getHourlyForecastByLocationName(@RequestParam String location, @RequestHeader("clientId") String clientId, @RequestHeader("clientSecret") String clientSecret) {
        if (clientId == null || clientSecret == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing client ID or client secret.");
        }

        return weatherApiService.getHourlyForecastByLocationName(location, clientId, clientSecret);
    }
	
}
