package com.example.miestomeras.event;

import com.example.miestomeras.game.City;

public class EconomicCrisis extends Event {

    private static final int BUDGET_DECREASE = 500000;
    private static final int HAPPINESS_INCREASE = 40;

    public EconomicCrisis() {
        super(
                "Economic Crisis!",
                "The economy is in crisis!\nBudget -€500,000, Happiness -40%"
        );
    }

    @Override
    public void applyEffect(City city) {
        city.modifyBudget(-BUDGET_DECREASE);
        city.modifyHappiness(-HAPPINESS_INCREASE);
    }
}