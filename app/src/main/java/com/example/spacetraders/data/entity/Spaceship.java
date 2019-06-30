package com.example.spacetraders.data.entity;
import java.util.HashMap;

/**
 * Enum class for Spaceship that holds the different types of ships and their attributes such as
 * maximum cargo hold and maximum health
 */
public enum Spaceship {
    // Ship has name, cargo size, health, weapons amount, fuel efficiency, and base price
    FLEA("Flea", 10, 10, 1, 5, 500.0),
    GNAT("Gnat", 15, 20, 1, 14, 1000.0),
    FIREFLY("Firefly", 20, 30, 1, 17, 750.0),
    MOSQUITO("Mosquito", 15, 30, 2, 13, 1500.0),
    BUMBLEBEE("Bumblebee", 20, 40, 2, 15, 2000.0),
    BEETLE("Beetle", 50, 25, 0, 14, 3500.0),
    HORNET("Hornet", 20, 45, 3, 16, 3000.0),
    GRASSHOPPER("Grasshopper", 30, 40, 2, 15, 2500.0),
    TERMITE("Termite", 60, 55, 1, 13, 4500.0),
    WASP("Wasp", 35, 40, 3, 14, 6000.0);

    private final String name;
    private final int cargoSize;
    private final int maximumHealth;
    private final int maxWeaponsAmount;
    private final int fuelEfficiency;
    private final double basePrice;
    private int[] currentResources;
    private int currentHealth;

    /**
     * @param name name of the ship
     * @param cargoSize maximum amount of cargo ship can hold
     * @param maximumHealth maximum health that the ship can have
     * @param maxWeaponsAmount max amount of weapons that can be added onto ship
     * @param fuelEfficiency how far it can travel on a full tank
     * @param basePrice price of the ship before economic model changes the price
     * When creating a ship, it starts out with an empty cargo, so any cargo in the ship that was sold
     * is lost upon purchase of a new ship
     */
    Spaceship(String name, int cargoSize, int maximumHealth, int maxWeaponsAmount, int fuelEfficiency, double basePrice) {
        this.name = name;
        this.cargoSize = cargoSize;
        this.maximumHealth = maximumHealth;
        this.maxWeaponsAmount = maxWeaponsAmount;
        this.fuelEfficiency = fuelEfficiency;
        this.basePrice = basePrice;
        // indices are ordered based on ordinal from resources
        currentResources = new int[Resources.values().length];
        currentHealth = maximumHealth;
    }

    public String getName() {
        return name;
    }

    public int getCargoSize() {
        return cargoSize;
    }

    public int getMaximumHealth() {
        return maximumHealth;
    }

    public int getMaxWeaponsAmount() {
        return maxWeaponsAmount;
    }

    public int getFuelEfficiency() {
        return fuelEfficiency;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public int[] getCurrentResources() {
        return currentResources;
    }

    public void setCurrentResources(int[] currentResources) {
        this.currentResources = currentResources;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    /**
     * @param newResourceAmounts the amounts of each resource that the character has after purchase
     * @param buying whether or not the character is buying
     * Changes the resource quantity in the spaceship cargo to the new amount
     */
    public void setResource(int[] newResourceAmounts, Boolean buying) {
        if (buying) {
            for (int i = 0; i < currentResources.length; i++) {
                currentResources[i] += newResourceAmounts[i];
            }
        } else {
            for (int i = 0; i < currentResources.length; i++) {
                currentResources[i] -= newResourceAmounts[i];
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
