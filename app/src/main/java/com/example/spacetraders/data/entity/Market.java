package com.example.spacetraders.data.entity;

import java.util.Random;

public class Market {
    private int[] resourcePrices = new int[Resources.values().length];
    private int[] resourceAmount = new int[Resources.values().length];

    public int[] getResourcePrices() { return resourcePrices; }

    public void setResourcePrices(int[] resourcePrices) { this.resourcePrices = resourcePrices; }

    public int[] getResourceAmount() { return resourceAmount; }

    public void setResourceAmount(int[] resourceAmount) { this.resourceAmount = resourceAmount; }

    public Market(TechLevel techLevel) {
        Random rand = new Random();

        for (int i = 0; i < resourcePrices.length; i++) {
            int basePrice = Resources.values()[i].getBasePrice();
            int IPL = Resources.values()[i].getIPL();
            int MTLP = Resources.values()[i].getMTLP();
            int Var = Resources.values()[i].getVar();

            if (rand.nextBoolean()) {
                Var = -1 * Var;
            }

            //(basePrice) + (IPL * (TechLevel - MTLP)) + (Var)
            resourcePrices[i] = basePrice + (IPL * (techLevel.ordinal() - MTLP)) + Var;

            int cap = 50;
            int techNum = techLevel.ordinal();
            if (i == 0 && techNum >= 0) {
                resourceAmount[i] = rand.nextInt(cap);
            } else if (i == 1 && techNum >= 0) {
                resourceAmount[i] = rand.nextInt(cap);
            } else if (i == 2 && techNum >= 1) {
                resourceAmount[i] = rand.nextInt(cap);
            } else if (i == 3 && techNum >= 2) {
                resourceAmount[i] = rand.nextInt(cap);
            } else if (i == 4 && techNum >= 3) {
                resourceAmount[i] = rand.nextInt(cap);
            } else if (i == 5 && techNum >= 3) {
                resourceAmount[i] = rand.nextInt(cap);
            } else if (i == 6 && techNum >= 4) {
                resourceAmount[i] = rand.nextInt(cap);
            } else if (i == 7 && techNum >= 4) {
                resourceAmount[i] = rand.nextInt(cap);
            } else if (i == 8 && techNum >= 5) {
                resourceAmount[i] = rand.nextInt(cap);
            } else if (i == 9 && techNum >= 6) {
                resourceAmount[i] = rand.nextInt(cap);
            }
        }
    }
}