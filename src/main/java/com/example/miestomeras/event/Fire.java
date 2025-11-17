package com.example.miestomeras.event;

import com.example.miestomeras.game.City;

public class Fire extends Event {

    private static final int SAFETY_DECREASE = 15;
    private static final int BUDGET_DECREASE = 200000;

    public Fire() {
        super(
                "Fire!",
                "A major fire broke out in the city!\nSafety -15%, Emergency costs: €200,000"
        );
    }

    @Override
    public void applyEffect(City city) {
        city.modifySafety(-SAFETY_DECREASE);
        city.modifyBudget(-BUDGET_DECREASE);
    }
}