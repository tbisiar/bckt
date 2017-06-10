package com.tbisiar.bckt.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by tbis163 on 6/02/17.
 */
@Entity
public class Location extends MongoObject {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String region;
    private double latitude;
    private double longitude;
    private String streetAddress;
    private String approach;
    private boolean isVerified;

    // This constructor is required for JPA
    protected Location(){}

    public Location(String region, double latitude, double longitude, String streetAddress, String approach, boolean isVerified) {
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
        this.streetAddress = streetAddress;
        this.approach = approach;
        this.isVerified = isVerified;
    }

    public long getId() { return id; }

    public String getRegion() {
        return region;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getApproach() {
        return approach;
    }

    public boolean isVerified() {
        return isVerified;
    }
}
