package com.tbisiar.bckt.domain;

/**
 * Created by tbis163 on 6/02/17.
 */
public class Restriction {

    private final String tide; //TODO: make this an enum
    private final String season; //TODO: make this an enum
    private final String timeOfDay; //TODO: make this an enum
    private final String weather; //TODO: make this an enum
    private final String duration; //TODO: make this an enum

    public Restriction(String tide, String season, String timeOfDay, String weather, String duration) {
        this.tide = tide;
        this.season = season;
        this.timeOfDay = timeOfDay;
        this.weather = weather;
        this.duration = duration;
    }

    public String getTide() {
        return tide;
    }

    public String getSeason() {
        return season;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public String getWeather() {
        return weather;
    }

    public String getDuration() {
        return duration;
    }
}

