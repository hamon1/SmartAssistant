package com.example.smart_assistant.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Astro {
    private String sunrise;
    private String sunset;
    private String moonrise;
    private String moonset;
    private String moon_phase;
    private String moon_illumination;

    // Getters and Setters
    public String getSunrise() {
        return sunrise;
    }
    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }
    public String getSunset() {
        return sunset;
    }
    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
    public String getMoonrise() {
        return moonrise;
    }
    public void setMoonrise(String moonrise) {
        this.moonrise = moonrise;
    }
    public String getMoon_phase() {
        return moon_phase;
    }
    public void setMoon_phase(String moon_phase) {
        this.moon_phase = moon_phase;
    }
    public String getMoonset() {
        return moonset;
    }
    public void setMoonset(String moonset) {
        this.moonset = moonset;
    }
    public String getMoon_illumination() {
        return moon_illumination;
    }
    public void setMoon_illumination(String moon_illumination) {
        this.moon_illumination = moon_illumination;
    }

}
