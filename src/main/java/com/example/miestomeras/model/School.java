package com.example.miestomeras.model;

import com.example.miestomeras.game.City;

public class School extends Building {

    private static final int HAPPINESS_BOOST = 8;

    public School() {
        super(
                "School",
                1000000,
                30000,
                "Increases happiness +8%"
        );
    }

    @Override
    public void applyEffect(City city) {
        city.modifyHappiness(HAPPINESS_BOOST);
    }

}
