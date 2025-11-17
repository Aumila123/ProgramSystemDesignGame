package com.example.miestomeras.model;

import com.example.miestomeras.game.City;

public abstract class Building {

    private String name;
    private int cost;
    private int maintenanceCost;
    private String description;

    public Building(String name, int cost, int maintenanceCost, String description) {
        this.name = name;
        this.cost = cost;
        this.maintenanceCost = maintenanceCost;
        this.description = description;
    }

    public abstract void applyEffect(City city);

    public String getInfo() {
        return name + " - Cost: €" + cost +
                ", Maintenance: €" + maintenanceCost + "/turn\n" +
                description;
    }

    // Getters
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