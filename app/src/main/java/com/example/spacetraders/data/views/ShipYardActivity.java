package com.example.spacetraders.data.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.spacetraders.R;
import com.example.spacetraders.data.Interactor;
import com.example.spacetraders.data.entity.Character;

public class ShipYardActivity extends AppCompatActivity {

    private Character character1;
    private TextView viewCurrency;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        character1 = Interactor.getInteractor().getCharacter();
        viewCurrency = findViewById(R.id.playerCurrency);

        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_yard);
    }
}
