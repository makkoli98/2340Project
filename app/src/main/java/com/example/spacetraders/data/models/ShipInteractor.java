package com.example.spacetraders.data.models;

import com.example.spacetraders.data.entity.Spaceship;

public class ShipInteractor {
    private Interactor interactor;

    /**
     * Allows for ShipInteractor to affect ship data in Interactor.java
     * @param interactor Interactor.java
     */
    public ShipInteractor(Interactor interactor) {
        this.interactor = interactor;
    }

    /**
     * Sets the new resources on the ship using the current setResource method in the Ship class
     * @param newResources the int array of the resources bought/sold by the character
     * @param Buying whether or not the character was buying or selling
     */
    public void setResource(int[] newResources, boolean Buying) {
        interactor.getCharacter().getShip().setResource(newResources, Buying);
    }

    public Spaceship getShip() {return interactor.getCharacter().getShip();}

    public int[] getCurrentResources() {return interactor.getCharacter().getShip().getCurrentResources();}
}
