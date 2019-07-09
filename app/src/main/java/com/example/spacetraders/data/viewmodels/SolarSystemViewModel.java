package com.example.spacetraders.data.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetraders.data.entity.Planet;
import com.example.spacetraders.data.entity.PoliticalSystem;
import com.example.spacetraders.data.entity.ResourceLevel;
import com.example.spacetraders.data.entity.TechLevel;
import com.example.spacetraders.data.entity.Character;

import com.example.spacetraders.data.models.CharacterInteractor;
import com.example.spacetraders.data.models.Interactor;
import com.example.spacetraders.data.models.Model;

public class SolarSystemViewModel extends AndroidViewModel {
    private CharacterInteractor characterInteractor;

    /**
     * Gets the character interactor which holds the solar system data
     * @param application ???
     */
    public SolarSystemViewModel(@NonNull Application application) {
        super(application);
        characterInteractor = Model.getInstance().getCharacterInteractor();
    }

    public TechLevel getTechLevel() {return characterInteractor.getCurrentTechLevel();}

    public ResourceLevel getResourceLevel() {return characterInteractor.getCurrentResourceLevel();}

    public PoliticalSystem getPoliticalSystem() {return characterInteractor.getCurrentPoliticalSystem();}

    public String getName() {return characterInteractor.getCurrentSolarSystemName();}

    public int getNumPlanets() {return characterInteractor.getNumPlanets();}

    public Planet[] getPlanets() {return characterInteractor.getPlanets(); }

    public Character getCharacter() {return characterInteractor.getCharacter();}

    public Planet getCurrentPlanet() {return characterInteractor.getCurrentPlanet();}
}
