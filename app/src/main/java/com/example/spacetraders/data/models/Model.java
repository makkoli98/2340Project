package com.example.spacetraders.data.models;

import java.util.Map;
import java.util.HashMap;


/**
 * Holds the different interactors and the save database
 * Each of the ViewModels uses this to get the appropriate Interactor
 */
public class Model {
    private SaveDatabase saveDatabase;
    private Map<String, Object> interactors;
    private Interactor repository;

    private static Model instance = new Model();

    /**
     * @return the instance of the model that holds all the interactors
     */
    public static Model getInstance() {return instance;}

    /**
     * Uses a hashmap to get the specific Interactor
     */
    private Model() {
        repository = Interactor.getInteractor();
        interactors = new HashMap<>();
        interactors.put("Character", new CharacterInteractor(repository));
        interactors.put("Universe", new UniverseInteractor(repository));
        interactors.put("Ship", new ShipInteractor(repository));
        interactors.put("Market", new MarketInteractor(repository));

    }

    public CharacterInteractor getCharacterInteractor() {return (CharacterInteractor) interactors.get("Character");}

    public UniverseInteractor getUniverseInteractor() {return (UniverseInteractor) interactors.get("Universe");}

    public ShipInteractor getShipInteractor() {return (ShipInteractor) interactors.get("Ship");}

    public MarketInteractor getMarketInteractor() {return (MarketInteractor) interactors.get("Market");}
}
