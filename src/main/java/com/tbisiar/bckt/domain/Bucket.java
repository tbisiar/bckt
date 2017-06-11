package com.tbisiar.bckt.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Bucket extends MongoObject {

    private String content;
    @OneToMany
    private Set<Drop> drops = new HashSet<>();

    // This constructor is required for JPA
    protected Bucket(){}

    public Bucket(String content, Set<Drop> drops) {
        this.content = content;
        this.drops = drops;
    }

    public String getContent() {
        return content;
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

}
