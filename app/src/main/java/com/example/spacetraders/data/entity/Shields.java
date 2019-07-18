package com.example.spacetraders.data.entity;

public enum Shields {

    GOLDEN_DYNASTY("Golden Dynasty", 750, 100),
    SILVER_DYNASTY("Silver Dynasty", 350, 50);

    private String shieldName;
    private int cost;
    private int healthBoost;

    Shields(String name, int price, int boost) {
        shieldName = name;
        cost = price;
        healthBoost = boost;
    }

    public String getName() {
        return shieldName;
    }
    public int getCost() {
        return cost;
    }
    public int getHealthBoost() {
        return healthBoost;
    }

}
