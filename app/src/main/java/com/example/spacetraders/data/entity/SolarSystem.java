package com.example.spacetraders.data.entity;
import android.graphics.Point;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class SolarSystem {
    private String name;
    private TechLevel techLevel;
    private ResourceLevel resourceLevel;
    private PoliticalSystem politicalSystem;
    private Planet[] planets;
    private int numPlanets;
    private int[] coords;
    private String[] possibleNames = {"Acamar", "Adahn", "Aldea", "Andevian", "Antedi", "Balosnee",
            "Baratas", "Brax", "Bretel", "Calondia", "Campor", "Capelle", "Carzon", "Castor",
            "Cestus", "Cheron", "Courteney", "Daled", "Damast", "Davlos", "Deneb", "Deneva",
            "Devidia", "Draylon", "Drema", "Endor", "Esmee", "Exo", "Ferris", "Festen", "Fourmi",
            "Frolix", "Gemulon", "Guinifer", "Hades", "Hamlet", "Helena", "Hulst", "Iodine",
            "Iralius", "Janus", "Japori", "Jarada", "Jason", "Kaylon", "Khefka", "Kira", "Klaatu",
            "Klaestron", "Korma", "Kravat", "Krios", "Laertes", "Largo", "Lave", "Ligon", "Lowry",
            "Magrat", "Malcoria", "Melina", "Mentar", "Merik", "Mintaka", "Montor", "Mordan",
            "Myrthe", "Nelvana", "Nix", "Nyle", "Odet", "Og", "Omega", "Omphalos", "Orias",
            "Othello", "Parade", "Penthara", "Picard", "Pollux", "Quator", "Rakhar", "Ran",
            "Regulas", "Relva", "Rhymus", "Rochani", "Rubicum", "Rutia", "Sarpeidon", "Sefalla",
            "Seltrice", "Sigma", "Sol", "Somari", "Stakoron", "Styris", "Talani", "Tamus",
            "Tantalos", "Tanuga", "Tarchannen", "Terosa", "Thera", "Titan", "Torin", "Triacus",
            "Turkana", "Tyrus", "Umberlee", "Utopia", "Vadera", "Vagra", "Vandor", "Ventax",
            "Xenon", "Xerxes", "Yew", "Yojimbo", "Zalkon", "Zuul"};
    private static List<String> SolarSystemNames = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TechLevel getTechLevel() { return techLevel; }

    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
    }

    public ResourceLevel getResources() {
        return resourceLevel;
    }

    public void setResources(ResourceLevel resourceLevel) {
        this.resourceLevel = resourceLevel;
    }

    public PoliticalSystem getPoliticalSystem() {
        return politicalSystem;
    }

    public void setPoliticalSystem(PoliticalSystem poliSys) { this.politicalSystem = poliSys; }

    public Planet[] getPlanets() { return planets; }

    public void setPlanets(Planet[] planets) { this.planets = planets; }

    public int getNumPlanets() { return numPlanets; }

    public void setNumPlanets(int numPlanets) { this.numPlanets = numPlanets; }

    public int[] getCoords() { return coords; }

    public void setCoords(int[] coords) { this.coords = coords; }

    public int getDistance(SolarSystem system) {
        int[] otherCoords = system.getCoords();
        double x = otherCoords[0] - coords[0];
        double y = otherCoords[1] - coords[1];
        return Math.round(Math.round(Math.hypot(x, y)));
    }

    @Override
    public String toString() { return name; }

    public SolarSystem(GameDifficulty gameDifficulty) {
        Random rand = new Random();
        String possibleName = possibleNames[rand.nextInt(possibleNames.length)];
        while (true) {
            if (!(SolarSystemNames.contains(possibleName))) {
                name = possibleName;
                SolarSystemNames.add(possibleName);
                break;
            } else {
                possibleName = possibleNames[rand.nextInt(possibleNames.length)];
            }
        }

        int[] techWeights = null;
        int[] resourceWeights = null;
        switch(gameDifficulty) {
            case BEGINNER:
                techWeights = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
                resourceWeights = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
                break;
            case EASY:
                techWeights = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
                resourceWeights = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
                break;
            case NORMAL:
                techWeights = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
                resourceWeights = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
                break;
            case HARD:
                techWeights = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
                resourceWeights = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
                break;
            case IMPOSSIBLE:
                techWeights = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
                resourceWeights = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
                break;
        }

        if(techWeights.length != TechLevel.values().length) {
            System.err.println("Tech Weights do not match Tech Levels");
        }
        if(resourceWeights.length != ResourceLevel.values().length) {
            System.err.println("Resource Weights do not match Resources");
        }

        techLevel = TechLevel.values()[getSelection(techWeights)];
        resourceLevel = ResourceLevel.values()[getSelection(resourceWeights)];

        Random rand2 = new Random();
        politicalSystem = PoliticalSystem.values()[rand2.nextInt(PoliticalSystem.values().length)];
    }

    public static int getRandom(double[] weights) {
        Random rand = new Random();
        double x = rand.nextDouble();
        for (int i = 0; i < weights.length; i++) {
            if (x <= weights[i]) {
                return i;
            } else {
                x -= weights[i];
            }
        }
        return rand.nextInt(weights.length);
    }

    private int getSelection(int[] weights) {
        int total = 0;
        for(int val: weights) total += val;
        int r = new Random().nextInt(total);
        int selection = 0;
        for(int i = 0; i < weights.length; i++) {
            selection += weights[i];
            if(r <= selection) return i;
        }
        return weights.length - 1;
    }

    /**
     * Generates a list of planets populating the solar system
     * Each planet is given the name "Planet1", "Planet2"...
     * Except for Planet1, which is given the same name as the solar system
     * and is set as the home planet of the system
     *
     * @param numPlanetLimit maximum number of planets a solar system can have
     * @return a list of the created planets
     */
    public Planet[] generatePlanets(int numPlanetLimit) {
        Random rand = new Random();
        int numPlanets = rand.nextInt(numPlanetLimit) + 1;
        Planet[] newPlanets = new Planet[numPlanets];

        for (int i = 0; i < numPlanets; i++) {
            if (i == 0) {
                newPlanets[i] = new Planet(name);
                newPlanets[i].setHome(true);
            }
            newPlanets[i] = new Planet("Planet" + Integer.valueOf(i + 1));
            Market market = new Market(techLevel);
            market.changeResourceLevel(resourceLevel);
            if (rand.nextBoolean()) {
                market.changeEvent(RadicalEvent.values()[rand.nextInt(RadicalEvent.values().length)]);
            }
            newPlanets[i].setMarket(market);
        }

        this.numPlanets = numPlanets;
        this.planets = newPlanets;
        return newPlanets;
    }
}
