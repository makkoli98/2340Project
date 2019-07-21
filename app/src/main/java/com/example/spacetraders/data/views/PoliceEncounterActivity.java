package com.example.spacetraders.data.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.spacetraders.R;
import com.example.spacetraders.data.entity.Resources;
import com.example.spacetraders.data.models.Interactor;
import com.example.spacetraders.data.entity.Character;

public class PoliceEncounterActivity extends AppCompatActivity {

    Button escapeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_encounter);

        Character character = Interactor.getInteractor().getCharacter();

        escapeButton = findViewById(R.id.inspection_button);
        escapeButton.setOnClickListener((view) -> {
            if (character.getShip().getCurrentResources()[Resources.NARCOTICS.ordinal()] > 0
                || character.getShip().getCurrentResources()[Resources.FIREARMS.ordinal()] > 0) {
                character.setCurrency((int) (character.getCurrency() * 0.9));
                int[] playerResources = character.getShip().getCurrentResources();
                playerResources[Resources.NARCOTICS.ordinal()] = 0;
                playerResources[Resources.FIREARMS.ordinal()] = 0;
                Toast.makeText(getApplicationContext(), "Don't do drugs, kids", Toast.LENGTH_SHORT).show();
            }
            startActivity(new Intent(PoliceEncounterActivity.this, MainGameActivity.class));
            finish();
        });
    }
}
