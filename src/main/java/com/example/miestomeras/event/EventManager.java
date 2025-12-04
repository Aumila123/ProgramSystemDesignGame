package com.example.miestomeras.event;

import com.example.miestomeras.game.City;
import java.util.Random;

public class EventManager {

    private static final int EVENT_CHANCE = 30;
    private final Random random;

    public EventManager() {
        this.random = new Random();
    }

    public Event checkForEvent() {
        if (random.nextInt(100) < EVENT_CHANCE) {
            return generateRandomEvent();
        }
        return null;
    }

    private Event generateRandomEvent() {
        int eventType = random.nextInt(6);

        return switch (eventType) {
            case 0 -> new Fire();
            case 1 -> new Protest();
            case 2 -> new EconomicBoom();
            case 3 -> new Epidemic();
            case 4 -> new Festival();
            case 5 -> new EconomicCrisis();
            default -> null;
        };
    }

    public String triggerEvent(City city) {
        Event event = checkForEvent();

        if (event != null) {
            event.applyEffect(city);
            return event.getMessage();
        }

        return null;
    }
}