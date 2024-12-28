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
import com.example.smart_assistant.algorithm.RecommendationAlgorithm;
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
    public String getWeather(@RequestParam String location) {
        // WeatherResponse response = externalApiService.mappingResponse(externalApiService.fetchWeather(location));
        String response = externalApiService.fetchWeather(location);
        WeatherResponse mappingResponse = externalApiService.mappingResponse(response);
        System.out.println(mappingResponse.getCurrent().getTemp_c());
        // double tempC, double feelsLikeC, double windChillC, 
        //                     double heatIndexC, double dewPointC, double windKph, 
        //                     String condition
        double tempC = mappingResponse.getCurrent().getTemp_c();
        double feelsLikeC = mappingResponse.getCurrent().getFeelslike_c();
        double windChillC = mappingResponse.getCurrent().getWindchill_c();
        double heatIndexC = mappingResponse.getCurrent().getHeatindex_c();
        double dewPointC = mappingResponse.getCurrent().getDewpoint_c();
        double windKph = mappingResponse.getCurrent().getWind_kph();
        String condition = mappingResponse.getCurrent().getCondition().getText();

        String forecaseResponse = externalApiService.getForecast(location);
        WeatherResponse forecastMappingResponse = externalApiService.mappingResponse(forecaseResponse);

        
        System.out.println("forecast weather avg: " + forecastMappingResponse.getForecast().getForecastday().get(0).getDay().getAvgtemp_c());
        System.out.println("forecast weather max: " + forecastMappingResponse.getForecast().getForecastday().get(0).getDay().getMaxtemp_c());
        System.out.println("forecast weather min: " + forecastMappingResponse.getForecast().getForecastday().get(0).getDay().getMintemp_c());

        double avgtemp = forecastMappingResponse.getForecast().getForecastday().get(0).getDay().getAvgtemp_c();
        double maxtemp = forecastMappingResponse.getForecast().getForecastday().get(0).getDay().getMaxtemp_c();
        double mintemp = forecastMappingResponse.getForecast().getForecastday().get(0).getDay().getMintemp_c();

        RecommendationAlgorithm recommendationAlgorithm = new RecommendationAlgorithm();
        String recommendation = recommendationAlgorithm.recommendOutfit(tempC, feelsLikeC, windChillC, heatIndexC, dewPointC, windKph, condition, avgtemp, maxtemp, mintemp, 0, "");

        System.out.println(tempC + "/" + feelsLikeC + "/" + windChillC + "/" + heatIndexC + "/" + dewPointC + "/" + windKph + "/" + condition);
        System.out.println("result: " + recommendation);
        // return mappingResponse;
        return recommendation;
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
