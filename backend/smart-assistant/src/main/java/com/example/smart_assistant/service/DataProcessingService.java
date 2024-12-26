// package com.example.smart_assistant.service;

// import java.util.List;
// import org.springframework.stereotype.Service;
// import com.example.smart_assistant.repository.NewsRepository;
// import com.example.smart_assistant.repository.TrendRepository;
// import com.example.smart_assistant.model.News;
// import com.example.smart_assistant.model.Trend;

// @Service
// public class DataProcessingService {

//     private final NewsRepository newsRepository;
//     private final TrendRepository trendRepository;

//     public DataProcessingService(NewsRepository newsRepository, TrendRepository trendRepository) {
//         this.newsRepository = newsRepository;
//         this.trendRepository = trendRepository;
//     }

//     public void processAndStoreNews(News newsData) {
//         List<News> cleanedData = cleanAndTransformNews(newsData);
//         // newsRepository.saveAll(cleanedData);
//     }

//     public void processAndStoreTrends(Trend trendData) {
//         List<Trend> cleanedData = cleanAndTransformTrends(trendData);
//         // trendRepository.saveAll(cleanedData);
//     }

//     private List<News> cleanAndTransformNews(News rawData) {

//     }

//     private List<Trend> cleanAndTransformTrends(Trend rawData) {
    
//     }
// }
