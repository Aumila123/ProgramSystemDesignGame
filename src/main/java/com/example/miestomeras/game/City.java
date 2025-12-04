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
    private int happiness;  // 0-100
    private int safety;     // 0-100
    private int environment; // 0-100
    private int turnNumber;

    private List<Building> buildings;

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

    public void increaseTaxes() {
        int taxIncome = (int)(population * 50);
        budget += taxIncome;
        happiness -= 30;
        if (happiness < 0) happiness = 0;
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
        StringBuilder sb = new StringBuilder();
        sb.append("=== TURN ").append(turnNumber).append(" / ").append(WIN_TURNS).append(" ===\n");
        sb.append("Population: ").append(population).append("\n");
        sb.append("Budget: €").append(budget).append("\n");
        sb.append("Happiness: ").append(happiness).append("%\n");
        sb.append("Safety: ").append(safety).append("%\n");
        sb.append("Environment: ").append(environment).append("%\n");
        sb.append("Buildings: ").append(buildings.size()).append("\n");
        return sb.toString();
    }

    public void displayStatus() {
        System.out.println("\n" + getStatus());
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
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

    public void setSafety(int safety) {
        this.safety = safety;
        if (this.safety > 100) this.safety = 100;
        if (this.safety < 0) this.safety = 0;
    }

    public int getEnvironment() {
        return environment;
    }

    public void setEnvironment(int environment) {
        this.environment = environment;
        if (this.environment > 100) this.environment = 100;
        if (this.environment < 0) this.environment = 0;
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
