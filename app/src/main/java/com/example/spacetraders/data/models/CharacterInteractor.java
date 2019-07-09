package com.example.spacetraders.data.models;

import com.example.spacetraders.data.entity.Character;
import com.example.spacetraders.data.entity.GameDifficulty;
import com.example.spacetraders.data.entity.Planet;
import com.example.spacetraders.data.entity.PoliticalSystem;
import com.example.spacetraders.data.entity.ResourceLevel;
import com.example.spacetraders.data.entity.TechLevel;

public class CharacterInteractor {
    private Interactor interactor;

    /**
     * Allows the character interactor to affect the data held in Interactor.java
     * @param interactor The original interactor from Interactor.java that has all of the data for the app
     */
    public CharacterInteractor(Interactor interactor) {
        this.interactor = interactor;
    }

    /**
     * Stores the newly created character into the Interactor
     * @param character the newly created character
     */
    public void createCharacter(Character character) {
        interactor.setCharacter(character);
        interactor.getCharacter().setCurrentSolarSystem(interactor.getUniverse().getSystems()[0]);
    }

    public Character getCharacter() {return interactor.getCharacter();}

    /**
     * updates the currency of the character after each transaction
     * @param transactionCost cost of the transaction
     * @param Buying whether the character bought or sold
     */
    public void updateCurrency(int transactionCost, boolean Buying) {
        if (Buying) {
            interactor.getCharacter().setCurrency(interactor.getCharacter().getCurrency() - transactionCost);
        } else {
            interactor.getCharacter().setCurrency(interactor.getCharacter().getCurrency() + transactionCost);
        }
    }

    public void setName(String name) {interactor.getCharacter().setName(name);}

    public void setDifficulty(GameDifficulty difficulty) {interactor.getCharacter().setDifficulty(difficulty);}

    public TechLevel getCurrentTechLevel() {return interactor.getCharacter().getCurrentSolarSystem().getTechLevel();}

    public ResourceLevel getCurrentResourceLevel() {return interactor.getCharacter().getCurrentSolarSystem().getResources();}

    public PoliticalSystem getCurrentPoliticalSystem() {return interactor.getCharacter().getCurrentSolarSystem().getPoliticalSystem();}

    public String getCurrentSolarSystemName() {return interactor.getCharacter().getCurrentSolarSystem().getName();}

    public int getNumPlanets() {return interactor.getCharacter().getCurrentSolarSystem().getNumPlanets();}

    public Planet[] getPlanets() {return interactor.getCharacter().getCurrentSolarSystem().getPlanets();}

    public Planet getCurrentPlanet() {return interactor.getCharacter().getCurrentSolarSystem().getCurrentPlanet();}
}
