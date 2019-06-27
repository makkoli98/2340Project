package com.example.spacetraders.data.entity;

import java.util.Random;

public class Market {
    private int[] resourcePrices = new int[Resources.values().length];

    public int[] getResourcePrices() { return resourcePrices; }

    public void setResourcePrices(int[] resourcePrices) { this.resourcePrices = resourcePrices; }

    public Market(TechLevel techLevel) {
        Random rand = new Random();

        //(basePrice) + (IPL * (TechLevel - MTLP)) + (Var)
        for (int i = 0; i < resourcePrices.length; i++) {
            int basePrice = Resources.values()[i].getBasePrice();
            int IPL = Resources.values()[i].getIPL();
            int MTLP = Resources.values()[i].getMTLP();
            int Var = Resources.values()[i].getVar();

            if (rand.nextBoolean()) {
                Var = -1 * Var;
            }

            resourcePrices[i] = basePrice + (IPL * (techLevel.ordinal() - MTLP)) + Var;
        }
    }
}