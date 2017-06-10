package com.tbisiar.bckt.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by tbis163 on 6/02/17.
 */
@Entity
public class Restriction extends MongoObject {

    @Id
    @GeneratedValue
    private long id;
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

    public long getId() { return id; }

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

