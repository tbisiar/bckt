package com.tbisiar.bckt.domain;

import java.util.Set;

public class Drop {

    private final long id;
    private final String title;
    private final String description;
    private final Set<Photo> photo;
    private final Location location;
    private final DropType dropType;
    private final Set<Restriction> restrictions;

    public Drop(long id, String title, String description, Set<Photo> photo, Location location, DropType dropType, Set<Restriction> restrictions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.location = location;
        this.dropType = dropType;
        this.restrictions = restrictions;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Set<Photo> getPhoto() {
        return photo;
    }

    public Location getLocation() {
        return location;
    }

    public DropType getDropType() {
        return dropType;
    }

    public Set<Restriction> getRestrictions() {
        return restrictions;
    }
}
