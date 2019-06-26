package com.example.spacetraders.data.entity;

/**
 * Planet class to hold all of the info pertaining to each individual planets
 */

public class Planet {
    private String name;

    private boolean isHome;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public boolean isHome() { return isHome; }

    public void setHome(boolean isHome) { this.isHome = isHome; }

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
    }
}
