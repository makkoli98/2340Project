package com.example.spacetraders.data.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.view.Display;

import com.example.spacetraders.data.entity.Spaceship;
import com.example.spacetraders.data.models.CharacterInteractor;
import com.example.spacetraders.data.models.MarketInteractor;
import com.example.spacetraders.data.models.Model;
import com.example.spacetraders.data.models.ShipInteractor;
import com.example.spacetraders.data.entity.Character;

public class MarketViewModel extends AndroidViewModel {
    private ShipInteractor shipInteractor;
    private CharacterInteractor characterInteractor;
    private MarketInteractor marketInteractor;

    /**
     * Creates ViewModel that will deal with info from the marketplace trading
     * @param application idk what this is
     */
    public MarketViewModel(@NonNull Application application) {
        super(application);
        shipInteractor = Model.getInstance().getShipInteractor();
        characterInteractor = Model.getInstance().getCharacterInteractor();
        marketInteractor = Model.getInstance().getMarketInteractor();
    }

    /**
     * Uses the ship interactor to set the correct resources to the ship
     * @param resourceInputs the resource amounts of the transaction
     * @param Buying whether the character bought or sold
     */
    public void setResource(int[] resourceInputs, boolean Buying) { shipInteractor.setResource(resourceInputs, Buying);}

    public Spaceship getShip() {return shipInteractor.getShip();}

    public Character getCharacter() {return characterInteractor.getCharacter();}

    /**
     * uses character interactor to update the characters currency after each transaction
     * @param transactionCost cost of transaction
     * @param Buying whether the character bought or sold
     */
    public void updateCurrency(int transactionCost, boolean Buying) {
        characterInteractor.updateCurrency(transactionCost, Buying);
    }

    public int[] getResourceAmounts() {return marketInteractor.getResourceAmounts();}

    public int[] getResourcePrices() {return marketInteractor.getResourcePrices();}

    public int[] getCurrentResources() {return shipInteractor.getCurrentResources();}

    public void setResourceAmounts(int[] newAmounts) {marketInteractor.setResourceAmounts(newAmounts);}

    public int getCargoSize() {return shipInteractor.getShip().getCargoSize();}
}
