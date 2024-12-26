package com.example.smart_assistant.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.example.smart_assistant.model.News;
import com.example.smart_assistant.model.ResponseWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import java.nio.charset.StandardCharsets;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
// import com.example.smart_assistant.model.Trend;

@Service
public class ExternalApiService {
    
    @Value("${newsapi.key}")
    private String ApiKey;

    private final RestTemplate restTemplate;

    @Autowired
    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
                + "&apiKey=" + ApiKey;
    
             // 요청 헤더 설정 (Optional)
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json"); // JSON 형식의 응답 요청
            // headers.set("Accept-Charset", "UTF-8"); // UTF-8
    
            // 요청 엔티티 생성
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    

            // GDELT API 요청
            ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, String.class
            );

            System.out.println("Response status: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody());

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

    public String fetchTrendsFromTwitter(String query) {
        try {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());

            // ZonedDateTime now = ZonedDateTime.now(); // 현재 시간
            // ZonedDateTime yesterday = now.minusDays(14); // 24시간 전

            // ISO 8601 형식으로 변환
            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
            // String from = yesterday.format(formatter);
            // String to = now.format(formatter);
            String url = "https://newsapi.org/v2/top-headlines?" +
                "q=" + encodedQuery 
                // + "&from=" + from 
                // + "&to=" + to 
                // + "&language=ko" 
                // + "&sortBy=publishedAt" 
                + "&apiKey=" + ApiKey;
            // 요청 헤더 설정 (Optional)
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json"); // JSON 형식의 응답 요청
            // headers.set("Accept-Charset", "UTF-8"); // UTF-8
    
            // 요청 엔티티 생성
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    
            // GDELT API 요청
            ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, String.class
            );

            System.out.println("Response status: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody());

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
}
