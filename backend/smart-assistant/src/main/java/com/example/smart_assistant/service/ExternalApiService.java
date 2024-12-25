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
        // String url = "https://api.gdeltproject.org/api/v2/doc/doc?query=" + query + "&format=json&maxrecords=10";
        // String url = "https://api.gdeltproject.org/api/v2/doc/doc?query=country:Korea&startdate=20241224000000&enddate=20241225000000&mode=ArtList&format=json";
        try {

            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
    
    
            String url = "https://newsapi.org/v2/everything?" +
                "q=" + encodedQuery + "&" + "language=ko&" +
                "apiKey=" + ApiKey;
    
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
                // ResponseEntity<ResponseWrapper> response = restTemplate.exchange(
                //     url, HttpMethod.GET, requestEntity, ResponseWrapper.class
                // );
    
                System.out.println("Response status: " + response.getStatusCode());
                System.out.println("Response body: " + response.getBody());
    
                if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                    // String rawJson = response.getBody().toString();
                    // String decodedJson = new String(rawJson.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                    // System.out.println("Decoded JSON: " + decodedJson);

                    // // ResponseWrapper 클래스를 사용해 디코딩된 JSON을 파싱
                    // ResponseWrapper wrapper = new ObjectMapper().readValue(decodedJson, ResponseWrapper.class);
                    // return wrapper.getArticles();

                    // System.out.println("\n\nResponse\n\n");
                    // String jsonString = response.getBody(); // JSON 문자열 확인
                    // System.out.println("JSON String: " + jsonString);

                    // byte[] decodedBytes = jsonString.getBytes(StandardCharsets.ISO_8859_1);
                    // String decodedJson = new String(decodedBytes, StandardCharsets.UTF_8); // UTF-8로 문자열 변환
                    
                    
                    // ObjectMapper objectMapper = new ObjectMapper();
                    // ResponseWrapper wrapper = objectMapper.readValue(decodedJson, ResponseWrapper.class); // JSON 파싱
                    // return wrapper.getArticles();
                    return response.getBody();
                } else {
                    System.out.println("Failed to fetch news. Status code: " + response.getStatusCode());
                }
    
        } catch (UnsupportedEncodingException e) {
            System.out.println("Failed to fetch news. Status code: ");
            e.printStackTrace();
            // return List.of();
        // } catch (JsonProcessingException e) {
        //     System.out.println("Failed to fetch news. Status code: ");
        //     e.printStackTrace();
        //     // return List.of();
        // }
        }
        // return List.of();
        return null;
    }

    // public Trend fetchTrendsFromTwitter(String hashtag) {
    //     String url = "https://api.twitter.com/2/tweets/search/recent?query=" + hashtag;
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.set("Authorization", "Bearer ");
    //     HttpEntity<String> entity = new HttpEntitu<>(headers);
    //     ResponseEntity<Trend> response = restTemplate.exchange(
    //         url, HttpMethod.GET, entity, Trend.class);

    //     return response.getBody();
    // }
}
