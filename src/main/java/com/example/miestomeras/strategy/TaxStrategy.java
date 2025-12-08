package com.example.miestomeras.strategy;

import com.example.miestomeras.game.City;

public interface TaxStrategy {

    void applyTax(City city);

    String getDescription();

    int getExpectedIncome(City city);

}