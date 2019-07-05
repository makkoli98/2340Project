package com.example.spacetraders.data.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.spacetraders.R;
import com.example.spacetraders.data.Interactor;
import com.example.spacetraders.data.entity.SolarSystem;

import java.util.Comparator;

public class TravelActivity extends AppCompatActivity {

    SolarSystem[] systems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        systems = Interactor.getInteractor().getUniverse().getSystems();

    }
}


