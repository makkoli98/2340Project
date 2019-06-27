package com.example.spacetraders.data.entity;

public enum TechLevel {
    PRE_AGRICULTURAL ("Pre-Agricultural"),
    AGRICULTURAL ("Agricultural"),
    MEDIEVAL ("Medieval"),
    RENAISSANCE ("Renaissance"),
    EARLY_INDUSTRIAL ("Early-Industrial"),
    INDUSTRIAL ("Industrial"),
    POST_INDUSTRIAL ("Post-Industrial"),
    HI_TECH ("Hi-Tech");

    private final String name;

    TechLevel(String name) { this.name = name; }

    public String getName() { return name; }
}
