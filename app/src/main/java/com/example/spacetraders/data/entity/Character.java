package com.example.spacetraders.data.entity;
import java.util.ArrayList;

public class Character {
    private String name;
    private int[] skills;
    private GameDifficulty difficulty;
    private int currency;
    private Spaceship ship;
    private SolarSystem currentSolarSystem;
    private ArrayList<Mercenary> mercenaries;

    public Character() {
        this("NAME", GameDifficulty.BEGINNER, 10000, ShipType.GNAT);
    }

    /**
     * Constructor for the Character class
     *
     * @param nam Name of the character
     * @param diff Difficulty setting of the character
     * @param currency the currency of the character
     * @param shipType the shiptype of the character
     */
    public Character(String nam, GameDifficulty diff, int currency, ShipType shipType) {
        name = nam;
        difficulty = diff;
        this.currency = currency;
        ship = new Spaceship(shipType);
        skills = new int[Skill.values().length];
        skills[Skill.UNALLOCATED.ordinal()] = 16;
        mercenaries = new ArrayList<>();
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

    public int[] getSkills() {
        return skills;
    }

    /**
     *  If the skill is a valid value (eg, does not go over max skill), then adds the proper value
     *  to the specified skill. If the skill is invalid, returns false.
     *  This method should also be used to increase the number of UNALLOCATED skill points.
     * @param skill the skill to add to
     * @param value the value to add (can be negative to subtract skills)
     * @return if skill was properly added
     */
    public boolean addSkill(Skill skill, int value) {
        if(value > skills[Skill.UNALLOCATED.ordinal()] || skills[skill.ordinal()] + value < 0) {
            return false;
        }
        skills[skill.ordinal()] += value;
        if(skill != Skill.UNALLOCATED) {
            skills[Skill.UNALLOCATED.ordinal()] -= value;
        }
        return true;
    }

    public GameDifficulty getDifficulty() { return difficulty; }

    public void setDifficulty(GameDifficulty diff) { difficulty = diff; }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int amount) {
        currency = amount;
    }

    public Spaceship getShip() {
        return ship;
    }

    public void setShip(Spaceship shp) {
        ship = shp;
    }

    public void setShipType(ShipType type) {
        ship = new Spaceship(type);
    }

    public SolarSystem getCurrentSolarSystem() {
        return currentSolarSystem;
    }

    public void setCurrentSolarSystem(SolarSystem currentSolarSystem) {
        this.currentSolarSystem = currentSolarSystem;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\n"
                + "Difficulty: " + getDifficulty() + "\n"
                + "Currency: " + getCurrency() + "\n"
                + "Ship: " + getShip().getName() + "\n"
                + "Pilot level: " + getSkill(Skill.PILOT) + "\n"
                + "Trader level: " + getSkill(Skill.TRADER) + "\n"
                + "Fighter level: " + getSkill(Skill.FIGHTER) + "\n"
                + "Engineer level: " + getSkill(Skill.ENGINEER) + "\n";
    }

    public void addMercenary(Mercenary mercenary) {
        mercenaries.add(mercenary);
        int[] mercenarySkills = mercenary.getSkills();
        for (int i = 0; i < mercenarySkills.length; i++) {
            if (mercenarySkills[i] > skills[i]) {
                skills[i] = mercenarySkills[i];
            }
        }
    }

    public ArrayList<Mercenary> getMercenaries() {return mercenaries;}

    public void adjustPoints(int[] newSkillPoints) {
        for (int i = 0; i < newSkillPoints.length; i++) {
            if (skills[i] < newSkillPoints[i]) {
                skills[i] = newSkillPoints[i];
            }
        }
    }

    public int currShipWorth() {
        int currentShipWorth = 0;
        currentShipWorth += ship.getBasePrice();
        int[] currShipResources = ship.getCurrentResources();

        currentShipWorth += currShipResources[0] * Resources.WATER.getBasePrice();
        currentShipWorth += currShipResources[1] * Resources.FURS.getBasePrice();
        currentShipWorth += currShipResources[2] * Resources.FOOD.getBasePrice();
        currentShipWorth += currShipResources[3] * Resources.ORE.getBasePrice();
        currentShipWorth += currShipResources[4] * Resources.GAMES.getBasePrice();
        currentShipWorth += currShipResources[5] * Resources.FIREARMS.getBasePrice();
        currentShipWorth += currShipResources[6] * Resources.MEDICINE.getBasePrice();
        currentShipWorth += currShipResources[7] * Resources.MACHINES.getBasePrice();
        currentShipWorth += currShipResources[8] * Resources.NARCOTICS.getBasePrice();
        currentShipWorth += currShipResources[9] * Resources.ROBOTS.getBasePrice();

        return currentShipWorth;
    }

}
