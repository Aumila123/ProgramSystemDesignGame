package com.example.miestomeras.model;

import com.example.miestomeras.game.City;

public class Hospital extends Building {

    // Constants for Hospital effects
    private static final int HAPPINESS_BOOST = 5;
    private static final int SAFETY_BOOST = 3;

    public Hospital() {
        super(
                "Hospital",
                1500000,
                50000,
                "Increases happiness +5%, safety +3%"
        );
    }

    @Override
    public void applyEffect(City city) {
        city.modifyHappiness(HAPPINESS_BOOST);
        city.modifySafety(SAFETY_BOOST);
    }
}