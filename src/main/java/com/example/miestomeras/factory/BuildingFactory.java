package com.example.miestomeras.factory;

import com.example.miestomeras.model.*;

public class BuildingFactory {

    // Enum to define building types
    public enum BuildingType {
        HOSPITAL,
        SCHOOL,
        POLICE_STATION,
        PARK,
        PERSONS_HOME,
        MARKET
    }

    // Factory method to create buildings
    public static Building createBuilding(BuildingType type) {
        switch (type) {
            case HOSPITAL:
                return new Hospital();
            case SCHOOL:
                return new School();
            case POLICE_STATION:
                return new PoliceStation();
            case PARK:
                return new Park();
            case PERSONS_HOME:
                return new PersonsHome();
            case MARKET:
                return new Market();
            default:
                throw new IllegalArgumentException("Unknown building type: " + type);
        }
    }

    // Optional: Method to get all available building types
    public static BuildingType[] getAvailableTypes() {
        return BuildingType.values();
    }

    // Optional: Method to get building info without creating it
    public static String getBuildingInfo(BuildingType type) {
        Building building = createBuilding(type);
        return building.getInfo();
    }
}