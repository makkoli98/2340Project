package com.example.spacetraders.data.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetraders.R;
import com.example.spacetraders.data.entity.Character;
import com.example.spacetraders.data.models.Interactor;

public class MainGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        TextView systemName = findViewById(R.id.solar_system_name);
        Character character = Interactor.getInteractor().getCharacter();
        systemName.setText(character.getCurrentSolarSystem().getName());

        /*
         * Disables actionbar back button
         */
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }

        //Button solarSystem = findViewById(R.id.button_solarSystem);
        TextView solarSystem = findViewById(R.id.solar_system_name);
        Button mercenary = findViewById(R.id.button_mercenary);
        Button market = findViewById(R.id.button_market);
        Button player = findViewById(R.id.button_player);
        Button travel = findViewById(R.id.button_travel);
        Button shipyard = findViewById(R.id.button_shipyard);

        solarSystem.setOnClickListener((View v) -> {
                Intent intent = new Intent(MainGameActivity.this, SolarSystemActivity.class);
                startActivity(intent);
        });

        mercenary.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainGameActivity.this, MercenaryActivity.class);
            startActivity(intent);
        });


        market.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainGameActivity.this, MarketActivity.class);
            startActivity(intent);
        });

        player.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainGameActivity.this, PlayerActivity.class);
            startActivity(intent);
        });


        travel.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainGameActivity.this, TravelActivity.class);
            startActivity(intent);
        });

        shipyard.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainGameActivity.this, ShipYardActivity.class);
            startActivity(intent);
        });

        mercenary.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainGameActivity.this, MercenaryActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Interactor.getInteractor().saveGame(getApplicationContext());
    }
}
