package com.example.spacetraders.data.entity

/**
 * Planet class to hold all of the info pertaining to each individual planets
 */

class Planet
/**
 * Constructor for Planet class. Assigns given name to planet name.
 * @param name name of the planet
 */
@JvmOverloads constructor(var name: String? = "Planet") {
    var isHome: Boolean = false
    var market: Market? = null

    init {
        isHome = false
    }
}
/**
 * No-arg constructor for Planet class. Defaults name to "Planet"
 */
