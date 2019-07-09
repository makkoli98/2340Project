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


public class RefuelActivity extends AppCompatActivity {

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refuel);

        character = Interactor.getInteractor().getCharacter();


        shipType = findViewById(R.id.displayType);
        cargoSize = findViewById(R.id.displayCargoSize);
        health = findViewById(R.id.displayCurrHealth);
        weaponsSize = findViewById(R.id.displayWeaponsNum);
        fuelEfficiency = findViewById(R.id.displayFuelEfficiency);
        basePrice = findViewById(R.id.displayBasePrice);
        fuelLeft = findViewById(R.id.displayFuelLeft);
        currency = findViewById(R.id.displayCurrency);
        refuelButton = findViewById(R.id.buttonRefuel);
        upgradeButton = findViewById(R.id.buttonUpgrade);

        shipType.setText(character.getShip().getName());
        cargoSize.setText(""+ character.getShip().getCargoSize());
        health.setText("" + character.getShip().getCurrentHealth());
        weaponsSize.setText("" + character.getShip().getMaxWeaponsAmount());
        fuelEfficiency.setText(""+ character.getShip().getFuelEfficiency());
        basePrice.setText(""+ character.getShip().getBasePrice());
        fuelLeft.setText(""+ character.getShip().getFuel());
        currency.setText("" + character.getCurrency() + "c");

        //int amountForRefuel = character.getShip().getFuelEfficiency() - character.getShip().getFuel();
        //1 fuel =  1 credit
        refuelButton.setText("1 Fuel = 5c");
        refuelButton.setOnClickListener((View v) -> {
           if(character.getShip().getFuel() == character.getShip().getFuelEfficiency()) {
               Toast.makeText(getApplicationContext(), "Tank Full", Toast.LENGTH_LONG).show();
               return;
           } else if (character.getCurrency() < 5) {
               Toast.makeText(getApplicationContext(), "Insufficient", Toast.LENGTH_LONG).show();
               return;
           } else {
               character.setCurrency(character.getCurrency() - 5);
               currency.setText("" + character.getCurrency() + "c");
               character.getShip().setFuel(character.getShip().getFuel() + 1);
               fuelLeft.setText("" + character.getShip().getFuel());
           }
        });

        upgradeButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(RefuelActivity.this, ShipYardActivity.class);
            startActivity(intent);
        });


    }
}
