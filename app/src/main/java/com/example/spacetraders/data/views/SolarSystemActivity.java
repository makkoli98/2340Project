package com.example.spacetraders.data.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.spacetraders.R;
import com.example.spacetraders.data.entity.Planet;
import com.example.spacetraders.data.entity.SolarSystem;
import com.example.spacetraders.data.viewmodels.SolarSystemViewModel;

public class SolarSystemActivity extends AppCompatActivity {

    private TextView name, resourceLevel, techLevel, politicalSystem, currentPlanetName;
    private SolarSystem currentSolarSystem;
    private LinearLayout linearLayout;
    private Button[] planetButtons;
    private Planet[] planets;

    private SolarSystemViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_solar_system);

        viewModel = ViewModelProviders.of(this).get(SolarSystemViewModel.class);

        name = findViewById(R.id.solarSystemName);
        resourceLevel = findViewById(R.id.solarSystemResource);
        techLevel = findViewById(R.id.solarSystemTech);
        politicalSystem = findViewById(R.id.solarSystemPolitics);
        currentPlanetName = findViewById(R.id.planetName);

        planets = viewModel.getPlanets();

        name.setText(viewModel.getName());
        resourceLevel.setText(viewModel.getResourceLevel().toString());
        techLevel.setText(viewModel.getTechLevel().toString());
        politicalSystem.setText(viewModel.getPoliticalSystem().toString());
        currentPlanetName.setText(viewModel.getCurrentPlanet().getName());

        linearLayout = (LinearLayout) findViewById(R.id.listPlanets);
        System.out.println(viewModel.getNumPlanets());
        for (int i = 0; i < viewModel.getNumPlanets(); i++) {
            Planet planet = planets[i];
            Button button = new Button(this);
            button.setText(viewModel.getPlanets()[i].getName());
            button.setOnClickListener((View v) -> {
                if (viewModel.getCharacter().getCurrentSolarSystem().getCurrentPlanet() == planet) {
                    Toast.makeText(getApplicationContext(), "Already located on planet", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(getApplicationContext(), "You have travelled to " + planet.getName(), Toast.LENGTH_LONG).show();
                viewModel.getCharacter().getCurrentSolarSystem().setCurrentPlanet(planet);
                currentPlanetName.setText(planet.getName());
            });
            linearLayout.addView(button);
        }

    }
}
