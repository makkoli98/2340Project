package com.example.spacetraders.data.entity;
import java.util.ArrayList;
import java.util.HashMap;




public class Spaceship {
    private int[] currentResources;
    private int currentHealth;
    private ShipType type;
    private int fuel;
    public static int maxFuel;
    private int currWeaponsCount;
    private ArrayList<Weapons> list;




    public Spaceship(ShipType type) {
        this.type = type;
        currentResources = new int[Resources.values().length];
        currentHealth = type.getMaximumHealth();
        maxFuel = 100;
        currWeaponsCount = 0;
        list = new ArrayList<Weapons>(0);
    }


    public int[] getCurrentResources() {
        return currentResources;
    }

    public void setCurrentResources(int[] currentResources) throws IllegalArgumentException {
        int sum = 0;
        for(int i : currentResources) {
            sum += i;
        }
        if(sum > type.getCargoSize()) {
            throw new IllegalArgumentException("Attempted to set Spaceship resources beyond cargo capacity");
        }
        this.currentResources = currentResources;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) throws IllegalArgumentException {
        if(currentHealth < 0) {
            throw new IllegalArgumentException("Attempted to set Spaceship to negative health");
        }
        if(currentHealth > type.getMaximumHealth()) {
            throw new IllegalArgumentException("Attempted to set Spaceship beyond maximum health");
        }
        this.currentHealth = currentHealth;
    }

    /**
     * @param newResourceAmounts the amounts of each resource that the character has after purchase
     * @param buying whether or not the character is buying
     * Changes the resource quantity in the spaceship cargo to the new amount
     */
    public void setResource(int[] newResourceAmounts, Boolean buying) {
        for (int i = 0; i < currentResources.length; i++) {
            currentResources[i] += ((buying) ? 1 : -1) * newResourceAmounts[i];
        }
        /*
        if (buying) {
            for (int i = 0; i < currentResources.length; i++) {
                currentResources[i] += newResourceAmounts[i];
            }
        } else {
            for (int i = 0; i < currentResources.length; i++) {
                currentResources[i] -= newResourceAmounts[i];
            }
        }
        */
    }

    public int getFuel() {
        return fuel;
    }

    /**
     * Sets the fuel to a valid provided value. If the value is invalid, returns false and does not
     * update the fuel value;
     * @param val the fuel value
     * @return if the value was valid
     */
    public boolean setFuel(int val) {
        if(val < 0 || val > 100) {
            return false;
        }
        fuel = val;
        return true;
    }

    @Override
    public String toString() {
        return type.getName();
    }

    // ShipType methods
    public ShipType getType() {
        return type;
    }

    public String getName() {
        return type.getName();
    }

    public int getCargoSize() {
        return type.getCargoSize();
    }

    public int getMaximumHealth() {
        return type.getMaximumHealth();
    }

    public int getMaxWeaponsAmount() {
        return type.getMaxWeaponsAmount();
    }

    public int getFuelEfficiency() {
        return type.getFuelEfficiency();
    }

    public int getBasePrice() {
        return type.getBasePrice();
    }

    public int getCurrWeaponsCount() {
        return currWeaponsCount;
    }

    public void setCurrWeaponsCount(int count) {
        currWeaponsCount += count;
    }
    public void addWeapon(Weapons laser) {
        if (list.size() >= type.getMaxWeaponsAmount()) {
            return;
        } else {
            list.add(laser);
            currWeaponsCount++;
        }
    }
    public int getSonicRayAmount() {
        int count = 0;
        for (Weapons w: list) {
            if (w.getWeaponName().equals("Sonic Ray")) {
                count++;
            }
        }
        return count;
    }
    public int getPlasmaRayAmount() {
        int count = 0;
        for (Weapons w: list) {
            if (w.getWeaponName().equals("Plasma Ray")) {
                count++;
            }
        }
        return count;
    }
    public int getMesonPhaserAmount() {
        int count = 0;
        for (Weapons w: list) {
            if (w.getWeaponName().equals("Meson Phaser")) {
                count++;
            }
        }
        return count;
    }
}
