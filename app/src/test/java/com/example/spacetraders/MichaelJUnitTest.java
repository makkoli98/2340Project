package com.example.spacetraders;

import com.example.spacetraders.data.entity.Market;
import com.example.spacetraders.data.entity.ResourceLevel;
import com.example.spacetraders.data.entity.Resources;
import com.example.spacetraders.data.entity.TechLevel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
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
        baseResources[Resources.WATER.ordinal()] *= 4;
        market.changeResourceLevel(ResourceLevel.LOTSOFWATER);
        assertArrayEquals(baseResources, market.getResourcePrices());
    }
}
