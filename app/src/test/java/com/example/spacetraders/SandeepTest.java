package com.example.spacetraders;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.spacetraders.data.entity.GameDifficulty;
import com.example.spacetraders.data.entity.SolarSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.HashSet;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SandeepTest {
    @Test
    public void testName() {
        HashSet<String> solarSystemNames = new HashSet<>();
        try {
            for (int i = 0; i < 10; i++) {
                SolarSystem solarSystem = new SolarSystem(GameDifficulty.BEGINNER);
                solarSystemNames.add(solarSystem.getName());
            }
            assertEquals(solarSystemNames.size(), 10);
        } catch (Exception e) {

        }
    }

    @Test
    public void testNumberSystems() {
        try {
            for (int i = 0; i < 200; i++) {
                SolarSystem solarSystem = new SolarSystem(GameDifficulty.BEGINNER);
            }
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            assertEquals(e.getMessage(), "Max Solar System Limit Reached");
        }
    }

    @Test public void testRandom() {
        double[] weights = new double[] {.1, .2, .15, .3, .25};
        double[] indices = new double[] {0,0,0,0,0};
        for (int i = 0; i < 1000; i++) {
            int index = SolarSystem.getRandom(weights);
            indices[index]++;
        }
        for (int j = 0; j < 15; j++) {
            for (int i = 0; i < indices.length; i++) {
                assertTrue(indices[i]/1000 <= weights[i]* 1.2 || indices[i]/1000 >= weights[i] * .8);
            }
        }
    }
}