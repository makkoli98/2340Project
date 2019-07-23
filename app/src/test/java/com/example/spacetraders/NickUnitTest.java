package com.example.spacetraders;

import com.example.spacetraders.data.entity.GameDifficulty;
import com.example.spacetraders.data.entity.Mercenary;
import com.example.spacetraders.data.entity.ShipType;
import com.example.spacetraders.data.entity.Character;


import org.junit.Test;

import static org.junit.Assert.*;

public class NickUnitTest {

    @Test
    public void addMercenary() {
        Mercenary test = new Mercenary();
        test.setName("Charles");
        int[] mercenary_skills = test.getSkills();
        int[] successful_test = {mercenary_skills[0], mercenary_skills[1], mercenary_skills[2], mercenary_skills[3], 16};

        Character solda = new Character(new String("BOI"), GameDifficulty.BEGINNER, 10000, ShipType.FLEA);
        int[] characterSkillPoints = solda.getSkills();

        System.out.println("Skill points before addition:");
        for (int i = 0; i < characterSkillPoints.length; i++) {
            System.out.println(characterSkillPoints[i]);
        }

        System.out.println("Mercenary points:");
        for (int i = 0; i < mercenary_skills.length; i++) {
            System.out.println(mercenary_skills[i]);
        }

        solda.addMercenary(test);

        System.out.println("Skill Points After addition of Mercenary");
        for (int i = 0; i < solda.getSkills().length; i++) {
            System.out.println(solda.getSkills()[i]);
        }

        assertEquals(1, (double)(solda.getMercenaries().size()), 0);
        assertArrayEquals(successful_test, solda.getSkills());



    }

    @Test
    public void adjustPoints() {
        Character boi = new Character("GIGI", GameDifficulty.BEGINNER, 10000, ShipType.FLEA);
        //PILOT, TRADER, FIGHTER, ENGINEER, UNALLOCATED;

        int[] skillPoints = boi.getSkills();

        int[] failedUpdate= {4, 3,1, 2, 0};//this shouldn't work bc it only updates if the values are greater than the previous

        int[] success_test = {4, 3, 1, 2, 16};

        System.out.println("BEFORE:");
        for (int i = 0; i < skillPoints.length; i++) {
            System.out.println(skillPoints[i]);
        }

        boi.adjustPoints(failedUpdate);

        System.out.println("AFTER:");
        for (int i = 0; i < skillPoints.length; i++) {
            System.out.println(skillPoints[i]);
        }


        assertArrayEquals(success_test, boi.getSkills());

    }

    @Test
    public void adjustPoints2() {
        Character boi = new Character("GIGI", GameDifficulty.BEGINNER, 10000, ShipType.FLEA);
        //PILOT, TRADER, FIGHTER, ENGINEER, UNALLOCATED;
        int[] skillPoints = boi.getSkills();

        int[] successfulUpdate = {6, 5, 0, 2, 17};

        System.out.println("BEFORE:");
        for (int i = 0; i < skillPoints.length; i++) {
            System.out.println(skillPoints[i]);
        }

        boi.adjustPoints(successfulUpdate);
        System.out.println("AFTER:");
        for (int i = 0; i < skillPoints.length; i++) {
            System.out.println(skillPoints[i]);
        }
        assertArrayEquals(successfulUpdate, skillPoints);

    }

    @Test
    public void setCurrency() {
        Character boi = new Character("NICK", GameDifficulty.BEGINNER, 10000, ShipType.FLEA);

        int newCredits = 5000;
        boi.setCurrency(5000);
        assertEquals(5000, boi.getCurrency());

    }

    @Test
    public void setCurrency2() {
        Character ye = new Character("YE", GameDifficulty.BEGINNER, 10000, ShipType.FLEA);
        int new_credits = -5000;
        ye.setCurrency(-5500);
        assertEquals(0, ye.getCurrency());
    }
}