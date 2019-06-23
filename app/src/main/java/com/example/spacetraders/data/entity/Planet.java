package com.example.spacetraders.data.entity;

/**
 * Planet class to hold all of the info pertaining to each individual planets
 */

public class Planet {
    private int[] coords;

    /**
     * Alternate constructor for Planet that chains to the normal constructor
     * @param coords coordinates in the form of [xCoord, yCoord]
     */
    public Planet(int[] coords) {
        this(coords[0], coords[1]);
    }

    /**
     * Constructor for Planet class. Takes in x and y coordinates and combines into a list.
     * @param xCoord x-coordinate of Planet
     * @param yCoord y-coordinate of Planet
     */
    public Planet(int xCoord, int yCoord) {
        int[] combined = new int[2];
        combined[0] = xCoord;
        combined[1] = yCoord;
        coords = combined;
    }

    public int[] getCoords() { return coords; }

    public void setCoords(int[] coords) { this.coords = coords; }
}
