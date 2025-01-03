// package com.example.smart_assistant.model;


// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.fasterxml.jackson.annotation.JsonProperty;

// @JsonIgnoreProperties(ignoreUnknown = true) // 응답 데이터에 정의되지 않은 필드는 무시
// public class News {

//     @JsonProperty("source")
//     private Source source;

//     @JsonProperty("author")
//     private String author;

//     @JsonProperty("title")
//     private String title;

//     @JsonProperty("description")
//     private String description;

//     @JsonProperty("url")
//     private String url;

//     @JsonProperty("urlToImage")
//     private String urlToImage;

//     @JsonProperty("publishedAt")
//     private String publishedAt;

//     @JsonProperty("content")
//     private String content;

//     // 내부 Source 클래스 정의
//     @JsonIgnoreProperties(ignoreUnknown = true)
//     public static class Source {
//         @JsonProperty("id")
//         private String id;

//         @JsonProperty("name")
//         private String name;

//         // Getters and Setters
//         public String getId() {
//             return id;
//         }

//         public void setId(String id) {
//             this.id = id;
//         }

//         public String getName() {
//             return name;
//         }

//         public void setName(String name) {
//             this.name = name;
//         }
//     }

//     // Getters and Setters
//     public Source getSource() {
//         return source;
//     }

//     public void setSource(Source source) {
//         this.source = source;
//     }

//     public String getAuthor() {
//         return author;
//     }

//     public void setAuthor(String author) {
//         this.author = author;
//     }

//     public String getTitle() {
//         return title;
//     }

//     public void setTitle(String title) {
//         this.title = title;
//     }

//     public String getDescription() {
//         return description;
//     }

//     public void setDescription(String description) {
//         this.description = description;
//     }

//     public String getUrl() {
//         return url;
//     }

//     public void setUrl(String url) {
//         this.url = url;
//     }

//     public String getUrlToImage() {
//         return urlToImage;
//     }

//     public void setUrlToImage(String urlToImage) {
//         this.urlToImage = urlToImage;
//     }

//     public String getPublishedAt() {
//         return publishedAt;
//     }

//     public void setPublishedAt(String publishedAt) {
//         this.publishedAt = publishedAt;
//     }

//     public String getContent() {
//         return content;
//     }

//     public void setContent(String content) {
//         this.content = content;
//     }
// }