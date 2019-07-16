package com.example.spacetraders.data.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.spacetraders.R;

public class PoliceEncounterActivity extends AppCompatActivity {

    Button escapeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_encounter);

        escapeButton = findViewById(R.id.escape_police);
        escapeButton.setOnClickListener((view) -> {
            startActivity(new Intent(PoliceEncounterActivity.this, MainGameActivity.class));
            finish();
        });
    }
}
