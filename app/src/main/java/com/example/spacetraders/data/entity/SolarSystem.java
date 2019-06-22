package com.example.spacetraders.data.entity;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class SolarSystem {
    private String name;
    private int techLevel;
    private int resources;
    private int politicalSystem;
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

    public int getTechLevel() {
        return techLevel;
    }

    public void setTechLevel(int techLevel) {
        this.techLevel = techLevel;
    }

    public int getResources() {
        return resources;
    }

    public void setResources(int resources) {
        this.resources = resources;
    }

    public int getPoliticalSystem() {
        return politicalSystem;
    }

    public void setPoliticalSystem(int politicalSystem) {
        this.politicalSystem = politicalSystem;
    }

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

        double[] techWeighting = new double[9];
        double[] resourceWeighting = new double[13];

        switch (gameDifficulty) {
            case BEGINNER:
                for (int i = 0; i < 9; i++) {
                    techWeighting[i] = .05 + ((11.0/720) * i);
                }
                for (int i = 0; i < 13; i++) {
                    if (i == 0) {
                        resourceWeighting[i] = .15;
                    } else if (i == 2 || i == 6 || i == 8) {
                        resourceWeighting[i] = .05;
                    } else {
                        resourceWeighting[i] = 7.0/90;
                    }
                }
                break;
            case EASY:
                for (int i = 0; i < 9; i++) {
                    techWeighting[i] = (.05 + (11.0/720*i) + (1.0/9)) / 2.0;
                }
                for (int i = 0; i < 13; i++) {
                    if (i == 0) {
                        resourceWeighting[i] = .15;
                    } else if (i == 2 || i == 6 || i == 8) {
                        resourceWeighting[i] = 1.0/12;
                    } else {
                        resourceWeighting[i] = 6.0/90;
                    }
                }
                break;
            case NORMAL:
                for (int i = 0; i < 9; i++) {
                    techWeighting[i] = 1.0/9;
                }
                for (int i = 0; i < 13; i++) {
                    if (i == 0) {
                        resourceWeighting[i] = .15;
                    } else if (i == 2 || i == 6 || i == 8) {
                        resourceWeighting[i] = 7.0/60;
                    } else {
                        resourceWeighting[i] = 5.0/90;
                    }
                }
                break;
            case HARD:
                for (int i = 0; i < 9; i++) {
                    techWeighting[i] = ((31.0/181) - (11.0/720*i) + (1.0/9))/2.0;
                }
                for (int i = 0; i < 13; i++) {
                    if (i == 0) {
                        resourceWeighting[i] = .20;
                    } else if (i == 2 || i == 6 || i == 8) {
                        resourceWeighting[i] = 2.0/15;
                    } else {
                        resourceWeighting[i] = 4.0/90;
                    }
                }
                break;
            case IMPOSSIBLE:
                for (int i = 0; i < 9; i++) {
                    techWeighting[i] = (31.0/181) - (11.0/720*i);
                }
                for (int i = 0; i < 13; i++) {
                    if (i == 0) {
                        resourceWeighting[i] = .25;
                    } else if (i == 2 || i == 6 || i == 8) {
                        resourceWeighting[i] = .15;
                    } else {
                        resourceWeighting[i] = 3.0/90;
                    }
                }
                break;
            default:
                for (int i = 0; i < 9; i++) {
                    techWeighting[i] = 1.0/9;
                }
                for (int i = 0; i < 13; i++) {
                    if (i == 0) {
                        resourceWeighting[i] = .15;
                    } else if (i == 2 || i == 6 || i == 8) {
                        resourceWeighting[i] = 7.0/60;
                    } else {
                        resourceWeighting[i] = 5.0/90;
                    }
                }
                break;
        }
        techLevel = getRandom(techWeighting);
        resources = getRandom(resourceWeighting);
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
}
