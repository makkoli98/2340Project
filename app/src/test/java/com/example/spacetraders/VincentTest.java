package com.example.spacetraders;

import com.example.spacetraders.data.entity.Resources;
import com.example.spacetraders.data.entity.ShipType;
import com.example.spacetraders.data.entity.Spaceship;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VincentTest {
    Spaceship ship;

    @Before
    public void setup() {
        ship = new Spaceship(ShipType.GNAT);
    }

    @Test
    public void testSetResource1() {
        int[] resourceAmounts = new int[Resources.values().length];
        resourceAmounts[Resources.NARCOTICS.ordinal()] = 17;
        resourceAmounts[Resources.FIREARMS.ordinal()] = 1;
        ship.setResource(resourceAmounts, true);

        assertEquals(17, ship.getCurrentResources()[Resources.NARCOTICS.ordinal()]);
        assertEquals(1, ship.getCurrentResources()[Resources.FIREARMS.ordinal()]);
    }

    @Test
    public void testSetFuel1() {
        assertTrue(ship.setFuel(17));
        assertEquals(17, ship.getFuel());
    }

    @Test
    public void testSetFuel2() {
        assertFalse(ship.setFuel(-10));
        assertEquals(0, ship.getFuel());
    }
}
