package com.tbisiar.bckt.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bucket extends MongoObject {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String content;
    private Set<Drop> drops;

    // This constructor is required for JPA
    protected Bucket(){}

    // TODO: Rework to allow minimal constructor with only required attributes
    public Bucket(String content, Set<Drop> drops) {
        this.content = content;
        this.drops = drops;
    }

    public long getId() {
        return id;
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
