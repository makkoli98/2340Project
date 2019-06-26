package com.example.spacetraders.data.entity;

import java.util.ArrayList;
import java.util.Random;

/**
 * The universe in which the game takes place
 *
 * Holds a collection of solar systems
 */

public class Universe {

    private SolarSystem[] systems;
    private static int[] gridSize = {150, 100};

    public SolarSystem[] getSystems() { return systems; }

    public void setSystems(SolarSystem[] systems) { this.systems = systems; }

    /**
     * Simple constructor that allows for Universe initialization with just GameDifficulty
     * @param gameDifficulty difficulty of current game
     */
    public Universe(GameDifficulty gameDifficulty) {
        this(10, 5, 10, gameDifficulty);
    }

    /**
     * Constructor for the Universe Class. Allows for more flexible control of universe
     * @param numSystems number of solar systems the universe contains
     * @param numPlanetLimit number of the limit of planets a solar system can contain (1-limit)
     * @param proximityParam minimum distance between solar systems
     * @param gameDifficulty difficulty of the game
     */
    public Universe(int numSystems, int numPlanetLimit, int proximityParam,
                    GameDifficulty gameDifficulty) {

        Random rand = new Random();
        systems = new SolarSystem[numSystems];

        for (int i = 0; i < numSystems; i++) {
            systems[i] = new SolarSystem(gameDifficulty);
        }

        ArrayList<Integer> xCoords = new ArrayList<>();
        ArrayList<Integer> yCoords = new ArrayList<>();
        xCoords.add(0);
        yCoords.add(0);
        int sysCounter = 1;
        int randX;
        int randY;
        while (sysCounter < numSystems) {
            randX = rand.nextInt(gridSize[0]);
            randY = rand.nextInt(gridSize[1]);
            if (!(xCoords.contains(randX) && yCoords.contains(randY))) {
                xCoords.add(randX);
                yCoords.add(randY);
                sysCounter++;
            }
        }


        for (int i = 0; i < numSystems; i++) {
            int[] sysCoords = {xCoords.get(i), yCoords.get(i)};
            systems[i].setCoords(sysCoords);
            systems[i].generatePlanets(numPlanetLimit);
        }
    }

}
