package com.example.spacetraders.data.models

class MarketInteractor
/**
 * creates market interactor for handling any thing with the market such as resource prices/amounts
 * @param interactor Interactor.java
 */
(private val interactor: Interactor) {

    var resourcePrices: IntArray
        get() = interactor.character!!.currentSolarSystem!!.currentPlanet!!.market!!.resourcePrices
        set(resourcePrices) {
            interactor.character!!.currentSolarSystem!!.currentPlanet!!.market!!.resourcePrices = resourcePrices
        }

    var resourceAmounts: IntArray
        get() = interactor.character!!.currentSolarSystem!!.currentPlanet!!.market!!.resourceAmount
        set(resourceAmounts) {
            interactor.character!!.currentSolarSystem!!.currentPlanet!!.market!!.resourceAmount = resourceAmounts
        }
}
