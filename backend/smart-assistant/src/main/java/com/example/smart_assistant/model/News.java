package com.example.smart_assistant.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class News {
    private String url;
    private String urlMobile;
    private String title;
    private String seendate;
    private String socialimage;
    private String domain;
    private String language;
    private String sourcecountry;

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("url_mobile")
    public String getUrlMobile() {
        return urlMobile;
    }

    public void setUrlMobile(String urlMobile) {
        this.urlMobile = urlMobile;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("seendate")
    public String getSeendate() {
        return seendate;
    }

    public void setSeendate(String seendate) {
        this.seendate = seendate;
    }

    @JsonProperty("socialimage")
    public String getSocialimage() {
        return socialimage;
    }

    public void setSocialimage(String socialimage) {
        this.socialimage = socialimage;
    }

    @JsonProperty("domain")
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("sourcecountry")
    public String getSourcecountry() {
        return sourcecountry;
    }

    public void setSourcecountry(String sourcecountry) {
        this.sourcecountry = sourcecountry;
    }
}