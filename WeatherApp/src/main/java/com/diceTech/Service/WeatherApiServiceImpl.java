package com.diceTech.Service;


import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.*;


@Service
public class WeatherApiServiceImpl implements WeatherApiServiceIntr{
	
	// Injecting OpenWeather API key from application.properties
	@Value("${weather.api.key}") 
    private String weatherApiKey;
	
	// Autowiring RestTemplate to make HTTP requests
	@Autowired
    private  RestTemplate restTemplate;
    
	/**
     * Retrieves the forecast summary for a specific location from the OpenWeatherMap API.
     * 
     * @param location The location for which the forecast summary is requested.
     * @param clientId The client ID for authentication.
     * @param clientSecret The client secret for authentication.
     * @return ResponseEntity containing the forecast summary data or an error message if communication with the API fails.
     */
    @Override
    public ResponseEntity<String> getForecastSummaryByLocationName(String location, String clientId, String clientSecret) {
    	
    	// Setting up HTTP headers for authentication
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("clientId", clientId);
        headers.set("clientSecret", clientSecret);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        // URL for fetching forecast summary
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+ location +"&appid=" + weatherApiKey;
       
        try {
            return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error communicating with Weather API: " + e.getMessage());
        }
    }
    
    /**
     * Retrieves the hourly forecast for a specific location from the OpenWeatherMap API.
     * 
     * @param location The location for which the hourly forecast is requested.
     * @param clientId The client ID for authentication.
     * @param clientSecret The client secret for authentication.
     * @return ResponseEntity containing the hourly forecast data or an error message if communication with the API fails.
     */
    @Override
    public ResponseEntity<String> getHourlyForecastByLocationName(String location, String clientId, String clientSecret) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("clientId", clientId);
        headers.set("clientSecret", clientSecret);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        String url = "https://api.openweathermap.org/data/2.5/forecast?q=" + location + "&appid=" + weatherApiKey;
    
        
        try {
            return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error communicating with Weather API: " + e.getMessage());
        }
    }
}
