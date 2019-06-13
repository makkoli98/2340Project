package com.example.spacetraders.data.views;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

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

    }
}