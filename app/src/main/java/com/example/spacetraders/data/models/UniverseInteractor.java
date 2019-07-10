package com.example.spacetraders.data.models;

import com.example.spacetraders.data.entity.Universe;

public class UniverseInteractor {
    private Interactor interactor;

    /**
     * allows UniverseInteractor to affect the universe info in Interactor.java
     * @param interactor Interactor.java
     */
    public UniverseInteractor(Interactor interactor) {
        this.interactor = interactor;
    }

    /**
     * Stores the newly created universe into the Interactor
     * @param universe the newly created universe
     */
    public void createUniverse(Universe universe) {
        interactor.setUniverse(universe);
    }

    public Universe getUniverse() {return interactor.getUniverse();}
}
