package com.example.spacetraders.data.entity

import java.util.HashMap


class Spaceship(// ShipType methods
        val type: ShipType) {
    private var currentResources: IntArray? = null
    private var currentHealth: Int = 0
    private var fuel: Int = 0
    var maxFuel: Int = 100

    val name: String
        get() = type.name

    val cargoSize: Int
        get() = type.cargoSize

    val maximumHealth: Int
        get() = type.maximumHealth

    val maxWeaponsAmount: Int
        get() = type.maxWeaponsAmount

    val fuelEfficiency: Int
        get() = type.fuelEfficiency

    val basePrice: Int
        get() = type.basePrice

    init {
        currentResources = IntArray(Resources.values().size)
        currentHealth = type.maximumHealth
        maxFuel = 100
    }

    fun getCurrentResources(): IntArray? {
        return currentResources
    }

    @Throws(IllegalArgumentException::class)
    fun setCurrentResources(currentResources: IntArray) {
        var sum = 0
        for (i in currentResources) {
            sum += i
        }
        if (sum > type.cargoSize) {
            throw IllegalArgumentException("Attempted to set Spaceship resources beyond cargo capacity")
        }
        this.currentResources = currentResources
    }

    fun getCurrentHealth(): Int {
        return currentHealth
    }

    @Throws(IllegalArgumentException::class)
    fun setCurrentHealth(currentHealth: Int) {
        if (currentHealth < 0) {
            throw IllegalArgumentException("Attempted to set Spaceship to negative health")
        }
        if (currentHealth > type.maximumHealth) {
            throw IllegalArgumentException("Attempted to set Spaceship beyond maximum health")
        }
        this.currentHealth = currentHealth
    }

    /**
     * @param newResourceAmounts the amounts of each resource that the character has after purchase
     * @param buying whether or not the character is buying
     * Changes the resource quantity in the spaceship cargo to the new amount
     */
    fun setResource(newResourceAmounts: IntArray, buying: Boolean?) {
        for (i in currentResources!!.indices) {
            currentResources!![i] += (if (buying!!) 1 else -1) * newResourceAmounts[i]
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

    fun getFuel(): Int {
        return fuel
    }

    /**
     * Sets the fuel to a valid provided value. If the value is invalid, returns false and does not
     * update the fuel value;
     * @param val the fuel value
     * @return if the value was valid
     */
    fun setFuel(`val`: Int): Boolean {
        if (`val` < 0 || `val` > 100) {
            return false
        }
        fuel = `val`
        return true
    }

    override fun toString(): String {
        return type.name
    }

    companion object {
        var maxFuel: Int = 0
    }
}
