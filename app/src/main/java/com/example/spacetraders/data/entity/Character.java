package com.example.spacetraders.data.entity;

public class Character {
    private String name;
    /*
    private int skillPoints;
    private int pilotPts;
    private int traderPts;
    private int fighterPts;
    private int engineerPts;
    */
    private int[] skills;
    private GameDifficulty difficulty;
    private int credits;
    private Spaceship ship;

    /*
    /**
     * No-arg constructor for the Character class
     *
     * Name: Bob
     * Skill Points: 16
     * Game Difficulty: Beginner
     * Credits: 1000
     * Spaceship: Gnat
     */
    public Character() {
        this("Bob", 16, GameDifficulty.BEGINNER, 1000, new Spaceship(), 0, 0, 0, 0);
    }
*/
    /**
     * Constructor for the Character class
     *
     * @param nam Name of the character
     * @param sp Skill points remaining for the character to allocate
     * @param diff Difficulty setting of the character
     * @param creds Currency available to the character
     * @param shp The character's current ship
     */
    public Character(String nam, GameDifficulty diff, int creds, Spaceship shp, int pilot,
                     int trader, int fighter, int engineer) {
        name = nam;
        //skillPoints = sp;
        difficulty = diff;
        credits = creds;
        ship = shp;
        /*
        pilotPts = pilot;
        traderPts = trader;
        fighterPts = fighter;
        engineerPts = engineer;
        */
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

    /*
    public int getSkillPoints() { return skillPoints; }

    public void setSkillPoints(int sp) {
        skillPoints = sp;
    }

    public int getPilotPts() { return pilotPts; }

    public void setPilotPts(int pilot) { pilotPts = pilot; }

    public int getTraderPts() { return traderPts; }

    public void setTraderPts(int trader) { traderPts = trader; }

    public int getFighterPts() { return fighterPts; }

    public void setFighterPts(int fighter) { fighterPts = fighter; }

    public int getEngineerPts() { return engineerPts; }

    public void setEngineerPts(int engineer) { engineerPts = engineer; }
    */

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
}
