package com.example.spacetraders.data.entity;

/**
 * Bare bones implementation of the Spaceship class
 * Might be a good idea to make this an abstract class later one
 * as we implement the different types of ships
 */
public class Spaceship {
    private String name;

    /**
     * Default no-args constructor for the Spaceship class
     * Sets the default ship to Gnat
     */
    public Spaceship() {
        this("Gnat");
    }

    /**
     * Constructor for the Spaceship class
     *
     * @param nam The name of the ship
     */
    public Spaceship(String nam) {
        name = nam;
    }
}
