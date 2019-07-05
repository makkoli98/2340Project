package com.example.spacetraders.data.views;

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

import java.io.File;


import com.example.spacetraders.R;
import com.example.spacetraders.data.Interactor;
import com.example.spacetraders.data.entity.Character;
import com.example.spacetraders.data.entity.GameDifficulty;
import com.example.spacetraders.data.entity.SaveDatabase;
import com.example.spacetraders.data.entity.ShipType;
import com.example.spacetraders.data.entity.Skill;
import com.example.spacetraders.data.entity.SolarSystem;
import com.example.spacetraders.data.entity.Spaceship;
import com.example.spacetraders.data.entity.Universe;

public class NewCharacterActivity extends AppCompatActivity {
    private Spinner difficulty;
    private TextInputLayout characterName;
    private TextView pilot;
    private TextView fighter;
    private TextView trader;
    private TextView engineer;

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

        Button done = findViewById(R.id.doneButton);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = characterName.getEditText().getText().toString();
                int pilotLevel = Integer.parseInt(pilot.getText().toString());
                int fighterLevel = Integer.parseInt(fighter.getText().toString());
                int traderLevel = Integer.parseInt(trader.getText().toString());
                int engineerLevel = Integer.parseInt(engineer.getText().toString());
                GameDifficulty characterDifficulty = (GameDifficulty) difficulty.getSelectedItem();

                int totalSkillPoints = 0;
                totalSkillPoints = pilotLevel + engineerLevel + traderLevel + fighterLevel;
                if (totalSkillPoints != 16) {
                    Toast.makeText(getApplicationContext(), "Points Distribution is not valid", Toast.LENGTH_LONG).show();
                    return;
                }
                Character character = new Character(name, characterDifficulty, 1000, ShipType.GNAT, pilotLevel, traderLevel, fighterLevel, engineerLevel);
                Universe universe = new Universe(character.getDifficulty());

                Interactor.getInteractor().setCharacter(character);
                Interactor.getInteractor().setUniverse(universe);
                Interactor.getInteractor().getCharacter().setCurrentSolarSystem(universe.getSystems()[0]);

                //Log Character and Universe Information
                System.out.println(character);
                System.out.println(universe);

                startActivity(new Intent(NewCharacterActivity.this, MainGameActivity.class));
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
}