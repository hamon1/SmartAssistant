package com.example.smart_assistant.model;


// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.fasterxml.jackson.annotation.JsonProperty;
// import java.util.List;


// @JsonIgnoreProperties(ignoreUnknown = true)
// public class ResponseWrapper {

//     @JsonProperty("articles")
//     private List<News> articles;

//     public List<News> getArticles() {
//         return articles;
//     }

//     public void setArticles(List<News> articles) {
//         this.articles = articles;
//     }
// }


import java.util.List;

public class ResponseWrapper {
    private String status;
    private int totalResults;
    private List<Article> articles;

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
