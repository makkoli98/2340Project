package com.example.spacetraders.data.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

import com.example.spacetraders.data.entity.GameDifficulty
import com.example.spacetraders.data.entity.Universe
import com.example.spacetraders.data.models.CharacterInteractor
import com.example.spacetraders.data.models.Interactor
import com.example.spacetraders.data.models.Model
import com.example.spacetraders.data.entity.Character
import com.example.spacetraders.data.models.ShipInteractor
import com.example.spacetraders.data.models.UniverseInteractor

class NewCharacterViewModel
/**
 * Creates ViewModel that will deal with info from the new character activity
 * @param application no clue what this means
 */
(application: Application) : AndroidViewModel(application) {
    private val characterInteractor: CharacterInteractor
    private val universeInteractor: UniverseInteractor

    val character: Character?
        get() = characterInteractor.character

    val universe: Universe?
        get() = universeInteractor.universe


    init {
        characterInteractor = Model.instance.characterInteractor
        universeInteractor = Model.instance.universeInteractor
    }

    /**
     * creates the character and stores it into the Model Interactor
     * @param character the character data that is the new character
     */
    fun createCharacter(character: Character) {
        characterInteractor.createCharacter(character)
    }

    fun setName(name: String) {
        characterInteractor.setName(name)
    }

    fun setDifficulty(difficulty: GameDifficulty) {
        characterInteractor.setDifficulty(difficulty)
    }

    /**
     * Gets the Universe and stores it to the Universe Interactor
     * @param universe the Universe that was randomly generated at character creation
     */
    fun createUniverse(universe: Universe) {
        universeInteractor.createUniverse(universe)
    }
}
