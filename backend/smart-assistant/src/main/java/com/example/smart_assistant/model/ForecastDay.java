package com.example.smart_assistant.model;
import java.util.List;

public class ForecastDay {
    private String date;
    private Day day;
    private Astro astro;
    private List<HourlyForecast> hour;
    private long date_epoch;

    // Getters and Setters
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public Day getDay() {
        return day;
    }
    public void setDay(Day day) {
        this.day = day;
    }
    public Astro getAstro() {
        return astro;
    }
    public void setAstro(Astro astro) {
        this.astro = astro;
    }
    public List<HourlyForecast> getHour() {
        return hour;
    }
    public void setHour(List<HourlyForecast> hour) {
        this.hour = hour;
    }
    public long getDate_epoch() {
        return date_epoch;
    
    }
    public void setDate_epoch(long date_epoch) {
        this.date_epoch = date_epoch;
    }
}
