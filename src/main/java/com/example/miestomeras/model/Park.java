package com.example.miestomeras.model;

import com.example.miestomeras.game.City;

public class Park extends Building{

    private static final int HAPPINESS_BOOST = 5;
    private static final int ENVIRONMENT_BOOST = 10;

    public Park() {
        super(
                "Park",
                300000,
                10000,
                "Increases environment +10%, happiness +5%"
        );
    }

    @Override
    public void applyEffect(City city) {
        city.modifyHappiness(HAPPINESS_BOOST);
        city.modifyEnvironment(ENVIRONMENT_BOOST);
    }

}
