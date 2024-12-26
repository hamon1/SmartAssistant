package com.example.smart_assistant.controller;

import com.example.smart_assistant.model.WeatherResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.smart_assistant.service.ExternalApiService;

@RestController
public class WeatherController {
    @Value("${weatherapi.key}")
    private String apiKey;



    private final ExternalApiService externalApiService;
    // // private final DataProcessingService processingService;
    // private final RestTemplate restTemplate;

    @Autowired
    public WeatherController(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }
    // @Autowired
    // public WeatherController(RestTemplate restTemplate) {
    //     this.restTemplate = restTemplate;
    // }



    @GetMapping(value = "/weather", produces = "application/json")
    public String getWeather(@RequestParam String location) {
        String response = externalApiService.fetchWeather(location);
        return response;
    }

    @GetMapping(value = "/week-weather", produces = "application/json")
    public String getWeeklyWeather(@RequestParam String location) {
        String response = externalApiService.fetchWeeklyWeather(location);
        return response;
    }

}
