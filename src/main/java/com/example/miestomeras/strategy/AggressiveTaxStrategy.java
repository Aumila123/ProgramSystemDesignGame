package com.example.miestomeras.strategy;

import com.example.miestomeras.game.City;

public class AggressiveTaxStrategy implements TaxStrategy {

    private static final int TAX_PER_PERSON = 100;
    private static final int HAPPINESS_PENALTY = 30;

    @Override
    public void applyTax(City city) {
        int taxIncome = city.getPopulation() * TAX_PER_PERSON;
        city.modifyBudget(taxIncome);
        city.modifyHappiness(-HAPPINESS_PENALTY);
    }

    @Override
    public String getDescription() {
        return "Aggressive Tax (€" + TAX_PER_PERSON + " per person, -" + HAPPINESS_PENALTY + "% happiness)";
    }

    @Override
    public int getExpectedIncome(City city) {
        return city.getPopulation() * TAX_PER_PERSON;
    }

    @Override
    public int getHappinessPenalty() {
        return HAPPINESS_PENALTY;
    }
}