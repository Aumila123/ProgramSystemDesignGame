package com.example.miestomeras.strategy;

import com.example.miestomeras.game.City;

public interface TaxStrategy {

    // Apply tax strategy to city
    void applyTax(City city);

    // Get description of this strategy
    String getDescription();

    // Get expected income
    int getExpectedIncome(City city);

    // Get happiness penalty
    int getHappinessPenalty();
}