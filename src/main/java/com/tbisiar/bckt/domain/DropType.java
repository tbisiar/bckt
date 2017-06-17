package com.tbisiar.bckt.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Document(collection = "dropType")
public class DropType extends MongoObject {

    public static final String RESTAURANT = "Restaurant";
    public static final String ACTIVITY = "Activity";
    public static final String EVENT = "Event";

    private String type;
    @ManyToOne
    private Equipment equipment;
    private int effortLevel;
    private int skillLevel;

    // This constructor is required for JPA
    protected DropType() {
    }

    public DropType(String type, Equipment equipment, int effortLevel, int skillLevel) {
        this.type = type;
        this.equipment = equipment;
        this.effortLevel = effortLevel;
        this.skillLevel = skillLevel;
    }

    public String getType() {
        return type;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public int getEffortLevel() {
        return effortLevel;
    }

    public int getSkillLevel() {
        return skillLevel;
    }
}
