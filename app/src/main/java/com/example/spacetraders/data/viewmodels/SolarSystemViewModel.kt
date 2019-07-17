package com.example.spacetraders.data.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

import com.example.spacetraders.data.entity.Planet
import com.example.spacetraders.data.entity.PoliticalSystem
import com.example.spacetraders.data.entity.ResourceLevel
import com.example.spacetraders.data.entity.TechLevel
import com.example.spacetraders.data.entity.Character

import com.example.spacetraders.data.models.CharacterInteractor
import com.example.spacetraders.data.models.Interactor
import com.example.spacetraders.data.models.Model

class SolarSystemViewModel
/**
 * Gets the character interactor which holds the solar system data
 * @param application ???
 */
(application: Application) : AndroidViewModel(application) {
    private val characterInteractor: CharacterInteractor

    val techLevel: TechLevel?
        get() = characterInteractor.currentTechLevel

    val resourceLevel: ResourceLevel?
        get() = characterInteractor.currentResourceLevel

    val politicalSystem: PoliticalSystem?
        get() = characterInteractor.currentPoliticalSystem

    val name: String?
        get() = characterInteractor.currentSolarSystemName

    val numPlanets: Int
        get() = characterInteractor.numPlanets

    val planets: Array<Planet?>?
        get() = characterInteractor.planets

    val character: Character?
        get() = characterInteractor.character

    val currentPlanet: Planet?
        get() = characterInteractor.currentPlanet

    init {
        characterInteractor = Model.instance.characterInteractor
    }
}
