package com.example.spacetraders.data.entity;

public class Character {
    private String name;
    private int skillPoints;
    private GameDifficulty difficulty;
    private int credits;
    private Spaceship ship;

    public Character() {
        this("Bob", 16, GameDifficulty.BEGINNER, 1000, Spaceship.GNAT);
    }

    public Character(String nam, int sp, GameDifficulty diff, int creds, Spaceship shp) {
        name = nam;
        skillPoints = sp;
        difficulty = diff;
        credits = creds;
        ship = shp;
    }

    public String getName() {
        return name;
    }

    public void setName(String nam) {
        name = nam;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(int sp) {
        skillPoints = sp;
    }

    public GameDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(GameDifficulty diff) {
        difficulty = diff;
    }

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
}
