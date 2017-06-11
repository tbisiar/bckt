package com.tbisiar.bckt.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Document(collection ="dropType")
public class DropType extends MongoObject {

    public static final String RESTAURANT = "Restaurant";
    public static final String ACTIVITY = "Activity";
    public static final String EVENT = "Event";

    // Todo: make this a configurable list
    // i.e. - Restaurant, Activity, Event, etc.
    private String type;
    @ManyToOne
    private Equipment equipment;
    private int effortLevel; //TODO: make this an enum
    private int skillLevel; //TODO: make this an enum

    // This constructor is required for JPA
    protected DropType(){}

    public DropType(String type, Equipment equipment, int effortLevel, int skillLevel) {
        // TODO: add check on String 'type'
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
