package com.example.spacetraders.data.entity;

public class Character {
    private String name;
    private int[] skills;
    private GameDifficulty difficulty;
    private int credits;
    private Spaceship ship;
    private SolarSystem currentSolarSystem;


    /**
     * Constructor for the Character class
     *
     * @param nam Name of the character
     * @param diff Difficulty setting of the character
     * @param creds Currency available to the character
     */
    public Character(String nam, GameDifficulty diff, int creds, ShipType shipType, int pilot,
                     int trader, int fighter, int engineer) {
        name = nam;
        difficulty = diff;
        credits = creds;
        ship = new Spaceship(shipType);
        skills = new int[Skill.values().length];
        skills[Skill.PILOT.ordinal()] = pilot;
        skills[Skill.TRADER.ordinal()] = trader;
        skills[Skill.FIGHTER.ordinal()] = fighter;
        skills[Skill.ENGINEER.ordinal()] = engineer;
        skills[Skill.UNALLOCATED.ordinal()] = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String nam) {
        name = nam;
    }

    public int getSkill(Skill skill) {
        return skills[skill.ordinal()];
    }

    public void setSkill(Skill skill, int value) {
        skills[skill.ordinal()] = value;
    }

    public GameDifficulty getDifficulty() { return difficulty; }

    public void setDifficulty(GameDifficulty diff) { difficulty = diff; }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int creds) {
        credits = creds;
    }

    public Spaceship getShip() {
        return ship;
    }

    public void setShip(Spaceship shp) {
        ship = shp;
    }

    public SolarSystem getCurrentSolarSystem() {
        return currentSolarSystem;
    }

    public void setCurrentSolarSystem(SolarSystem currentSolarSystem) {
        this.currentSolarSystem = currentSolarSystem;
    }
}
