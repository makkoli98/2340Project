package com.example.spacetraders.data.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.spacetraders.R;

public class PirateEncounterActivity extends AppCompatActivity {

    Button escapeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pirate_encounter);

        escapeButton = findViewById(R.id.escape_pirate);
        escapeButton.setOnClickListener((view) -> {
            startActivity(new Intent(PirateEncounterActivity.this, MainGameActivity.class));
            finish();
        });
    }
}
