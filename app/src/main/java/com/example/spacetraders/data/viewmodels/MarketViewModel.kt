package com.example.spacetraders.data.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.view.Display

import com.example.spacetraders.data.entity.Spaceship
import com.example.spacetraders.data.models.CharacterInteractor
import com.example.spacetraders.data.models.MarketInteractor
import com.example.spacetraders.data.models.Model
import com.example.spacetraders.data.models.ShipInteractor
import com.example.spacetraders.data.entity.Character

class MarketViewModel
/**
 * Creates ViewModel that will deal with info from the marketplace trading
 * @param application idk what this is
 */
(application: Application) : AndroidViewModel(application) {
    private val shipInteractor: ShipInteractor
    private val characterInteractor: CharacterInteractor
    private val marketInteractor: MarketInteractor

    val ship: Spaceship?
        get() = shipInteractor.ship

    val character: Character?
        get() = characterInteractor.character

    var resourceAmounts: IntArray
        get() = marketInteractor.resourceAmounts
        set(newAmounts) {
            marketInteractor.resourceAmounts = newAmounts
        }

    val resourcePrices: IntArray
        get() = marketInteractor.resourcePrices

    val currentResources: IntArray?
        get() = shipInteractor.currentResources

    val cargoSize: Int
        get() = shipInteractor.ship!!.cargoSize

    init {
        shipInteractor = Model.instance.shipInteractor
        characterInteractor = Model.instance.characterInteractor
        marketInteractor = Model.instance.marketInteractor
    }

    /**
     * Uses the ship interactor to set the correct resources to the ship
     * @param resourceInputs the resource amounts of the transaction
     * @param Buying whether the character bought or sold
     */
    fun setResource(resourceInputs: IntArray, Buying: Boolean) {
        shipInteractor.setResource(resourceInputs, Buying)
    }

    /**
     * uses character interactor to update the characters currency after each transaction
     * @param transactionCost cost of transaction
     * @param Buying whether the character bought or sold
     */
    fun updateCurrency(transactionCost: Int, Buying: Boolean) {
        characterInteractor.updateCurrency(transactionCost, Buying)
    }
}
