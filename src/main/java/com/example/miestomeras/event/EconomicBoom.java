package com.example.miestomeras.event;

import com.example.miestomeras.game.City;

public class EconomicBoom extends Event {

    private static final int BUDGET_INCREASE = 500000;
    private static final int HAPPINESS_INCREASE = 5;

    public EconomicBoom() {
        super(
                "Economic Boom!",
                "The economy is thriving! Tourism and business are up!\nBudget +€500,000, Happiness +5%"
        );
    }

    @Override
    public void applyEffect(City city) {
        city.modifyBudget(BUDGET_INCREASE);
        city.modifyHappiness(HAPPINESS_INCREASE);
    }
}