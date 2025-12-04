package com.example.miestomeras.model;

import com.example.miestomeras.game.City;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class BuildingTest {

    private City city;

    @BeforeEach
    public void setUp() {
        city = new City();
    }

    @Test
    public void testHospitalEffect() {
        int initialHappiness = city.getHappiness();
        int initialSafety = city.getSafety();

        Hospital hospital = new Hospital();
        hospital.applyEffect(city);

        assertEquals(initialHappiness + 5, city.getHappiness());
        assertEquals(initialSafety + 3, city.getSafety());
    }

    @Test
    public void testSchoolEffect() {
        int initialHappiness = city.getHappiness();

        School school = new School();
        school.applyEffect(city);

        assertEquals(initialHappiness + 8, city.getHappiness());
    }

    @Test
    public void testParkEffect() {
        int initialHappiness = city.getHappiness();
        int initialEnvironment = city.getEnvironment();

        Park park = new Park();
        park.applyEffect(city);

        assertEquals(initialHappiness + 5, city.getHappiness());
        assertEquals(initialEnvironment + 10, city.getEnvironment());
    }

    @Test
    public void testMarketEffect() {
        int initialBudget = city.getBudget();
        int initialHappiness = city.getHappiness();

        Market market = new Market();
        market.applyEffect(city);

        assertTrue(city.getBudget() > initialBudget);
        assertEquals(initialHappiness + 3, city.getHappiness());
    }
}