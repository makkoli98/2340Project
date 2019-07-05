package com.example.spacetraders.data.entity;

/**
 * Enum class for Spaceship that holds the different types of ships and their attributes such as
 * maximum cargo hold and maximum health
 */

public enum ShipType {
    // Ship has name, cargo size, health, weapons amount, fuel efficiency, and base price
    FLEA("Flea", 10, 10, 1, 5, 500),
    GNAT("Gnat", 15, 20, 1, 14, 1000),
    FIREFLY("Firefly", 20, 30, 1, 17, 750),
    MOSQUITO("Mosquito", 15, 30, 2, 13, 1500),
    BUMBLEBEE("Bumblebee", 20, 40, 2, 15, 2000),
    BEETLE("Beetle", 50, 25, 0, 14, 3500),
    HORNET("Hornet", 20, 45, 3, 16, 3000),
    GRASSHOPPER("Grasshopper", 30, 40, 2, 15, 2500),
    TERMITE("Termite", 60, 55, 1, 13, 4500),
    WASP("Wasp", 35, 40, 3, 14, 6000);

    private final String name;
    private final int cargoSize;
    private final int maximumHealth;
    private final int maxWeaponsAmount;
    private final int fuelEfficiency;
    private final int basePrice;

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
    private ShipType(String name, int cargoSize, int maximumHealth, int maxWeaponsAmount, int fuelEfficiency, int basePrice) {
        this.name = name;
        this.cargoSize = cargoSize;
        this.maximumHealth = maximumHealth;
        this.maxWeaponsAmount = maxWeaponsAmount;
        this.fuelEfficiency = fuelEfficiency;
        this.basePrice = basePrice;
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

    public int getBasePrice() {
        return basePrice;
    }
}
