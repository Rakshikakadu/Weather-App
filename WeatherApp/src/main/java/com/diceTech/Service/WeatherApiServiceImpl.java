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
	
	@Value("${weather.api.key}")
    private String weatherApiKey;
	
	@Autowired
    private  RestTemplate restTemplate;
    
    @Override
    public ResponseEntity<String> getForecastSummaryByLocationName(String location, String clientId, String clientSecret) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("clientId", clientId);
        headers.set("clientSecret", clientSecret);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+ location +"&appid=" + weatherApiKey;
       
        try {
            return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error communicating with Weather API: " + e.getMessage());
        }
    }

    
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