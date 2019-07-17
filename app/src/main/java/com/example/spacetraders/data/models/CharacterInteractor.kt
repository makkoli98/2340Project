package com.example.spacetraders.data.models

import com.example.spacetraders.data.entity.Character
import com.example.spacetraders.data.entity.GameDifficulty
import com.example.spacetraders.data.entity.Planet
import com.example.spacetraders.data.entity.PoliticalSystem
import com.example.spacetraders.data.entity.ResourceLevel
import com.example.spacetraders.data.entity.TechLevel

class CharacterInteractor
/**
 * Allows the character interactor to affect the data held in Interactor.java
 * @param interactor The original interactor from Interactor.java that has all of the data for the app
 */
(private val interactor: Interactor) {

    val character: Character?
        get() = interactor.character

    val currentTechLevel: TechLevel?
        get() = interactor.character!!.currentSolarSystem!!.techLevel

    val currentResourceLevel: ResourceLevel?
        get() = interactor.character!!.currentSolarSystem!!.resources

    val currentPoliticalSystem: PoliticalSystem?
        get() = interactor.character!!.currentSolarSystem!!.politicalSystem

    val currentSolarSystemName: String?
        get() = interactor.character!!.currentSolarSystem!!.name

    val numPlanets: Int
        get() = interactor.character!!.currentSolarSystem!!.numPlanets

    val planets: Array<Planet?>?
        get() = interactor.character!!.currentSolarSystem!!.planets

    val currentPlanet: Planet?
        get() = interactor.character!!.currentSolarSystem!!.currentPlanet

    /**
     * Stores the newly created character into the Interactor
     * @param character the newly created character
     */
    fun createCharacter(character: Character) {
        interactor.character = character
        interactor.character!!.currentSolarSystem = interactor.universe!!.systems!![0]
    }

    /**
     * updates the currency of the character after each transaction
     * @param transactionCost cost of the transaction
     * @param Buying whether the character bought or sold
     */
    fun updateCurrency(transactionCost: Int, Buying: Boolean) {
        if (Buying) {
            interactor.character!!.currency = interactor.character!!.currency - transactionCost
        } else {
            interactor.character!!.currency = interactor.character!!.currency + transactionCost
        }
    }

    fun setName(name: String) {
        interactor.character!!.name = name
    }

    fun setDifficulty(difficulty: GameDifficulty) {
        interactor.character!!.difficulty = difficulty
    }
}
