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

    @GetMapping("/forecast-summary")
    public ResponseEntity<String> getForecastSummaryByLocationName(@RequestParam  String location, @RequestHeader("clientId") String clientId, @RequestHeader("clientSecret") String clientSecret) {
        if (clientId == null || clientSecret == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing client ID or client secret.");
        }

        return weatherApiService.getForecastSummaryByLocationName(location, clientId, clientSecret);
    }

    @GetMapping("/hourly-forecast")
    public ResponseEntity<String> getHourlyForecastByLocationName(@RequestParam String location, @RequestHeader("clientId") String clientId, @RequestHeader("clientSecret") String clientSecret) {
        if (clientId == null || clientSecret == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing client ID or client secret.");
        }

        return weatherApiService.getHourlyForecastByLocationName(location, clientId, clientSecret);
    }
	
}
