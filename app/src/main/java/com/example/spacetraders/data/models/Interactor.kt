package com.example.spacetraders.data.models

import android.content.Context

import com.example.spacetraders.data.entity.Universe
import com.example.spacetraders.data.entity.Character


class Interactor private constructor() {
    var character: Character? = null
    var universe: Universe? = null
    private var id: Int = 0
    private var saveDatabase: SaveDatabase? = null

    init {
        id = -1
    }

    fun setSave(id: Int, saveDatabase: SaveDatabase) {
        this.id = id
        character = saveDatabase.getCharacter(id)
        universe = saveDatabase.getUniverse(id)
    }

    fun createSave(applicationContext: Context, character: Character, universe: Universe): Int {
        saveDatabase = SaveDatabase(applicationContext)
        id = saveDatabase!!.createNewSave()
        this.character = character
        this.universe = universe
        saveGame()
        return id
    }

    fun saveGame() {
        saveDatabase!!.saveCharacter(character!!, id)
        saveDatabase!!.saveUniverse(universe!!, id)
    }

    companion object {
        val interactor = Interactor()
    }


}
