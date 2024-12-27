package com.example.smart_assistant.model;
import java.util.List;

public class Forecast {
    private List<ForecastDay> forecastday;

    // Getters and Setters
    public List<ForecastDay> getForecastday() {
        return forecastday;
    }
    public void setForecastday(List<ForecastDay> forecastday) {
        this.forecastday = forecastday;
    }
}
