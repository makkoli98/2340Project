package com.example.spacetraders.data.entity;

import java.util.Random;

/**
 * Market Class
 *
 * Handles resource prices and amounts
 */
public class Market {
    private int[] resourcePrices = new int[Resources.values().length];
    private int[] resourceAmount = new int[Resources.values().length];

    public int[] getResourcePrices() { return resourcePrices; }

    public void setResourcePrices(int[] resourcePrices) { this.resourcePrices = resourcePrices; }

    public int[] getResourceAmount() { return resourceAmount; }

    public void setResourceAmount(int[] resourceAmount) { this.resourceAmount = resourceAmount; }

    /**
     * Market class constructor. Uses the tech level of the system to generate market values
     * The amount of each resource is randomized between 0 and the cap value.
     *
     * @param techLevel the tech level of the system
     */
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

    /**
     * Adjusts market prices based on the resource level of the system.
     * Negative resource levels multiply price by 0.8
     * Positive resource levels multiply price by 1.2
     *
     * @param resourceLevel
     */
    public void changeResourceLevel(ResourceLevel resourceLevel) {
        switch (resourceLevel) {
            case LOTSOFWATER:
                resourcePrices[Resources.WATER.ordinal()] *= 0.8;
                break;
            case RICHFAUNA:
                resourcePrices[Resources.FURS.ordinal()] *= 0.8;
                break;
            case RICHSOIL:
                resourcePrices[Resources.FOOD.ordinal()] *= 0.8;
                break;
            case MINERALRICH:
                resourcePrices[Resources.ORE.ordinal()] *= 0.8;
                break;
            case ARTISTIC:
                resourcePrices[Resources.GAMES.ordinal()] *= 0.8;
                break;
            case WARLIKE:
                resourcePrices[Resources.FIREARMS.ordinal()] *= 0.8;
                break;
            case LOTSOFHERBS:
                resourcePrices[Resources.MEDICINE.ordinal()] *= 0.8;
                break;
            case WEIRDMUSHROOMS:
                resourcePrices[Resources.NARCOTICS.ordinal()] *= 0.8;
                break;
            case DESERT:
                resourcePrices[Resources.WATER.ordinal()] *= 1.2;
                break;
            case LIFELESS:
                resourcePrices[Resources.FURS.ordinal()] *= 1.2;
                break;
            case POORSOIL:
                resourcePrices[Resources.FOOD.ordinal()] *= 1.2;
                break;
            case MINERALPOOR:
                resourcePrices[Resources.ORE.ordinal()] *= 1.2;
                break;
            default:
                break;
        }
    }

    /**
     * Adjusts market prices based on a radical event
     * As all of these are negative events,
     * prices are doubled during these events
     *
     * @param IE Radically price increasing event
     */
    public void changeEvent(RadicalEvent IE) {
        switch (IE) {
            case DROUGHT:
                resourcePrices[Resources.WATER.ordinal()] *= 2;
                break;
            case COLD:
                resourcePrices[Resources.FURS.ordinal()] *= 2;
                break;
            case CROPFAIL:
                resourcePrices[Resources.FOOD.ordinal()] *= 2;
                break;
            case WAR:
                resourcePrices[Resources.ORE.ordinal()] *= 2;
                resourcePrices[Resources.FIREARMS.ordinal()] *= 2;
                break;
            case BOREDOM:
                resourcePrices[Resources.GAMES.ordinal()] *= 2;
                resourcePrices[Resources.NARCOTICS.ordinal()] *= 2;
                break;
            case PLAGUE:
                resourcePrices[Resources.MEDICINE.ordinal()] *= 2;
                break;
            case LACKOFWORKERS:
                resourcePrices[Resources.MACHINES.ordinal()] *= 2;
                resourcePrices[Resources.ROBOTS.ordinal()] *= 2;
                break;
            default:
                break;
        }
    }
}