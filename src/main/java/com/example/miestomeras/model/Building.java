package com.example.miestomeras.model;

import com.example.miestomeras.game.City;

public abstract class Building {

    private final String name;
    private final int cost;
    private final int maintenanceCost;
    private final String description;

    public Building(String name, int cost, int maintenanceCost, String description) {
        this.name = name;
        this.cost = cost;
        this.maintenanceCost = maintenanceCost;
        this.description = description;
    }

    public abstract void applyEffect(City city);

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getMaintenanceCost() {
        return maintenanceCost;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name;
    }
}