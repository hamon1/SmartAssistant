package com.example.smart_assistant.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse {
    @JsonProperty("location")
    private Location location;

    @JsonProperty("current")
    private Current current;

    public static class Location {
        @JsonProperty("name")
        private String name;

        @JsonProperty("region")
        private String region;

        @JsonProperty("country")
        private String country;

        @JsonProperty("lat")
        private double lat;

        @JsonProperty("lon")
        private double lon;

    }

    public static class Current {
        @JsonProperty("temp_c")
        private double temp_c;

        @JsonProperty("wind_kph")
        private double wind_kph;

        @JsonProperty("humidity")
        private int humidity;

        @JsonProperty("condition")
        private String condition;

    }
}
