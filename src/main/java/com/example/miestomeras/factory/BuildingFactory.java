package com.example.miestomeras.factory;

import com.example.miestomeras.model.*;

public class BuildingFactory {

    public enum BuildingType {
        HOSPITAL,
        SCHOOL,
        POLICE_STATION,
        PARK,
        PERSONS_HOME,
        MARKET
    }

    public static Building createBuilding(BuildingType type) {
        return switch (type) {
            case HOSPITAL -> new Hospital();
            case SCHOOL -> new School();
            case POLICE_STATION -> new PoliceStation();
            case PARK -> new Park();
            case PERSONS_HOME -> new PersonsHome();
            case MARKET -> new Market();
        };
    }

    public static BuildingType[] getAvailableTypes() {
        return BuildingType.values();
    }
}