package com.example.spacetraders.data.models

import com.example.spacetraders.data.entity.Universe

class UniverseInteractor
/**
 * allows UniverseInteractor to affect the universe info in Interactor.java
 * @param interactor Interactor.java
 */
(private val interactor: Interactor) {

    val universe: Universe?
        get() = interactor.universe

    /**
     * Stores the newly created universe into the Interactor
     * @param universe the newly created universe
     */
    fun createUniverse(universe: Universe) {
        interactor.universe = universe
    }
}
