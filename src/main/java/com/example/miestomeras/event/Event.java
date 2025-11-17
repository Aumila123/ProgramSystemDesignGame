package com.example.miestomeras.event;

import com.example.miestomeras.game.City;

public abstract class Event {
    private String name;
    private String description;

    public Event(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void applyEffect(City city);

    public String getMessage() {
        return "⚠️ EVENT: " + name + "\n" + description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}