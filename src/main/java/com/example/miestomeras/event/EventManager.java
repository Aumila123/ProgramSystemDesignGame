package com.example.miestomeras.event;

import com.example.miestomeras.game.City;
import java.util.Random;

public class EventManager {

    private static final int EVENT_CHANCE = 30; // 30% chance per turn
    private Random random;

    public EventManager() {
        this.random = new Random();
    }

    // Check if an event should occur this turn
    public Event checkForEvent() {
        if (random.nextInt(100) < EVENT_CHANCE) {
            return generateRandomEvent();
        }
        return null; // No event this turn
    }

    // Generate a random event
    private Event generateRandomEvent() {
        int eventType = random.nextInt(5); // 5 different event types

        switch (eventType) {
            case 0:
                return new Fire();
            case 1:
                return new Protest();
            case 2:
                return new EconomicBoom();
            case 3:
                return new Epidemic();
            case 4:
                return new Festival();
            default:
                return null;
        }
    }

    // Apply event to city and return message
    public String triggerEvent(City city) {
        Event event = checkForEvent();

        if (event != null) {
            event.applyEffect(city);
            return event.getMessage();
        }

        return null; // No event occurred
    }
}