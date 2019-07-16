package com.example.spacetraders.data.models;

import android.content.Context;

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

    public void setSave(int id, SaveDatabase saveDatabase) {
        this.id = id;
        character = saveDatabase.getCharacter(id);
        universe = saveDatabase.getUniverse(id);
    }

    public int createSave(Context applicationContext, Character character, Universe universe) {
        saveDatabase = new SaveDatabase(applicationContext);
        id = saveDatabase.createNewSave();
        this.character = character;
        this.universe = universe;
        saveGame(applicationContext);
        return id;
    }

    public void saveGame(Context applicationContext) {
        saveDatabase = new SaveDatabase(applicationContext);
        saveDatabase.saveCharacter(character, id);
        saveDatabase.saveUniverse(universe, id);
    }



}
