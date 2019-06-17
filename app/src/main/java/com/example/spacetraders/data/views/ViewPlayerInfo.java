package com.example.spacetraders.data.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


import com.example.spacetraders.R;

public class ViewPlayerInfo extends AppCompatActivity {

    private TextView name;
    private TextView pilot;
    private TextView trader;
    private TextView fighter;
    private TextView engineer;
    private TextView difficulty;
    private TextView ship;

    private TextView display_name;
    private TextView display_pilot;
    private TextView display_trader;
    private TextView display_fighter;
    private TextView display_engineer;
    private TextView display_difficulty;
    private TextView display_ship;
    private Character player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_view_player_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = (TextView)findViewById(R.id.textView_name);
        pilot = findViewById(R.id.textView_pilot);
        trader = findViewById(R.id.textView_trader);
        fighter = findViewById(R.id.textView_fighter);
        engineer = findViewById(R.id.textView_engineer);
        difficulty = findViewById(R.id.textView_difficulty);
        ship = findViewById(R.id.textView_ship);

        display_name = findViewById(R.id.textView_dname);
        display_pilot = findViewById(R.id.textView_dpilot);
        display_trader = findViewById(R.id.textView_dtrader);
        display_fighter = findViewById(R.id.textView_dfighter);
        display_engineer = findViewById(R.id.textView_dengineer);
        display_difficulty = findViewById(R.id.textView_ddifficulty);
        display_ship = findViewById(R.id.textView_dship);

        int player_name = getIntent().getIntExtra("key", 0);
        String name = getIntent().getStringExtra("name");
        int pilot_level = getIntent().getIntExtra("pilotLevel", 0);
        int trader_level = getIntent().getIntExtra("traderLevel" , 0);
        int fighter_level = getIntent().getIntExtra("fighterLevel", 0);
        int engineer_level = getIntent().getIntExtra("engineerLevel", 0);
        String difficulty = getIntent().getStringExtra("difficultyLevel");
        String shipName = "Gnat";


        display_name.setText(name);
        display_pilot.setText("" + pilot_level);
        display_trader.setText("" + trader_level);
        display_fighter.setText("" + fighter_level);
        display_engineer.setText(""+ engineer_level);
        display_difficulty.setText("" + difficulty);
        display_ship.setText(shipName);




    }

}