package com.example.spacetraders.data.entity

/**
 * Enum class holding the name of the resources
 * Use for indexing resources in resource array
 * e.g.
 * int[] resources = new int[Resources.values().length]
 */
enum class Resources private constructor(val Name: String, val basePrice: Int, val mtlp: Int, val mtlu: Int, val ttp: Int, val ipl: Int, val `var`: Int, val mtl: Int, val mth: Int) {
    WATER("Water", 30, 0, 0, 2, 3, 4, 30, 50),
    FURS("Furs", 250, 0, 0, 0, 10, 10, 230, 280),
    FOOD("Food", 100, 1, 0, 1, 5, 5, 90, 160),
    ORE("Ore", 350, 2, 2, 3, 20, 10, 350, 420),
    GAMES("Games", 250, 3, 1, 6, -10, 5, 160, 270),
    FIREARMS("Firearms", 1250, 3, 1, 5, -75, 100, 600, 1100),
    MEDICINE("Medicine", 650, 4, 1, 6, -20, 10, 400, 700),
    MACHINES("Machines", 900, 4, 3, 5, -30, 5, 600, 800),
    NARCOTICS("Narcotics", 3500, 5, 0, 5, -125, 150, 2000, 3000),
    ROBOTS("Robots", 5000, 6, 4, 7, -150, 100, 3500, 5000)
}
