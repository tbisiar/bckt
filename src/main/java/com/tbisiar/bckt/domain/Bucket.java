package com.tbisiar.bckt.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Document(collection = "bucket")
public class Bucket extends MongoObject {

    @OneToMany
    private Set<Drop> drops = new HashSet<>();

    // This constructor is required for JPA
    protected Bucket() {
    }

    public Bucket(String title, String description, Set<Drop> drops, String owner) {
        super(title, description, owner);
        this.drops = drops;
    }

    public Set<Drop> getDrops() {
        return drops;
    }

    public void addDrop(Drop drop) {
        drops.add(drop);
    }

    public void removeDrop(Drop drop) {
        drops.remove(drop);
    }

    @Override
    public String toString() {
        return "Bucket: {" +
                " \r\n id: " + id +
                ", \r\n title: " + title +
                ", \r\n description: " + description +
                ", \r\n owner: " + owner +
                ", \r\n drops: " + drops +
                " \r\n }";
    }
}
