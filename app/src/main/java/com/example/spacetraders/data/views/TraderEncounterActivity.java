package com.example.spacetraders.data.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.spacetraders.R;

public class TraderEncounterActivity extends AppCompatActivity {

    Button escapeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trader_encounter);

        escapeButton = findViewById(R.id.escape_trader);
        escapeButton.setOnClickListener((view) -> {
            startActivity(new Intent(TraderEncounterActivity.this, MainGameActivity.class));
            finish();
        });
    }
}
