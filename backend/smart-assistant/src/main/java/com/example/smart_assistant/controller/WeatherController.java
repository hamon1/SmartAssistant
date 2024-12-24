package com.example.smart_assistant.controller;

import com.example.smart_assistant.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {
    @Value("${weatherapi.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public WeatherController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/weather", produces = "application/json")
    public String getWeather(@RequestParam String location) {
        String url = String.format("https://api.weatherapi.com/v1/current.json?key=%s&q=%s", apiKey, location);
        String response = restTemplate.getForObject(url, String.class);
        return response;
    }
}
