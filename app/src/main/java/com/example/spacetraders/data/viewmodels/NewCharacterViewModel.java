package com.example.spacetraders.data.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetraders.data.entity.GameDifficulty;
import com.example.spacetraders.data.entity.Universe;
import com.example.spacetraders.data.models.CharacterInteractor;
import com.example.spacetraders.data.models.Interactor;
import com.example.spacetraders.data.models.Model;
import com.example.spacetraders.data.entity.Character;
import com.example.spacetraders.data.models.ShipInteractor;
import com.example.spacetraders.data.models.UniverseInteractor;

public class NewCharacterViewModel extends AndroidViewModel {
    private CharacterInteractor characterInteractor;
    private UniverseInteractor universeInteractor;


    /**
     * Creates ViewModel that will deal with info from the new character activity
     * @param application no clue what this means
     */
    public NewCharacterViewModel(@NonNull Application application) {
        super(application);
        characterInteractor = Model.getInstance().getCharacterInteractor();
        universeInteractor = Model.getInstance().getUniverseInteractor();
    }

    /**
     * creates the character and stores it into the Model Interactor
     * @param character the character data that is the new character
     */
    public void createCharacter(Character character) {characterInteractor.createCharacter(character);}

    public Character getCharacter() {return characterInteractor.getCharacter();}

    /**
     * Gets the Universe and stores it to the Universe Interactor
     * @param universe the Universe that was randomly generated at character creation
     */
    public void createUniverse(Universe universe) { universeInteractor.createUniverse(universe);}

    public Universe getUniverse() {return universeInteractor.getUniverse();}
}
