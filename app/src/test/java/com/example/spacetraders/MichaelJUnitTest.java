package com.example.spacetraders;

import com.example.spacetraders.data.entity.Market;
import com.example.spacetraders.data.entity.RadicalEvent;
import com.example.spacetraders.data.entity.ResourceLevel;
import com.example.spacetraders.data.entity.Resources;
import com.example.spacetraders.data.entity.TechLevel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Local unit testing for the Market class methods.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MichaelJUnitTest {
    Market market;

    @Before
    public void setup() {
        market = new Market(TechLevel.HI_TECH);
    }

    @Test
    public void changeResourceLevelTest1() {
        int[] baseResources = market.getResourcePrices();
        int testPrice = (int) (baseResources[Resources.WATER.ordinal()] * 0.8);
        market.changeResourceLevel(ResourceLevel.LOTSOFWATER);
        int newPrice = baseResources[Resources.WATER.ordinal()];
        assertEquals(testPrice, newPrice);
    }

    @Test
    public void changeResourceLevelTest2() {
        int[] baseResources = market.getResourcePrices();
        int testPrice = (int) (baseResources[Resources.FURS.ordinal()] * 0.8);
        market.changeResourceLevel(ResourceLevel.RICHFAUNA);
        int newPrice = baseResources[Resources.FURS.ordinal()];
        assertEquals(testPrice, newPrice);
    }

    @Test
    public void changeResourceLevelTest3() {
        int[] baseResources = market.getResourcePrices();
        int testPrice = (int) (baseResources[Resources.FOOD.ordinal()] * 0.8);
        market.changeResourceLevel(ResourceLevel.RICHSOIL);
        int newPrice = baseResources[Resources.FOOD.ordinal()];
        assertEquals(testPrice, newPrice);
    }

    @Test
    public void changeResourceLevelTest4() {
        int[] baseResources = market.getResourcePrices();
        int testPrice = (int) (baseResources[Resources.ORE.ordinal()] * 0.8);
        market.changeResourceLevel(ResourceLevel.MINERALRICH);
        int newPrice = baseResources[Resources.ORE.ordinal()];
        assertEquals(testPrice, newPrice);
    }

    @Test
    public void changeResourceLevelTest5() {
        int[] baseResources = market.getResourcePrices();
        int testPrice = (int) (baseResources[Resources.GAMES.ordinal()] * 0.8);
        market.changeResourceLevel(ResourceLevel.ARTISTIC);
        int newPrice = baseResources[Resources.GAMES.ordinal()];
        assertEquals(testPrice, newPrice);
    }

    @Test
    public void changeResourceLevelTest6() {
        int[] baseResources = market.getResourcePrices();
        int testPrice = (int) (baseResources[Resources.FIREARMS.ordinal()] * 0.8);
        market.changeResourceLevel(ResourceLevel.WARLIKE);
        int newPrice = baseResources[Resources.FIREARMS.ordinal()];
        assertEquals(testPrice, newPrice);
    }

    @Test
    public void changeResourceLevelTest7() {
        int[] baseResources = market.getResourcePrices();
        int testPrice = (int) (baseResources[Resources.MEDICINE.ordinal()] * 0.8);
        market.changeResourceLevel(ResourceLevel.LOTSOFHERBS);
        int newPrice = baseResources[Resources.MEDICINE.ordinal()];
        assertEquals(testPrice, newPrice);
    }

    @Test
    public void changeResourceLevelTest8() {
        int[] baseResources = market.getResourcePrices();
        int testPrice = (int) (baseResources[Resources.NARCOTICS.ordinal()] * 0.8);
        market.changeResourceLevel(ResourceLevel.WEIRDMUSHROOMS);
        int newPrice = baseResources[Resources.NARCOTICS.ordinal()];
        assertEquals(testPrice, newPrice);
    }

    @Test
    public void changeResourceLevelTest9() {
        int[] baseResources = market.getResourcePrices();
        int testPrice = (int) (baseResources[Resources.WATER.ordinal()] * 1.2);
        market.changeResourceLevel(ResourceLevel.DESERT);
        int newPrice = baseResources[Resources.WATER.ordinal()];
        assertEquals(testPrice, newPrice);
    }

    @Test
    public void changeResourceLevelTest10() {
        int[] baseResources = market.getResourcePrices();
        int testPrice = (int) (baseResources[Resources.FURS.ordinal()] * 1.2);
        market.changeResourceLevel(ResourceLevel.LIFELESS);
        int newPrice = baseResources[Resources.FURS.ordinal()];
        assertEquals(testPrice, newPrice);
    }

    @Test
    public void changeResourceLevelTest11() {
        int[] baseResources = market.getResourcePrices();
        int testPrice = (int) (baseResources[Resources.FOOD.ordinal()] * 1.2);
        market.changeResourceLevel(ResourceLevel.POORSOIL);
        int newPrice = baseResources[Resources.FOOD.ordinal()];
        assertEquals(testPrice, newPrice);
    }

    @Test
    public void changeResourceLevelTest12() {
        int[] baseResources = market.getResourcePrices();
        int testPrice = (int) (baseResources[Resources.ORE.ordinal()] * 1.2);
        market.changeResourceLevel(ResourceLevel.MINERALPOOR);
        int newPrice = baseResources[Resources.ORE.ordinal()];
        assertEquals(testPrice, newPrice);
    }

    @Test
    public void changeResourceLevelTest13() {
        int[] baseResources = market.getResourcePrices().clone();
        market.changeResourceLevel(ResourceLevel.NOSPECIALRESOURCES);
        assertArrayEquals(baseResources, market.getResourcePrices());
    }

    @Test
    public void changeEventTest1() {
        int[] basePrices = market.getResourcePrices();
        int testPrice = basePrices[Resources.WATER.ordinal()] * 2;
        market.changeEvent(RadicalEvent.DROUGHT);
        int newPrice  = basePrices[Resources.WATER.ordinal()];
        assertEquals(testPrice, newPrice);
    }

    @Test
    public void changeEventTest2() {
        int[] basePrices = market.getResourcePrices();
        int testPrice = basePrices[Resources.FURS.ordinal()] * 2;
        market.changeEvent(RadicalEvent.COLD);
        int newPrice  = basePrices[Resources.FURS.ordinal()];
        assertEquals(testPrice, newPrice);
    }

    @Test
    public void changeEventTest3() {
        int[] basePrices = market.getResourcePrices();
        int testPrice = basePrices[Resources.FOOD.ordinal()] * 2;
        market.changeEvent(RadicalEvent.CROPFAIL);
        int newPrice  = basePrices[Resources.FOOD.ordinal()];
        assertEquals(testPrice, newPrice);
    }

    @Test
    public void changeEventTest4() {
        int[] basePrices = market.getResourcePrices();
        int testPrice1 = basePrices[Resources.ORE.ordinal()] * 2;
        market.changeEvent(RadicalEvent.WAR);
        int newPrice1  = basePrices[Resources.ORE.ordinal()];
        assertEquals(testPrice1, newPrice1);
    }

    @Test
    public void changeEventTest5() {
        int[] basePrices = market.getResourcePrices();
        int testPrice = basePrices[Resources.FIREARMS.ordinal()] * 2;
        market.changeEvent(RadicalEvent.WAR);
        int newPrice  = basePrices[Resources.FIREARMS.ordinal()];
        assertEquals(testPrice, newPrice);
    }

    @Test
    public void changeEventTest6() {
        int[] basePrices = market.getResourcePrices();
        int testPrice = basePrices[Resources.GAMES.ordinal()] * 2;
        market.changeEvent(RadicalEvent.BOREDOM);
        int newPrice  = basePrices[Resources.GAMES.ordinal()];
        assertEquals(testPrice, newPrice);
    }

    @Test
    public void changeEventTest7() {
        int[] basePrices = market.getResourcePrices();
        int testPrice = basePrices[Resources.NARCOTICS.ordinal()] * 2;
        market.changeEvent(RadicalEvent.BOREDOM);
        int newPrice  = basePrices[Resources.NARCOTICS.ordinal()];
        assertEquals(testPrice, newPrice);
    }

    @Test
    public void changeEventTest8() {
        int[] basePrices = market.getResourcePrices();
        int testPrice = basePrices[Resources.MEDICINE.ordinal()] * 2;
        market.changeEvent(RadicalEvent.PLAGUE);
        int newPrice  = basePrices[Resources.MEDICINE.ordinal()];
        assertEquals(testPrice, newPrice);
    }

    @Test
    public void changeEventTest9() {
        int[] basePrices = market.getResourcePrices();
        int testPrice = basePrices[Resources.MACHINES.ordinal()] * 2;
        market.changeEvent(RadicalEvent.LACKOFWORKERS);
        int newPrice  = basePrices[Resources.MACHINES.ordinal()];
        assertEquals(testPrice, newPrice);
    }

    @Test
    public void changeEventTest10() {
        int[] basePrices = market.getResourcePrices();
        int testPrice = basePrices[Resources.ROBOTS.ordinal()] * 2;
        market.changeEvent(RadicalEvent.LACKOFWORKERS);
        int newPrice  = basePrices[Resources.ROBOTS.ordinal()];
        assertEquals(testPrice, newPrice);
    }

    @Test
    public void changeEventTest11() {
        int[] basePrices = market.getResourcePrices().clone();
        market.changeEvent(RadicalEvent.NOEVENT);
        assertArrayEquals(basePrices, market.getResourcePrices());
    }
}
