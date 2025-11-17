package com.example.miestomeras.model;

import com.example.miestomeras.game.City;

public class Market extends Building{

    private static final int HAPPINESS_BOOST = 3;
    private static final int BUDGET_BOOST = 20000;

    public Market() {
        super(
                "Market",
                600000,
                20000,
                "Increases happiness +3%, budget +€20,000"
        );
    }

    @Override
    public void applyEffect(City city) {
        city.modifyHappiness(HAPPINESS_BOOST);
        city.modifyBudget(BUDGET_BOOST);
    }

}
