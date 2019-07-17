package com.example.spacetraders.data.models

import com.example.spacetraders.data.entity.Spaceship

class ShipInteractor
/**
 * Allows for ShipInteractor to affect ship data in Interactor.java
 * @param interactor Interactor.java
 */
(private val interactor: Interactor) {

    val ship: Spaceship?
        get() = interactor.character!!.ship

    val currentResources: IntArray?
        get() = interactor.character!!.ship!!.getCurrentResources()

    /**
     * Sets the new resources on the ship using the current setResource method in the Ship class
     * @param newResources the int array of the resources bought/sold by the character
     * @param Buying whether or not the character was buying or selling
     */
    fun setResource(newResources: IntArray, Buying: Boolean) {
        interactor.character!!.ship!!.setResource(newResources, Buying)
    }
}
