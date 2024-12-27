package com.example.smart_assistant.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Current {
    private double temp_c;
    private int is_day;
    private Condition condition;
    private double wind_kph;
    private double wind_mph;
    private double wind_degree;
    private int humidity;
    private int cloud;
    private double feelslike_c;
    private double windchill_c;
    private double heatindex_c;
    private double dewpoint_c;
    private double vis_k;
    private double uv;
    private double gust_kph;

    // Getters and Setters
    public double getTemp_c() {
        return temp_c;
    }
    public void setTemp_c(double temp_c) {
        this.temp_c = temp_c;
    }
    public int getIs_day() {
        return is_day;
    }
    public void setIs_day(int is_day) {
        this.is_day = is_day;
    }
    public Condition getCondition() {
        return condition;
    }
    public void setCondition(Condition condition) {
        this.condition = condition;
    }
    public double getWind_kph() {
        return wind_kph;
    }
    public void setWind_kph(double wind_kph) {
        this.wind_kph = wind_kph;
    }
    public double getWind_mph() {
        return wind_mph;
    }
    public void setWind_mph(double wind_mph) {
        this.wind_mph = wind_mph;
    }
    public double getWind_degree() {
        return wind_degree;
    }
    public void setWind_degree(double wind_degree) {
        this.wind_degree = wind_degree;
    }
    public int getHumidity() {
        return humidity;
    }
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
    public int getCloud() {
        return cloud;
    }
    public void setCloud(int cloud) {
        this.cloud = cloud;
    }
    public double getFeelslike_c() {
        return feelslike_c;
    }
    public void setFeelslike_c(double feelslike_c) {
        this.feelslike_c = feelslike_c;
    }
    public double getWindchill_c() {
        return windchill_c;
    }
    public void setWindchill_c(double windchill_c) {
        this.windchill_c = windchill_c;
    }
    public double getHeatindex_c() {
        return heatindex_c;
    }
    public void setHeatindex_c(double heatindex_c) {
        this.heatindex_c = heatindex_c;
    }
    public double getDewpoint_c() {
        return dewpoint_c;
    }
    public void setDewpoint_c(double dewpoint_c) {
        this.dewpoint_c = dewpoint_c;
    }
    public double getVis_k() {
        return vis_k;
    }
    public void setVis_k(double vis_k) {
        this.vis_k = vis_k;
    }
    public double getUv() {
        return uv;
    }
    public void setUv(double uv) {
        this.uv = uv;
    }
    public double getGust_kph() {
        return gust_kph;
    }
    public void setGust_kph(double gust_kph) {
        this.gust_kph = gust_kph;
    }
    
}
