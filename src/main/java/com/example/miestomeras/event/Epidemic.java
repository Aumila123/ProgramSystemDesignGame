package com.example.miestomeras.event;

import com.example.miestomeras.game.City;

public class Epidemic extends Event {

    private static final int POPULATION_DECREASE = 5;
    private static final int HAPPINESS_DECREASE = 10;
    private static final int BUDGET_DECREASE = 300000;

    public Epidemic() {
        super(
                "EPIDEMIC!",
                "A disease is spreading through the city!\nPopulation -5%, Happiness -10%, Medical costs: €300,000"
        );
    }

    @Override
    public void applyEffect(City city) {
        city.modifyPopulation(-POPULATION_DECREASE);
        city.modifyHappiness(-HAPPINESS_DECREASE);
        city.modifyBudget(-BUDGET_DECREASE);
    }
}