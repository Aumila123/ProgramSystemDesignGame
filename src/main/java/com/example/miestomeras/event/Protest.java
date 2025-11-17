package com.example.miestomeras.event;

import com.example.miestomeras.game.City;

public class Protest extends Event {

    private static final int HAPPINESS_DECREASE = 10;

    public Protest() {
        super(
                "Protest!",
                "Citizens are protesting in the streets!\nHappiness -10%"
        );
    }

    @Override
    public void applyEffect(City city) {
        city.modifyHappiness(-HAPPINESS_DECREASE);
    }
}