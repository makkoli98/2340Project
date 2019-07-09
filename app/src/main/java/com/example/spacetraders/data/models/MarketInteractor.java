package com.example.spacetraders.data.models;

public class MarketInteractor {
    private Interactor interactor;

    /**
     * creates market interactor for handling any thing with the market such as resource prices/amounts
     * @param interactor Interactor.java
     */
    public MarketInteractor(Interactor interactor) {
        this.interactor = interactor;
    }

    public int[] getResourcePrices() {return interactor.getCharacter().getCurrentSolarSystem().getPlanets()[0].getMarket().getResourcePrices();}

    public int[] getResourceAmounts() {return interactor.getCharacter().getCurrentSolarSystem().getPlanets()[0].getMarket().getResourceAmount();}

    public void setResourceAmounts(int[] resourceAmounts) {interactor.getCharacter().getCurrentSolarSystem().getPlanets()[0].getMarket().setResourceAmount(resourceAmounts);}

    public void setResourcePrices(int[] resourcePrices) {interactor.getCharacter().getCurrentSolarSystem().getPlanets()[0].getMarket().setResourcePrices(resourcePrices);}
}
