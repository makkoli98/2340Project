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
    public void testSetResource2() {
        int[] resourceAmounts = new int[Resources.values().length];
        for (int i : resourceAmounts) {
            i = 10;
        }
        ship.setResource(resourceAmounts, true);

        resourceAmounts = new int[Resources.values().length];
        resourceAmounts[Resources.FOOD.ordinal()] = 5;
        resourceAmounts[Resources.WATER.ordinal()] = 7;
        ship.setResource(resourceAmounts, false);

        for (int i = 0; i < resourceAmounts.length; i++) {
            if (i == Resources.FOOD.ordinal()) {
                assertEquals(5, ship.getCurrentResources()[i]);
            } else if (i == Resources.WATER.ordinal()) {
                assertEquals(7, ship.getCurrentResources()[i]);
            } else {
                assertEquals(10, ship.getCurrentResources()[i]);
            }
        }
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

    @Test
    public void testSetFuel3() {
        assertFalse(ship.setFuel(101));
        assertEquals(0, ship.getFuel());
    }
}
