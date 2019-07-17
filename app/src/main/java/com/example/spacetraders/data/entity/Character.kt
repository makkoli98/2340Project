package com.example.spacetraders.data.entity

class Character
/**
 * Constructor for the Character class
 *
 * @param nam Name of the character
 * @param diff Difficulty setting of the character
 * @param currency the currency of the character
 * @param shipType the shiptype of the character
 */
@JvmOverloads constructor(var name: String? = "NAME", var difficulty: GameDifficulty? = GameDifficulty.BEGINNER, var currency: Int = 1000, shipType: ShipType = ShipType.GNAT) {
    val skills: IntArray
    var ship: Spaceship? = null
    var currentSolarSystem: SolarSystem? = null

    init {
        ship = Spaceship(shipType)
        skills = IntArray(Skill.values().size)
        skills[Skill.UNALLOCATED.ordinal] = 16
    }

    fun getSkill(skill: Skill): Int {
        return skills[skill.ordinal]
    }

    /**
     * If the skill is a valid value (eg, does not go over max skill), then adds the proper value
     * to the specified skill. If the skill is invalid, returns false.
     * This method should also be used to increase the number of UNALLOCATED skill points.
     * @param skill the skill to add to
     * @param value the value to add (can be negative to subtract skills)
     * @return if skill was properly added
     */
    fun addSkill(skill: Skill, value: Int): Boolean {
        if (value > skills[Skill.UNALLOCATED.ordinal] || skills[skill.ordinal] + value < 0) {
            return false
        }
        skills[skill.ordinal] += value
        if (skill != Skill.UNALLOCATED) {
            skills[Skill.UNALLOCATED.ordinal] -= value
        }
        return true
    }

    fun setShipType(type: ShipType) {
        ship = Spaceship(type)
    }

    override fun toString(): String {
        return ("Name: " + name + "\n"
                + "Difficulty: " + difficulty + "\n"
                + "Currency: " + currency + "\n"
                + "Ship: " + ship!!.name + "\n"
                + "Pilot level: " + getSkill(Skill.PILOT) + "\n"
                + "Trader level: " + getSkill(Skill.TRADER) + "\n"
                + "Fighter level: " + getSkill(Skill.FIGHTER) + "\n"
                + "Engineer level: " + getSkill(Skill.ENGINEER) + "\n")
    }

}
