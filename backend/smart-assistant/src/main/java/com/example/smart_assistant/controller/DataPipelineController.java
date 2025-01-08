package com.example.smart_assistant.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
// import com.example.smart_assistant.service.DataProcessingService;
// import com.example.smart_assistant.model.News;
import com.example.smart_assistant.service.ExternalApiService;

import com.example.smart_assistant.model.ResponseWrapper;

import com.example.smart_assistant.algorithm.TextSummarizer;
import com.example.smart_assistant.algorithm.KeywordExtractor;

import java.util.*;

@RestController
@RequestMapping("/analysis")
public class DataPipelineController {

    private final ExternalApiService externalApiService;
    // // private final DataProcessingService processingService;

    @Autowired
    public DataPipelineController(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    @GetMapping("/top-issues")
    public List<Map<String, Object>> getTopIssues() {
        // 예시 쿼리로 GDELT API 호출
        String query = "South Korea"; // 필요에 따라 동적으로 설정 가능
        ResponseWrapper response = externalApiService.parseJsonResponse(externalApiService.fetchNewsFromNews(query));
        TextSummarizer textSummarizer = new TextSummarizer();
        KeywordExtractor keywordExtractor = new KeywordExtractor();

        System.out.println(response.getArticles().get(0).getDescription());

        List<Map<String, Object>> result = new ArrayList<>();

        for (int idx = 0; idx < 10; idx++) {
            String article = response.getArticles().get(idx).getDescription();

            String description = textSummarizer.summarizerText(article, 3);

            List<String> keyword = keywordExtractor.extractKeywords(article, 4);

            Map<String, Object> articleData = new HashMap<>();
            articleData.put("title", response.getArticles().get(idx).getTitle());
            articleData.put("url", response.getArticles().get(idx).getUrl());
            articleData.put("description", description);
            articleData.put("keywords", keyword);

            result.add(articleData);

            // System.out.println("Title: " + response.getArticles().get(idx).getTitle() + "[" + idx + "]");
            // System.out.println("Description: " + description);
            // System.out.println();
        }

        // String res = response.getArticles().get(1).getDescription();


        // String text = textSummarizer.summarizerText(res, 2);
        

        // List<String> keyword = keywordExtractor.extractKeywords(res, 4);


        // System.out.println(keyword);
        // System.out.println(text);

        // return response;
        // System.out.println(response.getArticles().get(0).getUrl());
        return result;
    }

    @GetMapping("/top-headlines")
    public ResponseWrapper getTopHeadlines() {
        String query = "South Korea";
        ResponseWrapper response = externalApiService.parseJsonResponse(externalApiService.fetchTrends(query));
        System.out.println(response.getArticles().get(0).getContent());
        return response;

    }
}
