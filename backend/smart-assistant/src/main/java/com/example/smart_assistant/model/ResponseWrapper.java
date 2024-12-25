package com.example.smart_assistant.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ResponseWrapper {
    private List<News> articles;

    @JsonProperty("articles")
    public List<News> getArticles() {
        return articles;
    }

    public void setArticles(List<News> articles) {
        this.articles = articles;
    }
}
