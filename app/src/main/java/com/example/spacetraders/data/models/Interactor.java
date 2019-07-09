package com.example.spacetraders.data;

import android.content.Context;

import com.example.spacetraders.data.entity.GameDifficulty;
import com.example.spacetraders.data.entity.SaveDatabase;
import com.example.spacetraders.data.entity.Spaceship;
import com.example.spacetraders.data.entity.Universe;
import com.example.spacetraders.data.entity.Character;


public class Interactor {
    private static Interactor interactorInstance = new Interactor();
    private Character character;
    private Universe universe;
    private int id;
    private SaveDatabase saveDatabase;

    private Interactor() {
        id = -1;
    }

    public static Interactor getInteractor() {
        return interactorInstance;
    }

    public void setSaveDatabase(SaveDatabase saveDatabase) {
        this.saveDatabase = saveDatabase;
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    public Universe getUniverse() {
        return universe;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

    public void setSave(int id) {
        this.id = id;
    }

    public int createSave(Context applicationContext) {
        saveDatabase = new SaveDatabase(applicationContext);
        id = saveDatabase.getNumSaves() - 1;
        return id;
    }

    public void saveGame() {

    }



}
