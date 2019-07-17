package com.example.spacetraders.data.entity

import java.util.Random
import kotlin.math.roundToInt

/**
 * Market Class
 *
 * Handles resource prices and amounts
 */
class Market
/**
 * Market class constructor. Uses the tech level of the system to generate market values
 * The amount of each resource is randomized between 0 and the cap value.
 *
 * @param techLevel the tech level of the system
 */
(techLevel: TechLevel) {
    var resourcePrices = IntArray(Resources.values().size)
    var resourceAmount = IntArray(Resources.values().size)

    init {
        val rand = Random()

        for (i in resourcePrices.indices) {
            val basePrice = Resources.values()[i].basePrice
            val IPL = Resources.values()[i].ipl
            val MTLP = Resources.values()[i].mtlp
            var Var = Resources.values()[i].`var`

            if (rand.nextBoolean()) {
                Var = -1 * Var
            }

            //(basePrice) + (IPL * (TechLevel - MTLP)) + (Var)
            resourcePrices[i] = basePrice + IPL * (techLevel.ordinal - MTLP) + Var

            val cap = 50
            val techNum = techLevel.ordinal
            if (i == 0 && techNum >= 0) {
                resourceAmount[i] = rand.nextInt(cap)
            } else if (i == 1 && techNum >= 0) {
                resourceAmount[i] = rand.nextInt(cap)
            } else if (i == 2 && techNum >= 1) {
                resourceAmount[i] = rand.nextInt(cap)
            } else if (i == 3 && techNum >= 2) {
                resourceAmount[i] = rand.nextInt(cap)
            } else if (i == 4 && techNum >= 3) {
                resourceAmount[i] = rand.nextInt(cap)
            } else if (i == 5 && techNum >= 3) {
                resourceAmount[i] = rand.nextInt(cap)
            } else if (i == 6 && techNum >= 4) {
                resourceAmount[i] = rand.nextInt(cap)
            } else if (i == 7 && techNum >= 4) {
                resourceAmount[i] = rand.nextInt(cap)
            } else if (i == 8 && techNum >= 5) {
                resourceAmount[i] = rand.nextInt(cap)
            } else if (i == 9 && techNum >= 6) {
                resourceAmount[i] = rand.nextInt(cap)
            }
        }
    }

    /**
     * Adjusts market prices based on the resource level of the system.
     * Negative resource levels multiply price by 0.8
     * Positive resource levels multiply price by 1.2
     *
     * @param resourceLevel
     */
    fun changeResourceLevel(resourceLevel: ResourceLevel) {
        when (resourceLevel) {
            ResourceLevel.LOTSOFWATER -> resourcePrices[Resources.WATER.ordinal] *= 0.8.roundToInt()
            ResourceLevel.RICHFAUNA -> resourcePrices[Resources.FURS.ordinal] *= 0.8.roundToInt()
            ResourceLevel.RICHSOIL -> resourcePrices[Resources.FOOD.ordinal] *= 0.8.roundToInt()
            ResourceLevel.MINERALRICH -> resourcePrices[Resources.ORE.ordinal] *= 0.8.roundToInt()
            ResourceLevel.ARTISTIC -> resourcePrices[Resources.GAMES.ordinal] *= 0.8.roundToInt()
            ResourceLevel.WARLIKE -> resourcePrices[Resources.FIREARMS.ordinal] *= 0.8.roundToInt()
            ResourceLevel.LOTSOFHERBS -> resourcePrices[Resources.MEDICINE.ordinal] *= 0.8.roundToInt()
            ResourceLevel.WEIRDMUSHROOMS -> resourcePrices[Resources.NARCOTICS.ordinal] *= 0.8.roundToInt()
            ResourceLevel.DESERT -> resourcePrices[Resources.WATER.ordinal] *= 1.2.roundToInt()
            ResourceLevel.LIFELESS -> resourcePrices[Resources.FURS.ordinal] *= 1.2.roundToInt()
            ResourceLevel.POORSOIL -> resourcePrices[Resources.FOOD.ordinal] *= 1.2.roundToInt()
            ResourceLevel.MINERALPOOR -> resourcePrices[Resources.ORE.ordinal] *= 1.2.roundToInt()
            else -> {
            }
        }
    }

    /**
     * Adjusts market prices based on a radical event
     * As all of these are negative events,
     * prices are doubled during these events
     *
     * @param IE Radically price increasing event
     */
    fun changeEvent(IE: RadicalEvent) {
        when (IE) {
            RadicalEvent.DROUGHT -> resourcePrices[Resources.WATER.ordinal] *= 2
            RadicalEvent.COLD -> resourcePrices[Resources.FURS.ordinal] *= 2
            RadicalEvent.CROPFAIL -> resourcePrices[Resources.FOOD.ordinal] *= 2
            RadicalEvent.WAR -> {
                resourcePrices[Resources.ORE.ordinal] *= 2
                resourcePrices[Resources.FIREARMS.ordinal] *= 2
            }
            RadicalEvent.BOREDOM -> {
                resourcePrices[Resources.GAMES.ordinal] *= 2
                resourcePrices[Resources.NARCOTICS.ordinal] *= 2
            }
            RadicalEvent.PLAGUE -> resourcePrices[Resources.MEDICINE.ordinal] *= 2
            RadicalEvent.LACKOFWORKERS -> {
                resourcePrices[Resources.MACHINES.ordinal] *= 2
                resourcePrices[Resources.ROBOTS.ordinal] *= 2
            }
            else -> {
            }
        }
    }
}