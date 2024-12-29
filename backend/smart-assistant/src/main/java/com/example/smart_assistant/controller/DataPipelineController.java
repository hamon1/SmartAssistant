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
    public ResponseWrapper getTopIssues() {
        // 예시 쿼리로 GDELT API 호출
        String query = "South Korea"; // 필요에 따라 동적으로 설정 가능
        ResponseWrapper response = externalApiService.parseJsonResponse(externalApiService.fetchNewsFromNews(query));

        System.out.println(response.getArticles().get(0).getDescription());

        String res = response.getArticles().get(1).getDescription();

        TextSummarizer textSummarizer = new TextSummarizer();

        String text = textSummarizer.summarizerText(res, 2);
        
        KeywordExtractor keywordExtractor = new KeywordExtractor();

        List<String> keyword = keywordExtractor.extractKeywords(res, 4);


        System.out.println(keyword);
        System.out.println(text);

        return response;
    }

    @GetMapping("/top-headlines")
    public ResponseWrapper getTopHeadlines() {
        String query = "South Korea";
        ResponseWrapper response = externalApiService.parseJsonResponse(externalApiService.fetchTrends(query));
        System.out.println(response.getArticles().get(0).getContent());
        return response;

    }
}
