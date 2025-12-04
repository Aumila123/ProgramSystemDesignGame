package com.example.miestomeras.factory;

import com.example.miestomeras.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BuildingFactoryTest {

    @Test
    public void testCreateHospital() {
        Building building = BuildingFactory.createBuilding(BuildingFactory.BuildingType.HOSPITAL);

        assertNotNull(building);
        assertTrue(building instanceof Hospital);
        assertEquals("Hospital", building.getName());
        assertEquals(1500000, building.getCost());
    }

    @Test
    public void testCreateSchool() {
        Building building = BuildingFactory.createBuilding(BuildingFactory.BuildingType.SCHOOL);

        assertNotNull(building);
        assertTrue(building instanceof School);
        assertEquals("School", building.getName());
    }

    @Test
    public void testCreatePoliceStation() {
        Building building = BuildingFactory.createBuilding(BuildingFactory.BuildingType.POLICE_STATION);

        assertNotNull(building);
        assertTrue(building instanceof PoliceStation);
        assertEquals("Police Station", building.getName());
    }

    @Test
    public void testGetAvailableTypes() {
        BuildingFactory.BuildingType[] types = BuildingFactory.getAvailableTypes();

        assertNotNull(types);
        assertEquals(6, types.length);
    }
}