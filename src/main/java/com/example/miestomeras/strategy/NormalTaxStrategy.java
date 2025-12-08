package com.example.miestomeras.strategy;

import com.example.miestomeras.game.City;

public class NormalTaxStrategy implements TaxStrategy {

    private static final int TAX_PER_PERSON = 50;
    private static final int HAPPINESS_PENALTY = 10;

    @Override
    public void applyTax(City city) {
        int taxIncome = city.getPopulation() * TAX_PER_PERSON;
        city.modifyBudget(taxIncome);
        city.modifyHappiness(-HAPPINESS_PENALTY);
    }

    @Override
    public String getDescription() {
        return "Normal Tax (€" + TAX_PER_PERSON + " per person, -" + HAPPINESS_PENALTY + "% happiness)";
    }

    @Override
    public int getExpectedIncome(City city) {
        return city.getPopulation() * TAX_PER_PERSON;
    }
}