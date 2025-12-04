package com.example.miestomeras.game;

import com.example.miestomeras.model.Building;
import com.example.miestomeras.strategy.TaxStrategy;
import java.util.ArrayList;
import java.util.List;

public class City {

    public static final int WIN_TURNS = 10;
    public static final int STARTING_POPULATION = 7000;
    public static final int STARTING_BUDGET = 8000000;
    public static final int STARTING_HAPPINESS = 80;
    public static final int STARTING_SAFETY = 70;
    public static final int STARTING_ENVIRONMENT = 75;

    private int population;
    private int budget;
    private int happiness;
    private int safety;
    private int environment;
    private int turnNumber;

    private final List<Building> buildings;

    public City() {
        this.population = STARTING_POPULATION;
        this.budget = STARTING_BUDGET;
        this.happiness = STARTING_HAPPINESS;
        this.safety = STARTING_SAFETY;
        this.environment = STARTING_ENVIRONMENT;
        this.turnNumber = 1;
        this.buildings = new ArrayList<>();
    }

    public boolean addBuilding(Building building) {
        if (budget >= building.getCost()) {
            budget -= building.getCost();
            buildings.add(building);
            building.applyEffect(this);
            return true;
        }
        return false;
    }

    public void processMaintenance() {
        int totalMaintenance = 0;
        for (Building building : buildings) {
            totalMaintenance += building.getMaintenanceCost();
        }
        budget -= totalMaintenance;
    }

    public void applyTaxStrategy(TaxStrategy strategy) {
        strategy.applyTax(this);
    }

    public void decreaseExpenses() {
        budget += 200000;
        happiness -= 10;
        safety -= 10;
        if (happiness < 0) happiness = 0;
        if (safety < 0) safety = 0;
    }

    public void endTurn() {
        processMaintenance();
        turnNumber++;

        if (happiness > 70) {
            population += 100;
        } else if (happiness < 40) {
            population -= 50;
        }

        normalizeIndicators();
    }

    private void normalizeIndicators() {
        if (happiness > 100) happiness = 100;
        if (happiness < 0) happiness = 0;
        if (safety > 100) safety = 100;
        if (safety < 0) safety = 0;
        if (environment > 100) environment = 100;
        if (environment < 0) environment = 0;
    }

    public boolean checkWin() {
        return turnNumber > WIN_TURNS && budget > 0 && happiness > 20;
    }

    public boolean checkLose() {
        return budget <= 0 || happiness <= 20;
    }

    public String getStatus() {
        return "=== TURN " + turnNumber + " / " + WIN_TURNS + " ===\n" +
                "Population: " + population + "\n" +
                "Budget: €" + budget + "\n" +
                "Happiness: " + happiness + "%\n" +
                "Safety: " + safety + "%\n" +
                "Environment: " + environment + "%\n" +
                "Buildings: " + buildings.size() + "\n";
    }

    public void displayStatus() {
        System.out.println("\n" + getStatus());
    }

    public int getPopulation() {
        return population;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
        if (this.happiness > 100) this.happiness = 100;
        if (this.happiness < 0) this.happiness = 0;
    }

    public int getSafety() {
        return safety;
    }

    public int getEnvironment() {
        return environment;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void modifyHappiness(int amount) {
        this.happiness += amount;
        normalizeIndicators();
    }

    public void modifySafety(int amount) {
        this.safety += amount;
        normalizeIndicators();
    }

    public void modifyEnvironment(int amount) {
        this.environment += amount;
        normalizeIndicators();
    }

    public void modifyBudget(int amount) {
        this.budget += amount;
    }

    public void modifyPopulation(int amount) {
        this.population += amount;
    }
}
