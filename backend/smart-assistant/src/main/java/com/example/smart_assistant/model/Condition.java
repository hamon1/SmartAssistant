package com.example.smart_assistant.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Condition {
    private String text;
    private String icon;
    private String code;

    // Getters and Setters
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
