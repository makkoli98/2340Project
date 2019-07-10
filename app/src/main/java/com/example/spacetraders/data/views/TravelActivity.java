package com.example.spacetraders.data.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetraders.R;
import com.example.spacetraders.data.models.Interactor;
import com.example.spacetraders.data.entity.Character;
import com.example.spacetraders.data.entity.SolarSystem;
import com.example.spacetraders.data.entity.Universe;

import java.util.Arrays;
import java.util.Comparator;

public class TravelActivity extends AppCompatActivity {

    Button[] travelButtons;

    Universe universe;
    SolarSystem[] systems;
    SolarSystem currentSystem;
    TextView fuelLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        Character character = Interactor.getInteractor().getCharacter();

        fuelLeft = findViewById(R.id.label_fuel);
        fuelLeft.setText("Fuel left: "+((Character) character).getShip().getFuel());

        universe = Interactor.getInteractor().getUniverse();
        systems = universe.getSystems();
        currentSystem = character.getCurrentSolarSystem();
        Arrays.sort(systems, new SystemComparator()); //sort in order of distance

        travelButtons = new Button[systems.length - 1]; //ignore current System

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        for(int i = 0; i < travelButtons.length; i++) {
            try {
                Button button = new Button(this);
                button.setId(View.generateViewId());
                button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                //todo: add fuel efficiency formula
                SolarSystem nextSystem = systems[i+1];
                int fuelCost = nextSystem.getDistance(currentSystem);
                button.setText("\nSolar System: " + nextSystem.getName() + "\nFuel Cost: " + fuelCost + "\n");
                button.setOnClickListener((view) -> {
                    if (character.getShip().getFuel() < fuelCost) {
                        Toast.makeText(getApplicationContext(), "Not enough fuel", Toast.LENGTH_LONG).show();
                        return;
                    }

                    character.getShip().setFuel(character.getShip().getFuel() - fuelCost);
                    character.setCurrentSolarSystem(nextSystem);
                    Toast.makeText(getApplicationContext(), "Used " + fuelCost + " fuel", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(TravelActivity.this, MainGameActivity.class));
                    finish();
                });

                linLayout.addView(button);
                travelButtons[i] = button;
            } catch(Exception e) {
                System.err.println(e);
                System.err.println("error creating button in TravelActivity");
            }
        }


    }
    private class SystemComparator implements Comparator<SolarSystem> {
        public int compare(SolarSystem a, SolarSystem b) {
            return currentSystem.getDistance(a) - currentSystem.getDistance(b);
        }
    }
}


