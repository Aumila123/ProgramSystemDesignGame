package com.example.miestomeras.event;

import com.example.miestomeras.game.City;

public class Festival extends Event {

    private static final int HAPPINESS_INCREASE = 8;
    private static final int BUDGET_DECREASE = 50000;

    public Festival() {
        super(
                "City Festival!",
                "A successful city festival brings joy to citizens!\nHappiness +8%, Event costs: €50,000"
        );
    }

    @Override
    public void applyEffect(City city) {
        city.modifyHappiness(HAPPINESS_INCREASE);
        city.modifyBudget(-BUDGET_DECREASE);
    }
}