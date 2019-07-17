package com.example.spacetraders.data.entity

import java.util.ArrayList
import java.util.Random

/**
 * The universe in which the game takes place
 *
 * Holds a collection of solar systems
 */

class Universe
/**
 * Constructor for the Universe Class. Allows for more flexible control of universe
 * @param numSystems number of solar systems the universe contains
 * @param numPlanetLimit number of the limit of planets a solar system can contain (1-limit)
 * @param proximityParam minimum distance between solar systems
 * @param gameDifficulty difficulty of the game
 */
(numSystems: Int, numPlanetLimit: Int, proximityParam: Int,
 gameDifficulty: GameDifficulty) {

    var systems: Array<SolarSystem?>? = null

    /**
     * Simple constructor that allows for Universe initialization with just GameDifficulty
     * @param gameDifficulty difficulty of current game
     */
    constructor(gameDifficulty: GameDifficulty) : this(10, 5, 10, gameDifficulty) {}

    init {

        val rand = Random()
        systems = Array(numSystems, {null})

        for (i in 0 until numSystems) {
            systems!![i] = SolarSystem(gameDifficulty)
        }

        val xCoords = ArrayList<Int>()
        val yCoords = ArrayList<Int>()
        var sysCounter = 0
        var randX: Int
        var randY: Int
        while (sysCounter < numSystems) {
            randX = rand.nextInt(gridSize[0])
            randY = rand.nextInt(gridSize[1])
            if (!(xCoords.contains(randX) && yCoords.contains(randY))) {
                xCoords.add(randX)
                yCoords.add(randY)
                sysCounter++
            }
        }


        for (i in 0 until numSystems) {
            val sysCoords = intArrayOf(xCoords[i], yCoords[i])
            systems!![i]!!.coords = sysCoords
            systems!![i]!!.generatePlanets(numPlanetLimit)
        }
    }

    override fun toString(): String {
        var str = ""
        var num = 1
        for (system in systems!!) {
            str += ("Solar system " + num + ":\n"
                    + "\tName: " + system!!.name + "\n"
                    + "\tCoordinates: " + "(" + system.coords!![0]
                    + ", " + system.coords!![1] + ")\n"
                    + "\tResource: " + system.resources + "\n"
                    + "\tTech level: " + system.techLevel + "\n")
            num++
        }
        return str
    }

    companion object {
        private val gridSize = intArrayOf(150, 100)
    }

}
