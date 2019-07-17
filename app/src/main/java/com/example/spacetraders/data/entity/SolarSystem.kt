package com.example.spacetraders.data.entity

import android.graphics.Point
import java.util.ArrayList
import java.util.Random

class SolarSystem(gameDifficulty: GameDifficulty) {
    var name: String? = null
    var techLevel: TechLevel? = null
    var resources: ResourceLevel? = null
    var politicalSystem: PoliticalSystem? = null
    var planets: Array<Planet?>? = null
    var numPlanets: Int = 0
    var currentPlanet: Planet? = null
    var coords: IntArray? = null
    private val possibleNames = arrayOf("Acamar", "Adahn", "Aldea", "Andevian", "Antedi", "Balosnee", "Baratas", "Brax", "Bretel", "Calondia", "Campor", "Capelle", "Carzon", "Castor", "Cestus", "Cheron", "Courteney", "Daled", "Damast", "Davlos", "Deneb", "Deneva", "Devidia", "Draylon", "Drema", "Endor", "Esmee", "Exo", "Ferris", "Festen", "Fourmi", "Frolix", "Gemulon", "Guinifer", "Hades", "Hamlet", "Helena", "Hulst", "Iodine", "Iralius", "Janus", "Japori", "Jarada", "Jason", "Kaylon", "Khefka", "Kira", "Klaatu", "Klaestron", "Korma", "Kravat", "Krios", "Laertes", "Largo", "Lave", "Ligon", "Lowry", "Magrat", "Malcoria", "Melina", "Mentar", "Merik", "Mintaka", "Montor", "Mordan", "Myrthe", "Nelvana", "Nix", "Nyle", "Odet", "Og", "Omega", "Omphalos", "Orias", "Othello", "Parade", "Penthara", "Picard", "Pollux", "Quator", "Rakhar", "Ran", "Regulas", "Relva", "Rhymus", "Rochani", "Rubicum", "Rutia", "Sarpeidon", "Sefalla", "Seltrice", "Sigma", "Sol", "Somari", "Stakoron", "Styris", "Talani", "Tamus", "Tantalos", "Tanuga", "Tarchannen", "Terosa", "Thera", "Titan", "Torin", "Triacus", "Turkana", "Tyrus", "Umberlee", "Utopia", "Vadera", "Vagra", "Vandor", "Ventax", "Xenon", "Xerxes", "Yew", "Yojimbo", "Zalkon", "Zuul")

    fun getDistance(system: SolarSystem): Int {
        val otherCoords = system.coords
        val x = (otherCoords!![0] - coords!![0]).toDouble()
        val y = (otherCoords[1] - coords!![1]).toDouble()
        return Math.round(Math.round(Math.hypot(x, y)).toFloat())
    }

    override fun toString(): String {
        return name!!
    }

    init {
        val rand = Random()
        var possibleName = possibleNames[rand.nextInt(possibleNames.size)]
        while (true) {
            if (!SolarSystemNames.contains(possibleName)) {
                name = possibleName
                SolarSystemNames.add(possibleName)
                break
            } else {
                possibleName = possibleNames[rand.nextInt(possibleNames.size)]
            }
        }

        var techWeights: IntArray? = null
        var resourceWeights: IntArray? = null
        when (gameDifficulty) {
            GameDifficulty.BEGINNER -> {
                techWeights = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7)
                resourceWeights = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
            }
            GameDifficulty.EASY -> {
                techWeights = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7)
                resourceWeights = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
            }
            GameDifficulty.NORMAL -> {
                techWeights = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7)
                resourceWeights = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
            }
            GameDifficulty.HARD -> {
                techWeights = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7)
                resourceWeights = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
            }
            GameDifficulty.IMPOSSIBLE -> {
                techWeights = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7)
                resourceWeights = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
            }
        }

        if (techWeights.size != TechLevel.values().size) {
            System.err.println("Tech Weights do not match Tech Levels")
        }
        if (resourceWeights.size != ResourceLevel.values().size) {
            System.err.println("Resource Weights do not match Resources")
        }

        techLevel = TechLevel.values()[getSelection(techWeights)]
        resources = ResourceLevel.values()[getSelection(resourceWeights)]

        val rand2 = Random()
        politicalSystem = PoliticalSystem.values()[rand2.nextInt(PoliticalSystem.values().size)]
    }

    private fun getSelection(weights: IntArray): Int {
        var total = 0
        for (`val` in weights) total += `val`
        val r = Random().nextInt(total)
        var selection = 0
        for (i in weights.indices) {
            selection += weights[i]
            if (r <= selection) return i
        }
        return weights.size - 1
    }

    /**
     * Generates a list of planets populating the solar system
     * Each planet is given the name "Planet1", "Planet2"...
     * Except for Planet1, which is given the same name as the solar system
     * and is set as the home planet of the system
     *
     * @param numPlanetLimit maximum number of planets a solar system can have
     * @return a list of the created planets
     */
    fun generatePlanets(numPlanetLimit: Int): Array<Planet?> {
        val rand = Random()
        val numPlanets = rand.nextInt(numPlanetLimit) + 1
        val newPlanets = arrayOfNulls<Planet>(numPlanets)

        for (i in 0 until numPlanets) {
            if (i == 0) {
                newPlanets[i] = Planet(name)
                newPlanets[i]!!.isHome = true
            }
            newPlanets[i] = Planet("Planet" + Integer.valueOf(i + 1))
            val market = Market(techLevel!!)
            market.changeResourceLevel(resources!!)
            if (rand.nextBoolean()) {
                market.changeEvent(RadicalEvent.values()[rand.nextInt(RadicalEvent.values().size)])
            }
            newPlanets[i]!!.market = market
        }

        this.numPlanets = numPlanets
        this.planets = newPlanets
        currentPlanet = newPlanets[0]
        return newPlanets
    }

    companion object {
        private val SolarSystemNames = ArrayList<String>()

        fun getRandom(weights: DoubleArray): Int {
            val rand = Random()
            var x = rand.nextDouble()
            for (i in weights.indices) {
                if (x <= weights[i]) {
                    return i
                } else {
                    x -= weights[i]
                }
            }
            return rand.nextInt(weights.size)
        }
    }
}
