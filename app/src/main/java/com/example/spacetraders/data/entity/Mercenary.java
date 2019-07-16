package com.example.spacetraders.data.entity;

import java.util.Arrays;
import java.util.Random;

import com.example.spacetraders.data.entity.Skill;

public class Mercenary {

    private int[] skills =  new int[4];
    private int price;
    private String name;

    /**
     * Creates a mercenary with a random skill level of 4 - 8 for each skill
     * The price is calculated by taking the sum of the skill levels and multiplying by 100
     */
    public Mercenary() {
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            skills[i] = rand.nextInt(5) + 4;
        }
        int sum = Arrays.stream(skills).sum();
        price = sum * 100;
    }

    public int[] getSkills() {return skills;}

    public int getPrice() {return price;}

    public void setPrice(int price) {this.price = price;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    @Override
    public String toString() {
        return "Pilot: " + skills[0] + "\nTrader: " + skills[1] + "\nFighter: " + skills[2] + "\nEngineer: " + skills[3] + "\nCost: " + price;
    }
}
