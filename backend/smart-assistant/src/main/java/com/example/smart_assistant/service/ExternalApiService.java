package com.example.smart_assistant.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.example.smart_assistant.model.News;
import com.example.smart_assistant.model.ResponseWrapper;

import java.util.List;
// import com.example.smart_assistant.model.Trend;

@Service
public class ExternalApiService {

    private final RestTemplate restTemplate;

    @Autowired
    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<News> fetchNewsFromGdelt(String query) {
        String url = "https://api.gdeltproject.org/api/v2/doc/doc?query=" + query + "&format=json&maxrecords=10";
         // 요청 헤더 설정 (Optional)
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json"); // JSON 형식의 응답 요청

        // // 요청 엔티티 생성 (Optional)
        // HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // try {
        //     ResponseEntity<News> response = restTemplate.exchange(
        //         url, HttpMethod.GET, requestEntity, News.class
        //     );

        //     System.out.println("Response status: " + response.getStatusCode());
        //     System.out.println("Response body: " + response.getBody());
    
        //     if (response.getStatusCode().is2xxSuccessful()) {
        //         return response.getBody();
        //     } else {
        //         System.out.println("Failed to fetch news. Status code: " + response.getStatusCode());
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        //     return null;
        // }
    
        // return null;
        // 요청 엔티티 생성
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        try {
            // GDELT API 요청
            ResponseEntity<ResponseWrapper> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, ResponseWrapper.class
            );

            System.out.println("Response status: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody());

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody().getArticles();
            } else {
                System.out.println("Failed to fetch news. Status code: " + response.getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }

        return List.of();
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
