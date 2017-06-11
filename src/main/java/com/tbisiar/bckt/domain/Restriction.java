package com.tbisiar.bckt.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;

@Entity
@Document(collection ="restriction")
public class Restriction extends MongoObject {

    private String tide; //TODO: make this an enum
    private String season; //TODO: make this an enum
    private String timeOfDay; //TODO: make this an enum
    private String weather; //TODO: make this an enum
    private String duration; //TODO: make this an enum

    protected Restriction(){}

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

