package com.example.smart_assistant.controller;

import com.example.smart_assistant.model.WeatherResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.smart_assistant.service.ExternalApiService;
import com.example.smart_assistant.model.WeatherResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

@RestController
public class WeatherController {
    @Value("${weatherapi.key}")
    private String apiKey;

    private final ExternalApiService externalApiService;
    private final ObjectMapper objectMapper;


    @Autowired
    public WeatherController(ExternalApiService externalApiService, ObjectMapper objectMapper) {
        this.externalApiService = externalApiService;
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "/weather", produces = "application/json")
    public WeatherResponse getWeather(@RequestParam String location) {
        // WeatherResponse response = externalApiService.mappingResponse(externalApiService.fetchWeather(location));
        String response = externalApiService.fetchWeather(location);
        // try {
        //     // JSON 파싱
        //     JsonNode rootNode = objectMapper.readTree(response);
        //     String cityName = rootNode.path("location").path("name").asText();
        //     double temperature = rootNode.path("current").path("temp_c").asDouble();
        //     String condition = rootNode.path("current").path("condition").path("text").asText();

        //     // 특정 값만 JSON 형식으로 반환
        //     return String.format("{\"city\":\"%s\",\"temperature\":%f,\"condition\":\"%s\"}",
        //             cityName, temperature, condition);
        // } catch (Exception e) {
        //     e.printStackTrace();
        //     return "{\"error\":\"Failed to parse weather data\"}";
        // }

        return externalApiService.mappingResponse(response);
    }

    @GetMapping(value = "/testWeather", produces = "application/json") 
    public String getTestWeather(@RequestParam String location) {
        String response = externalApiService.fetchWeather(location);
        return response;
    }

    @GetMapping(value = "/week-weather", produces = "application/json")
    public List<WeatherResponse> getWeeklyWeather(@RequestParam String location) throws Exception{
        String response = externalApiService.fetchWeeklyWeather(location);

        // Map<String, Object> weatherData = externalApiService.parseWeatherResponse(response);
        // Map<String, Object> lo = (Map<String, Object>) weatherData.get("location");
        // String cityName = (String) lo.get("name");
        // System.out.println("City: " + cityName);


        List<WeatherResponse> weatherResponse = externalApiService.mappingResponses(response);
        return weatherResponse;
        // return null;
    }

}
