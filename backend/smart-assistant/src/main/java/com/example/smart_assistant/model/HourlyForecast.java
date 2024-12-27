package com.example.smart_assistant.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HourlyForecast {
    private String time;
    private double temp_c;
    private int is_day;
    private Condition condition;

    // Getters and Setters
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
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

}
