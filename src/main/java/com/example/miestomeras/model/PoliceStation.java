package com.example.miestomeras.model;

import com.example.miestomeras.game.City;

public class PoliceStation extends Building {

    private static final int SAFETY_BOOST = 10;

    public PoliceStation() {
        super(
                "Police Station",
                800000,
                40000,
                "Increases safety +10%"
        );
    }

    @Override
    public void applyEffect(City city) {
        city.modifySafety(SAFETY_BOOST);
    }

}
