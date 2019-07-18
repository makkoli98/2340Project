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

import java.util.Random;

public class PirateEncounterActivity extends AppCompatActivity {

    Button escapeButton;
    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pirate_encounter);

        Character character = Interactor.getInteractor().getCharacter();
        rand = new Random();
        int escapeChance = rand.nextInt(2);

        // Chance to take all of player's resources
        escapeButton = findViewById(R.id.escape_pirate);
        escapeButton.setOnClickListener((view) -> {
            if (escapeChance < 1) {
                character.getShip().setCurrentResources(new int[Resources.values().length]);
                try {
                    character.getShip().setCurrentHealth(character.getShip().getCurrentHealth() - 1);
                    Toast.makeText(getApplicationContext(), "You took 1 damage!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            startActivity(new Intent(PirateEncounterActivity.this, MainGameActivity.class));
            finish();
        });
    }
}
