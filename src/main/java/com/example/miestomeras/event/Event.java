package com.example.miestomeras.event;

import com.example.miestomeras.game.City;

public abstract class Event {
    private final String name;
    private final String description;

    public Event(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void applyEffect(City city);

    public String getMessage() {
        return "⚠️ EVENT: " + name + "\n" + description;
    }

}