package com.example.spacetraders.data.views;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;

import com.example.spacetraders.R;
import com.example.spacetraders.data.entity.Character;
import com.example.spacetraders.data.entity.GameDifficulty;

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
    public void onAddPressed(View view) {
        String name = characterName.getEditText().getText().toString();
        int pilotLevel = Integer.parseInt(pilot.getText().toString());
        int fighterLevel = Integer.parseInt(fighter.getText().toString());
        int traderLevel = Integer.parseInt(trader.getText().toString());
        int engineerLevel = Integer.parseInt(engineer.getText().toString());
        GameDifficulty characterDifficulty = (GameDifficulty) difficulty.getSelectedItem();
        character = new Character(name, 0, characterDifficulty, 1000, null, pilotLevel, fighterLevel, traderLevel, engineerLevel);
        finish();
    }
}