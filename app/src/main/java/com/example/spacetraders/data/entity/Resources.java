package com.example.spacetraders.data.entity;

/**
 * Enum class holding the name of the resources
 * Use for indexing resources in resource array
 * e.g.
 *     int[] resources = new int[Resources.values().length]
 */
public enum Resources {
    WATER ("Water", 30, 0, 0, 2, 3, 4, 30, 50),
    FURS ("Furs", 250, 0, 0, 0, 10, 10, 230, 280),
    FOOD ("Food", 100, 1, 0, 1, 5, 5, 90,160),
    ORE ("Ore", 350, 2, 2, 3, 20, 10, 350, 420),
    GAMES ("Games", 250, 3, 1, 6, -10, 5, 160, 270),
    FIREARMS ("Firearms", 1250, 3, 1, 5, -75, 100, 600, 1100),
    MEDICINE ("Medicine", 650, 4, 1, 6, -20, 10, 400, 700),
    MACHINES ("Machines", 900, 4, 3, 5, -30, 5, 600, 800),
    NARCOTICS ("Narcotics", 3500, 5, 0, 5, -125, 150, 2000, 3000),
    ROBOTS ("Robots", 5000, 6, 4, 7, -150, 100, 3500, 5000);

    private final String name;

    private final int basePrice;

    private final int MTLP;

    private final int MTLU;

    private final int TTP;

    private final int IPL;

    private final int Var;

    private final int MTL;

    private final int MTH;

    Resources(String name, int basePrice, int MTLP, int MTLU, int TTP, int IPL, int Var, int MTL, int MTH) {
        this.name = name;
        this.basePrice = basePrice;
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.TTP = TTP;
        this.IPL = IPL;
        this.Var = Var;
        this.MTL = MTL;
        this.MTH = MTH;
    }

    public String getName() { return name; }

    public int getBasePrice() { return basePrice; }

    public int getMTLP() {
        return MTLP;
    }

    public int getMTLU() {
        return MTLU;
    }

    public int getTTP() {
        return TTP;
    }

    public int getIPL() {
        return IPL;
    }

    public int getVar() {
        return Var;
    }

    public int getMTL() {
        return MTL;
    }

    public int getMTH() {
        return MTH;
    }
}
