package com.example.miestomeras.game;

import com.example.miestomeras.model.Hospital;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class CityTest {

    private City city;

    @BeforeEach
    public void setUp() {
        city = new City();
    }

    @Test
    public void testCityInitialization() {
        assertEquals(7000, city.getPopulation());
        assertEquals(8000000, city.getBudget());
        assertEquals(80, city.getHappiness());
        assertEquals(70, city.getSafety());
        assertEquals(75, city.getEnvironment());
        assertEquals(1, city.getTurnNumber());
    }

    @Test
    public void testAddBuildingSuccess() {
        Hospital hospital = new Hospital();
        int initialBudget = city.getBudget();

        boolean result = city.addBuilding(hospital);

        assertTrue(result);
        assertEquals(initialBudget - hospital.getCost(), city.getBudget());
        assertEquals(1, city.getBuildings().size());
    }

    @Test
    public void testAddBuildingInsufficientBudget() {
        city.setBudget(100000); // Set budget too low
        Hospital hospital = new Hospital();

        boolean result = city.addBuilding(hospital);

        assertFalse(result);
        assertEquals(0, city.getBuildings().size());
    }

    @Test
    public void testCheckWinCondition() {
        city.setBudget(5000000);
        city.setHappiness(50);

        // Simulate passing 10 turns
        for (int i = 0; i < 10; i++) {
            city.endTurn();
        }

        assertTrue(city.checkWin());
    }

    @Test
    public void testCheckLoseConditionBankrupt() {
        city.setBudget(0);
        assertTrue(city.checkLose());
    }

    @Test
    public void testCheckLoseConditionLowHappiness() {
        city.setHappiness(20);
        assertTrue(city.checkLose());
    }
}