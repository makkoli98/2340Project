package com.example.spacetraders.data.models;

import com.example.spacetraders.data.entity.Character;

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
            interactor.getCharacter().setCredits(interactor.getCharacter().getCredits() - transactionCost);
        } else {
            interactor.getCharacter().setCredits(interactor.getCharacter().getCredits() + transactionCost);
        }
    }
}
