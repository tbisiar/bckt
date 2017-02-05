package com.tbisiar.bckt.domain;

/**
 * Created by tbis163 on 6/02/17.
 */
public class Location {

    private final String region;
    private final double latitude;
    private final double longitude;
    private final String streetAddress;
    private final String approach;
    private final boolean isVerified;

    public Location(String region, double latitude, double longitude, String streetAddress, String approach, boolean isVerified) {
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
        this.streetAddress = streetAddress;
        this.approach = approach;
        this.isVerified = isVerified;
    }

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
