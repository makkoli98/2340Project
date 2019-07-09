package com.example.spacetraders.data.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetraders.R;
import com.example.spacetraders.data.entity.GameDifficulty;
import com.example.spacetraders.data.entity.Resources;
import com.example.spacetraders.data.entity.SolarSystem;
import com.example.spacetraders.data.entity.Universe;
import com.example.spacetraders.data.entity.Character;
import com.example.spacetraders.data.models.Interactor;

public class MainGameActivity extends AppCompatActivity {
    private Button solarSystem, planet, market, player, travel, cargo, shipyard;
    TextView systemName;
    Character character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        systemName = findViewById(R.id.solar_system_name);
        character = Interactor.getInteractor().getCharacter();
        systemName.setText(character.getCurrentSolarSystem().getName());

        /*
         * Disables actionbar back button
         */
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }

        solarSystem = findViewById(R.id.button_solarSystem);
        planet = findViewById(R.id.button_planet);
        market = findViewById(R.id.button_market);
        player = findViewById(R.id.button_player);
        travel = findViewById(R.id.button_travel);
        cargo = findViewById(R.id.button_cargo);
        shipyard = findViewById(R.id.button_shipyard);

        /*
        solarSystem.setOnClickListener((View v) -> {
                Intent intent = new Intent(MainGameActivity.this, NewCharacterActivity.class);
                startActivity(intent);
        });
        */

        market.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainGameActivity.this, MarketActivity.class);
            startActivity(intent);
        });


        travel.setOnClickListener((View v) -> {
                Intent intent = new Intent(MainGameActivity.this, TravelActivity.class);
                startActivity(intent);
        });

        shipyard.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainGameActivity.this, RefuelActivity.class);
            startActivity(intent);
        });



    }
}
