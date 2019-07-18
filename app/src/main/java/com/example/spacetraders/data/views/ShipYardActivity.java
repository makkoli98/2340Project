package com.example.spacetraders.data.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetraders.R;
import com.example.spacetraders.data.models.Interactor;
import com.example.spacetraders.data.entity.Character;


public class ShipYardActivity extends AppCompatActivity {

    private Character character;
    private TextView shipType;
    private TextView cargoSize;
    private TextView health;
    private TextView weaponsSize;
    private TextView fuelEfficiency;
    private TextView basePrice;
    private TextView fuelLeft;
    private TextView currency;
    private Button refuelButton;
    private Button upgradeButton;
    private Button restoreHealth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_yard);

        character = Interactor.getInteractor().getCharacter();

        upgradeButton = findViewById(R.id.buttonUpgrade);
        refuelButton = findViewById(R.id.buttonRefuel);
        restoreHealth = findViewById(R.id.restoreHealthButton);

        shipType = findViewById(R.id.viewShipType);
        shipType.setText(character.getShip().getName());

        cargoSize = findViewById(R.id.viewCargoSize);
        cargoSize.setText("" + character.getShip().getCargoSize());

        health = findViewById(R.id.viewHealth);
        health.setText("" + character.getShip().getCurrentHealth());

        weaponsSize = findViewById(R.id.viewWeaponsCap);
        weaponsSize.setText("" + character.getShip().getMaxWeaponsAmount());

        fuelEfficiency = findViewById(R.id.viewFuelEfficiency);
        fuelEfficiency.setText("" + character.getShip().getFuelEfficiency());

        basePrice = findViewById(R.id.viewBasePrice);
        basePrice.setText("" + character.getShip().getBasePrice());

        fuelLeft = findViewById(R.id.viewCurrFuel);
        fuelLeft.setText("" + character.getShip().getFuel());

        currency = findViewById(R.id.displayCredits);
        currency.setText("Credits: " + character.getCurrency());



        refuelButton.setText("1 Fuel = 5c");
        refuelButton.setOnClickListener((View v) -> {
           if(character.getShip().getFuel() == character.getShip().maxFuel) {
               Toast.makeText(getApplicationContext(), "Tank Full", Toast.LENGTH_LONG).show();
               return;
           } else if (character.getCurrency() < 5) {
               Toast.makeText(getApplicationContext(), "Insufficient Funds", Toast.LENGTH_LONG).show();
               return;
           } else {
               character.setCurrency(character.getCurrency() - 5);
               currency.setText("" + character.getCurrency() + "c");
               character.getShip().setFuel(character.getShip().getFuel() + 1);
               fuelLeft.setText("" + character.getShip().getFuel());
           }
        });

        upgradeButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(ShipYardActivity.this, UpgradeShipActivity.class);
            startActivity(intent);
        });

        restoreHealth.setOnClickListener((View v) -> {
            if(character.getShip().getCurrentHealth() >= character.getShip().getMaximumHealth()) {
                Toast.makeText(getApplicationContext(), "Fuel Tank is Full", Toast.LENGTH_LONG).show();
            } else if (character.getCurrency() < 35) {
                Toast.makeText(getApplicationContext(), "Insufficient Funds", Toast.LENGTH_LONG).show();
            } else {
                character.setCurrency(character.getCurrency() - 35);
                character.getShip().setCurrentHealth(character.getShip().getMaximumHealth());
                currency.setText("" + character.getCurrency() + "c");
            }
        });



    }

    @Override
    protected void onPause() {
        super.onPause();
        //Interactor.getInteractor().setCharacter(character);
        //update all of the values

        character = Interactor.getInteractor().getCharacter();

        upgradeButton = findViewById(R.id.buttonUpgrade);

        shipType = findViewById(R.id.viewShipType);
        shipType.setText(character.getShip().getName());

        cargoSize = findViewById(R.id.viewCargoSize);
        cargoSize.setText("" + character.getShip().getCargoSize());

        health = findViewById(R.id.viewHealth);
        health.setText("" + character.getShip().getCurrentHealth());

        weaponsSize = findViewById(R.id.viewWeaponsCap);
        weaponsSize.setText("" + character.getShip().getMaxWeaponsAmount());

        fuelEfficiency = findViewById(R.id.viewFuelEfficiency);
        fuelEfficiency.setText("" + character.getShip().getFuelEfficiency());

        basePrice = findViewById(R.id.viewBasePrice);
        basePrice.setText("" + character.getShip().getBasePrice());

        fuelLeft = findViewById(R.id.viewCurrFuel);
        fuelLeft.setText("" + character.getShip().getFuel());

        currency = findViewById(R.id.displayCredits);
        currency.setText("Credits: " + character.getCurrency());

    }
}
