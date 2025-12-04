package com.example.miestomeras.event;

import com.example.miestomeras.game.City;
import java.util.Random;

public class EventManager {

    private static final int EVENT_CHANCE = 30;
    private Random random;

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
            case 5:
                return new EconomicCrisis();
            default:
                return null;
        }
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