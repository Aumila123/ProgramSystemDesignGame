package com.example.miestomeras.model;

import com.example.miestomeras.game.City;

public class PersonsHome extends Building{

    private static final int POPULATION_BOOST = 500;

    public PersonsHome() {
        super(
                "Persons Home",
                500000,
                15000,
                "Increases population capacity +500"
        );
    }

    @Override
    public void applyEffect(City city) {
        city.modifyPopulation(POPULATION_BOOST);
    }

}
