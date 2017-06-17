package com.tbisiar.bckt.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Document(collection = "drop")
public class Drop extends MongoObject {

    @OneToMany
    private Set<Photo> photo;
    @OneToOne
    private Location location;
    @ManyToOne
    private DropType dropType;
    @OneToMany
    private Set<Restriction> restrictions;

    // This constructor is required for JPA
    protected Drop() {
    }

    public Drop(String title, String description, Set<Photo> photo, Location location, DropType dropType, Set<Restriction> restrictions) {
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.location = location;
        this.dropType = dropType;
        this.restrictions = restrictions;
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
