package com.example.miestomeras.strategy;

import com.example.miestomeras.game.City;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class TaxStrategyTest {

    private City city;

    @BeforeEach
    public void setUp() {
        city = new City();
    }

    @Test
    public void testLowTaxStrategy() {
        int initialBudget = city.getBudget();
        int initialHappiness = city.getHappiness();

        TaxStrategy lowTax = new LowTaxStrategy();
        city.applyTaxStrategy(lowTax);

        int expectedIncome = city.getPopulation() * 25;
        assertTrue(city.getBudget() > initialBudget);
        assertEquals(initialHappiness - 3, city.getHappiness());
    }

    @Test
    public void testNormalTaxStrategy() {
        int initialBudget = city.getBudget();
        int initialHappiness = city.getHappiness();

        TaxStrategy normalTax = new NormalTaxStrategy();
        city.applyTaxStrategy(normalTax);

        assertTrue(city.getBudget() > initialBudget);
        assertEquals(initialHappiness - 10, city.getHappiness());
    }

    @Test
    public void testAggressiveTaxStrategy() {
        int initialBudget = city.getBudget();
        int initialHappiness = city.getHappiness();

        TaxStrategy aggressiveTax = new AggressiveTaxStrategy();
        city.applyTaxStrategy(aggressiveTax);

        assertTrue(city.getBudget() > initialBudget);
        assertEquals(initialHappiness - 30, city.getHappiness());
    }

    @Test
    public void testStrategyExpectedIncome() {
        TaxStrategy lowTax = new LowTaxStrategy();

        int expectedIncome = lowTax.getExpectedIncome(city);
        assertEquals(7000 * 25, expectedIncome);
    }
}