package com.example.spacetraders.data.entity;

import android.content.Context;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * The Save Database class is used to save the progress of any characters.
 * It should be instantiated twice, once at the app's launch and once at the app's closure.
 * At launch, either the createNewSave or selectSave method should be called. Do not call both.
 *
 * Each save is tracked with a numeric id, starting from 0 and increasing as more saves are made.
 * This ID mst be saved in order to save the data to the correct save file once the app closes.
 */
public class SaveDatabase {
    private int numSaves;
    private Gson gson;
    private File path;

    public SaveDatabase(Context applicationContext) {
        path = applicationContext.getFilesDir();

        File savesFolder = new File(path, "saves");
        savesFolder.mkdir();

        path = savesFolder;

        numSaves = savesFolder.listFiles().length;

        gson = new Gson();
    }

    public int createNewSave() {
        File saveDir = new File(path, "save"+numSaves);
        if(saveDir.mkdir()) {
            File uni = new File(path, "/save" + numSaves + "/universe.json");
            File cha = new File(path, "/save" + numSaves + "/character.json");
            try {
                uni.createNewFile();
                cha.createNewFile();
            } catch(IOException ioe) {
                System.err.println("Error creating universe or character json files");
            }
            numSaves++;
            return numSaves - 1;
        } else {
            System.err.println("Error creating specific save directory");
        }
        return -1;
    }

    public void saveUniverse(Universe universe, int id) {
        if (!validId(id)) {
            throw new IllegalArgumentException("Attempted to save to a non-existent save file");
        }

        String contents = gson.toJson(universe);
        try {
            File uniJson = new File(path, "/save" + id + "/universe.json");
            FileOutputStream writer = new FileOutputStream(uniJson);
            writer.write(contents.getBytes());
            writer.close();
        } catch(Exception e) {
            System.err.println(e);
        }
    }

    public void saveCharacter(Character character, int id) {
        if (!validId(id)) {
            throw new IllegalArgumentException("Attempted to save to a non-existent save file");
        }

        String contents = gson.toJson(character);
        try {
            File chaJson = new File(path, "/save" + id + "/character.json");
            chaJson.createNewFile();
            FileOutputStream writer = new FileOutputStream(chaJson);
            writer.write(contents.getBytes());
            writer.close();
        } catch(Exception e) {
            System.err.println(e);
        }
    }

    public Universe getUniverse(int id) {
        if (!validId(id)) {
            throw new IllegalArgumentException("Attempted to get a non-existent save file");
        }
        try {
            File uniJson = new File(path, "/save" + id + "/universe.json");
            byte[] data = new byte[(int) uniJson.length()];
            FileInputStream reader = new FileInputStream(uniJson);
            reader.read(data);
            String contents = new String(data);
            reader.close();
            if (!contents.equals("")) {
                return gson.fromJson(contents, Universe.class);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    public Character getCharacter(int id) {
        if (!validId(id)) {
            throw new IllegalArgumentException("Attempted to get a non-existent save file");
        }
        try {
            File uniJson = new File(path, "/save" + id + "/character.json");
            byte[] data = new byte[(int) uniJson.length()];
            FileInputStream reader = new FileInputStream(uniJson);
            reader.read(data);
            String contents = new String(data);
            reader.close();
            if (!contents.equals("")) {
                return gson.fromJson(contents, Character.class);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    public boolean validId(int id) {
        return -1 < id && id < numSaves;
    }

    public int getNumSaves() {
        return numSaves;
    }

}
