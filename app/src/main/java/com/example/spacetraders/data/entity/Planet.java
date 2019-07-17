package com.example.spacetraders.data.entity;

import java.util.ArrayList;
import java.util.Random;

import com.example.spacetraders.data.entity.Mercenary;

/**
 * Planet class to hold all of the info pertaining to each individual planets
 */

public class Planet {
    private String name;
    private boolean isHome;
    private Market market;
    private ArrayList<Mercenary> availableMercenaries = new ArrayList<>();

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public boolean isHome() { return isHome; }

    public void setHome(boolean isHome) { this.isHome = isHome; }

    public Market getMarket() { return market; }

    public void setMarket(Market market) { this.market = market; }

    /**
     * No-arg constructor for Planet class. Defaults name to "Planet"
     */
    public Planet() {
        this("Planet");
    }

    /**
     * Constructor for Planet class. Assigns given name to planet name.
     * @param name name of the planet
     */
    public Planet(String name) {
        this.name = name;
        isHome = false;
        generateMercenaries();
    }

    private void generateMercenaries() {
        Random rand = new Random();
        for (int i = 0; i < rand.nextInt(5) + 1; i++) {
            availableMercenaries.add(new Mercenary());
        }
    }

    public ArrayList<Mercenary> getAvailableMercenaries() {return availableMercenaries;}

    public void removeMercenary(Mercenary mercenary) {
        availableMercenaries.remove(mercenary);
    }
}
