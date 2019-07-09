package com.example.spacetraders.data.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;

import android.widget.Toast;


import com.example.spacetraders.R;
import com.example.spacetraders.data.models.Interactor;
import com.example.spacetraders.data.entity.Character;
import com.example.spacetraders.data.entity.GameDifficulty;
import com.example.spacetraders.data.entity.Skill;
import com.example.spacetraders.data.entity.SolarSystem;
import com.example.spacetraders.data.entity.Spaceship;
import com.example.spacetraders.data.entity.Universe;
import com.example.spacetraders.data.viewmodels.NewCharacterViewModel;

public class NewCharacterActivity extends AppCompatActivity {
    private Spinner difficulty;
    private TextInputLayout characterName;
    private TextView pilot;
    private TextView fighter;
    private TextView trader;
    private TextView engineer;

    private NewCharacterViewModel viewModel;

    private Character character;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_character);

        characterName = findViewById(R.id.textInputLayout);
        difficulty = findViewById(R.id.difficultySpinner);
        pilot = findViewById(R.id.pilotPts);
        fighter = findViewById(R.id.fighterPts);
        trader = findViewById(R.id.traderPts);
        engineer = findViewById(R.id.engrPts);


        ArrayAdapter<GameDifficulty> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, GameDifficulty.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulty.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(NewCharacterViewModel.class);

        Button done = findViewById(R.id.doneButton);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = characterName.getEditText().getText().toString();
                int pilotLevel = Integer.parseInt(pilot.getText().toString());
                int fighterLevel = Integer.parseInt(fighter.getText().toString());
                int traderLevel = Integer.parseInt(trader.getText().toString());
                int engineerLevel = Integer.parseInt(engineer.getText().toString());
                //String difficulty_level = difficulty.getSelectedItem().toString();

                GameDifficulty characterDifficulty = (GameDifficulty) difficulty.getSelectedItem();

                int totalSkillPoints = 0;
                totalSkillPoints = pilotLevel + engineerLevel + traderLevel + fighterLevel;
                if (totalSkillPoints != 16) {
                    Toast.makeText(getApplicationContext(), "Points Distribution is not valid", Toast.LENGTH_LONG).show();
                    return;
                } else {

                    /*
                    for(File f : getApplicationContext().getFilesDir().listFiles()) {
                        System.out.println(f.getAbsolutePath());
                        if(f.isDirectory()) {
                            for(File fi : f.listFiles()) {
                                System.out.println("Inner file: "+fi.getAbsolutePath());
                                if(fi.isDirectory()) {
                                    for(File fii : fi.listFiles()) {
                                        System.out.println("Inner Inner file: "+fii.getAbsolutePath());
                                    }
                                }
                            }
                        }
                    }

                    character = new Character(name, 0, characterDifficulty, 1000, null, pilotLevel, fighterLevel, traderLevel, engineerLevel);
                    SaveDatabase saveDatabase = new SaveDatabase(getApplicationContext());
                    int numSaves = saveDatabase.getNumSaves();
                    if(numSaves > 0) {
                        Character oldChar = saveDatabase.getCharacter(numSaves - 1);
                        System.out.println("Previous character name: "+oldChar.getName());
                    } else {
                        System.out.println("No Previous save exists");
                    }
                    int id = saveDatabase.createNewSave();
                    saveDatabase.saveCharacter(character, id);



                    Intent intent = new Intent(NewCharacterActivity.this , ViewPlayerInfo.class);
                    intent.putExtra("name", name);
                    //intent.putExtra("key", 5);//name key
                    intent.putExtra("pilotLevel", pilotLevel);//key for the pilot
                    intent.putExtra("fighterLevel", fighterLevel);//key for fighter
                    intent.putExtra("traderLevel", traderLevel);//key for trader
                    intent.putExtra("engineerLevel", engineerLevel);//key for engineer
                    intent.putExtra("difficultyLevel", difficulty_level);//key for difficulty
*/
                    Character character = new Character(name, characterDifficulty, 1000, Spaceship.GNAT, pilotLevel, traderLevel, fighterLevel, engineerLevel);
                    System.out.println("Character name: "+character.getName());
                    System.out.println("Difficulty : "+character.getDifficulty());
                    System.out.println("Character currency: "+character.getCredits());
                    System.out.println("Character Ship: "+character.getShip());
                    System.out.println("Character pilot level: "+character.getSkill(Skill.PILOT));
                    System.out.println("Character trader level: "+character.getSkill(Skill.TRADER));
                    System.out.println("Character fighter level: "+character.getSkill(Skill.FIGHTER));
                    System.out.println("Character engineer level: "+character.getSkill(Skill.ENGINEER));

                    Universe universe = new Universe(character.getDifficulty());
                    int i = 1;
                    for(SolarSystem s : universe.getSystems()) {
                        String solName = s.getName();
                        System.out.println("Solar system "+i+": "+solName);
                        System.out.println(name+"'s coordinates: ("+s.getCoords()[0]+", "+s.getCoords()[1]+")");
                        System.out.println(name+"'s resource: "+ s.getResources());
                        System.out.println(name+"'s tech level: "+s.getTechLevel());
                        i++;
                    }


                    viewModel.createUniverse(universe);
                    viewModel.createCharacter(character);
                    //Interactor.getInteractor().setCharacter(character);
                    //Interactor.getInteractor().setUniverse(universe);
                    //Interactor.getInteractor().getCharacter().setCurrentSolarSystem(universe.getSystems()[0]);



                    startActivity(new Intent(NewCharacterActivity.this, MainGameActivity.class));
                }

            }
        });


        Button lowerPilot = findViewById(R.id.pilotDecButton);
        lowerPilot.setOnClickListener((view) -> {
                TextView pilotPoints = findViewById(R.id.pilotPts);
                int points = Integer.parseInt(pilotPoints.getText().toString());
                if (points > 0) {
                    points--;
                }
                pilotPoints.setText(Integer.toString(points));
        });
        Button raisePilot = findViewById(R.id.pilotIncButton);
        raisePilot.setOnClickListener((view) -> {
            TextView pilotPoints = findViewById(R.id.pilotPts);
            int points = Integer.parseInt(pilotPoints.getText().toString());
            points++;
            pilotPoints.setText(Integer.toString(points));
        });

        Button lowerFighter = findViewById(R.id.fighterDecButton);
        lowerFighter.setOnClickListener((view) -> {
            TextView fighterPoints = findViewById(R.id.fighterPts);
            int points = Integer.parseInt(fighterPoints.getText().toString());
            if (points > 0) {
                points--;
            }
            fighterPoints.setText(Integer.toString(points));
        });

        Button raiseFighter = findViewById(R.id.fighterIncButton);
        raiseFighter.setOnClickListener((view) -> {
            TextView fighterPoints = findViewById(R.id.fighterPts);
            int points = Integer.parseInt(fighterPoints.getText().toString());
            points++;
            fighterPoints.setText(Integer.toString(points));
        });

        Button lowerTrader = findViewById(R.id.traderDecButton);
        lowerTrader.setOnClickListener((view) -> {
            TextView traderPoints = findViewById(R.id.traderPts);
            int points = Integer.parseInt(traderPoints.getText().toString());
            if (points > 0) {
                points--;
            }
            traderPoints.setText(Integer.toString(points));
        });

        Button raiseTrader = findViewById(R.id.traderIncButton);
        raiseTrader.setOnClickListener((view) -> {
            TextView traderPoints = findViewById(R.id.traderPts);
            int points = Integer.parseInt(traderPoints.getText().toString());
            points++;
            traderPoints.setText(Integer.toString(points));
        });

        Button lowerEngineer = findViewById(R.id.engDecButton);
        lowerEngineer.setOnClickListener((view) -> {
            TextView engineerPoints = findViewById(R.id.engrPts);
            int points = Integer.parseInt(engineerPoints.getText().toString());
            if (points > 0) {
                points--;
            }
            engineerPoints.setText(Integer.toString(points));
        });

        Button raiseEngineer  = findViewById(R.id.engIncButton);
        raiseEngineer.setOnClickListener((view) -> {
            TextView engineerPoints = findViewById(R.id.engrPts);
            int points = Integer.parseInt(engineerPoints.getText().toString());
            points++;
            engineerPoints.setText(Integer.toString(points));
        });



    }


    /**
     * Button handler which creates new character
     * @param view the button pressed
     */
    /*
    public void onAddPressed(View view) {
        String name = characterName.getEditText().getText().toString();
        int pilotLevel = Integer.parseInt(pilot.getText().toString());
        int fighterLevel = Integer.parseInt(fighter.getText().toString());
        int traderLevel = Integer.parseInt(trader.getText().toString());
        int engineerLevel = Integer.parseInt(engineer.getText().toString());
        GameDifficulty characterDifficulty = (GameDifficulty) difficulty.getSelectedItem();
        character = new Character(name, 0, characterDifficulty, 1000, null, pilotLevel, fighterLevel, traderLevel, engineerLevel);
        finish();
    }*/
}