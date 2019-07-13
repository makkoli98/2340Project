package com.example.spacetraders.data.entity;

public enum Weapons {

    SONICRAY("Sonic Ray", 250, 5),
    PLASMARAY("Plasma Ray", 500, 15),
    MESONPHASER("Meson Phaser", 750, 25);

    private final String name;
    private final int cost;
    private final int damage;

    Weapons(String weaponName, int weaponCost, int damageCount) {
        name = weaponName;
        cost = weaponCost;
        damage = damageCount;
    }
    
    public String getWeaponName() {
        return name;
    }
    public int getWeaponCost() {
        return cost;
    }
    public int getDamageCount() {
        return damage;
    }




}
