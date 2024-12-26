package com.example.smart_assistant.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.springframework.cache.annotation.Cacheable;

import com.example.smart_assistant.model.News;
import com.example.smart_assistant.model.ResponseWrapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import java.nio.charset.StandardCharsets;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
// import com.example.smart_assistant.model.Trend;

@Service
public class ExternalApiService {
    
    @Value("${newsapi.key}")
    private String NewsApiKey;

    @Value("${weatherapi.key}")
    private String WeatherApiKey;


    private final RestTemplate restTemplate;

    @Autowired
    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchWeather(String location) {
            String url = String.format("https://api.weatherapi.com/v1/current.json?key=%s&q=%s", WeatherApiKey, location);
            String response = restTemplate.getForObject(url, String.class);
            return response;
    }

    // @Scheduled(cron = "0 0 0 * * *") // 매일 자정 실행
    @Cacheable(value="weather", key = "#location")
    public String fetchWeeklyWeather(String location) {
        List<Map<String, Object>> weekWeatherData = new ArrayList<>();

        LocalDate today = LocalDate.now();

        for (int i = 1; i <= 7; i++) {
            LocalDate date = today.minusDays(i);
            String url = String.format(
                "https://api.weatherapi.com/v1/history.json?key=%s&q=%s&dt=%s",
                WeatherApiKey, location, date
            );

            try {
                // 각 날짜별로 데이터 가져오기
                String response = restTemplate.getForObject(url, String.class);

                // JSON 응답을 Map으로 변환
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> dailyWeather = objectMapper.readValue(response, Map.class);

                // 일별 데이터 추가
                weekWeatherData.add(dailyWeather);
            } catch (Exception e) {
                System.out.println("Failed to fetch weather data for date: " + date);
                e.printStackTrace();
            }
        }

        // 결과를 JSON 형태로 변환하여 반환
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(weekWeatherData);
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to process weather data";
        }
    }

    public String fetchNewsFromNews(String query) {
        try {

            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());

            ZonedDateTime now = ZonedDateTime.now(); // 현재 시간
            ZonedDateTime yesterday = now.minusDays(14); // 24시간 전

            // ISO 8601 형식으로 변환
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
            String from = yesterday.format(formatter);
            String to = now.format(formatter);

            System.out.println("From: " + from);
            System.out.println("To: " + to);

            String url = "https://newsapi.org/v2/everything?" +
                "q=" + encodedQuery 
                + "&from=" + from 
                + "&to=" + to 
                // + "&language=ko" 
                + "&sortBy=publishedAt" 
                + "&apiKey=" + NewsApiKey;
    
             // 요청 헤더 설정 (Optional)
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json; charset=UTF-8"); // JSON 형식의 응답 요청
            // headers.set("Accept-Charset", "UTF-8"); // UTF-8
    
            // 요청 엔티티 생성
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    

            // GDELT API 요청
            ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, String.class
            );

            // System.out.println("Response status: " + response.getStatusCode());
            // System.out.println("Response body: " + response.getBody());

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            } else {
                System.out.println("Failed to fetch news. Status code: " + response.getStatusCode());
            }
    
        } catch (UnsupportedEncodingException e) {
            System.out.println("Failed to fetch news. Status code: ");
            e.printStackTrace();
        }
        return null;
    }

    public String fetchTrends(String query) {
        try {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
            String url = "https://newsapi.org/v2/top-headlines?" +
                "q=" + encodedQuery 
                // + "&from=" + from 
                // + "&to=" + to 
                // + "&language=ko" 
                // + "&sortBy=publishedAt" 
                + "&apiKey=" + NewsApiKey;
            // 요청 헤더 설정 (Optional)
            HttpHeaders headers = new HttpHeaders();
            // headers.set("Accept", "application/json"); // JSON 형식의 응답 요청
            headers.set("Accept", "application/json; charset=UTF-8"); // JSON 형식의 응답 요청

    
            // 요청 엔티티 생성
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    
            // GDELT API 요청
            ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, String.class
            );

            // System.out.println("Response status: " + response.getStatusCode());
            // System.out.println("Response body: " + response.getBody());
            HttpHeaders header = response.getHeaders();
            String contentType = header.getFirst(HttpHeaders.CONTENT_TYPE);
            System.out.println("Content-Type: " + contentType);  // 응답 헤더 출력

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            } else {
                System.out.println("Failed to fetch news. Status code: " + response.getStatusCode());
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("Failed to fetch news. Status code: ");
            e.printStackTrace();
        }

        return null;
    }

    public ResponseWrapper parseJsonResponse(String jsonResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
             // JSON 응답이 UTF-8로 인코딩된 문자열이라 가정
        // String utf8Json = new String(jsonResponse.getBytes(), StandardCharsets.UTF_8);
        byte[] utf8Bytes = jsonResponse.getBytes(StandardCharsets.UTF_8);
        String utf8Json = new String(utf8Bytes, StandardCharsets.UTF_8);
        
        
        return objectMapper.readValue(utf8Json, ResponseWrapper.class);
            // return objectMapper.readValue(jsonResponse, ResponseWrapper.class);
        } catch (JsonProcessingException e) {
            System.out.println("Failed to parse JSON response.");
            e.printStackTrace();
        }
        return null;
    }
}
