package com.example.spacetraders.data.models

import java.util.HashMap


/**
 * Holds the different interactors and the save database
 * Each of the ViewModels uses this to get the appropriate Interactor
 */
class Model
/**
 * Uses a hashmap to get the specific Interactor
 */
private constructor() {
    private val saveDatabase: SaveDatabase? = null
    private val interactors: MutableMap<String, Any>
    private val repository: Interactor

    val characterInteractor: CharacterInteractor
        get() = interactors["Character"] as CharacterInteractor

    val universeInteractor: UniverseInteractor
        get() = interactors["Universe"] as UniverseInteractor

    val shipInteractor: ShipInteractor
        get() = interactors["Ship"] as ShipInteractor

    val marketInteractor: MarketInteractor
        get() = interactors["Market"] as MarketInteractor

    init {
        repository = Interactor.interactor
        interactors = HashMap()
        interactors["Character"] = CharacterInteractor(repository)
        interactors["Universe"] = UniverseInteractor(repository)
        interactors["Ship"] = ShipInteractor(repository)
        interactors["Market"] = MarketInteractor(repository)

    }

    companion object {

        /**
         * @return the instance of the model that holds all the interactors
         */
        val instance = Model()
    }
}
