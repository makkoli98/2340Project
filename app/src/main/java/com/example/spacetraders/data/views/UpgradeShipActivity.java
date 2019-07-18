package com.example.spacetraders.data.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetraders.R;
import com.example.spacetraders.data.entity.Resources;
import com.example.spacetraders.data.entity.ShipType;
import com.example.spacetraders.data.entity.Spaceship;
import com.example.spacetraders.data.entity.Weapons;
import com.example.spacetraders.data.models.Interactor;
import com.example.spacetraders.data.entity.Character;

public class UpgradeShipActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Character character1;
    private TextView viewCurrency;
    private Button previewButton;
    private Button purchaseButton;
    private Spinner shipSpinner;
    public static String shipType;
    private Button purchaseSonicLaser;
    private Button purchasePlasmaLaser;
    private Button purchaseMesonPhaser;
    private Button purchaseGoldShield;
    private Button purchaseSilverShield;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_ship);

        character1 = Interactor.getInteractor().getCharacter();
        viewCurrency = findViewById(R.id.viewPlayerCurrency);

        purchaseButton = findViewById(R.id.purchaseButtn);
        shipSpinner = findViewById(R.id.shipSpinner);

        purchaseGoldShield = findViewById(R.id.goldShield);
        purchaseSilverShield = findViewById(R.id.silverShield);

        purchaseSonicLaser = findViewById(R.id.sLaser);
        purchasePlasmaLaser = findViewById(R.id.pLaser);
        purchaseMesonPhaser = findViewById(R.id.mLaser);
        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ship_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shipSpinner.setAdapter(adapter);
        shipSpinner.setOnItemSelectedListener(this);



        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");

        purchaseSonicLaser.setOnClickListener((View v) -> {
            if (character1.getShip().getCurrWeaponsCount() >= character1.getShip().getMaxWeaponsAmount()) {
                Toast.makeText(getApplicationContext(), new String("You have reached the maximum amount of weapons!"), Toast.LENGTH_SHORT).show();
            } else if (character1.getCurrency() < Weapons.SONICRAY.getWeaponCost()) {
                Toast.makeText(getApplicationContext(), new String("Insufficient"), Toast.LENGTH_SHORT).show();
            } else if (character1.getCurrency() >= Weapons.SONICRAY.getWeaponCost()){
                character1.setCurrency(character1.getCurrency() - Weapons.SONICRAY.getWeaponCost());
                viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                character1.getShip().setCurrWeaponsCount(character1.getShip().getCurrWeaponsCount() + 1);
            }
        });

        purchasePlasmaLaser.setOnClickListener((View v) -> {
            if (character1.getShip().getCurrWeaponsCount() >= character1.getShip().getMaxWeaponsAmount()) {
                Toast.makeText(getApplicationContext(), new String("You have reached the maximum amount of weapons!"), Toast.LENGTH_SHORT).show();
            } else if (character1.getCurrency() < Weapons.PLASMARAY.getWeaponCost()) {
                Toast.makeText(getApplicationContext(), new String("Insufficient"), Toast.LENGTH_SHORT).show();
            } else if (character1.getCurrency() >= Weapons.PLASMARAY.getWeaponCost()){
                character1.setCurrency(character1.getCurrency() - Weapons.PLASMARAY.getWeaponCost());
                viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                character1.getShip().setCurrWeaponsCount(character1.getShip().getCurrWeaponsCount() + 1);
            }
        });

        purchaseMesonPhaser.setOnClickListener((View v) -> {
            if (character1.getShip().getCurrWeaponsCount() >= character1.getShip().getMaxWeaponsAmount()) {
                Toast.makeText(getApplicationContext(), new String("You have reached the maximum amount of weapons!"), Toast.LENGTH_SHORT).show();
            } else if (character1.getCurrency() < Weapons.MESONPHASER.getWeaponCost()) {
                Toast.makeText(getApplicationContext(), new String("Insufficient"), Toast.LENGTH_SHORT).show();
            } else if (character1.getCurrency() >= Weapons.MESONPHASER.getWeaponCost()){
                character1.setCurrency(character1.getCurrency() - Weapons.MESONPHASER.getWeaponCost());
                viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                character1.getShip().setCurrWeaponsCount(character1.getShip().getCurrWeaponsCount() + 1);
            }
        });

        purchaseSilverShield.setOnClickListener((View v) -> {

        });





    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        shipType = parent.getItemAtPosition(position).toString();
        previewButton = findViewById(R.id.previewButton);

        viewCurrency = findViewById(R.id.viewPlayerCurrency);

        purchaseButton.setOnClickListener((View v) -> {


            int currentShipWorth = 0;
            currentShipWorth += character1.getShip().getBasePrice();
            int[] currShipResources = character1.getShip().getCurrentResources();

            currentShipWorth += currShipResources[0] * Resources.WATER.getBasePrice();
            currentShipWorth += currShipResources[1] * Resources.FURS.getBasePrice();
            currentShipWorth += currShipResources[2] * Resources.FOOD.getBasePrice();
            currentShipWorth += currShipResources[3] * Resources.ORE.getBasePrice();
            currentShipWorth += currShipResources[4] * Resources.GAMES.getBasePrice();
            currentShipWorth += currShipResources[5] * Resources.FIREARMS.getBasePrice();
            currentShipWorth += currShipResources[6] * Resources.MEDICINE.getBasePrice();
            currentShipWorth += currShipResources[7] * Resources.MACHINES.getBasePrice();
            currentShipWorth += currShipResources[8] * Resources.NARCOTICS.getBasePrice();
            currentShipWorth += currShipResources[9] * Resources.ROBOTS.getBasePrice();


            if (shipType.equals("FLEA 500c")) {
                if (character1.getCurrency() >= ShipType.FLEA.getBasePrice()) {
                    if (currentShipWorth - ShipType.FLEA.getBasePrice() > 0) {
                        character1.setCurrency(character1.getCurrency() - (currentShipWorth - ShipType.FLEA.getBasePrice()));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.FLEA));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if (shipType.equals("GNAT 1000c")){
                if (character1.getCurrency() >= ShipType.GNAT.getBasePrice()) {
                    if (currentShipWorth - ShipType.GNAT.getBasePrice() > 0) {
                        character1.setCurrency(character1.getCurrency() - (currentShipWorth - ShipType.GNAT.getBasePrice()));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.GNAT));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if (shipType.equals("FIREFLY 750c")) {
                if (character1.getCurrency() >= ShipType.FIREFLY.getBasePrice()) {
                    if (currentShipWorth - ShipType.FIREFLY.getBasePrice() > 0) {
                        character1.setCurrency(character1.getCurrency() - (currentShipWorth - ShipType.FIREFLY.getBasePrice()));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.FIREFLY));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if(shipType.equals("MOSQUITO 1500c")) {
                if (character1.getCurrency() >= ShipType.MOSQUITO.getBasePrice()) {
                    if (currentShipWorth - ShipType.MOSQUITO.getBasePrice() > 0) {
                        character1.setCurrency(character1.getCurrency() - (currentShipWorth - ShipType.MOSQUITO.getBasePrice()));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.MOSQUITO));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if(shipType.equals("BUMBLEBEE 2000c")) {
                if (character1.getCurrency() >= ShipType.BUMBLEBEE.getBasePrice()) {
                    if (currentShipWorth - ShipType.BUMBLEBEE.getBasePrice() > 0) {
                        character1.setCurrency(character1.getCurrency() - (currentShipWorth - ShipType.BUMBLEBEE.getBasePrice()));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.BUMBLEBEE));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if(shipType.equals("BEETLE 3500c")) {
                if (character1.getCurrency() >= ShipType.BEETLE.getBasePrice()) {
                    if (currentShipWorth - ShipType.BEETLE.getBasePrice() > 0) {
                        character1.setCurrency(character1.getCurrency() - (currentShipWorth - ShipType.BEETLE.getBasePrice()));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.BEETLE));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if(shipType.equals("HORNET 3000c")) {
                if (character1.getCurrency() >= ShipType.HORNET.getBasePrice()) {
                    if (currentShipWorth - ShipType.HORNET.getBasePrice() > 0) {
                        character1.setCurrency(character1.getCurrency() - (currentShipWorth - ShipType.HORNET.getBasePrice()));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.HORNET));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if(shipType.equals("GRASSHOPPER 2500c")) {
                if (character1.getCurrency() >= ShipType.GRASSHOPPER.getBasePrice()) {
                    if (currentShipWorth - ShipType.GRASSHOPPER.getBasePrice() > 0) {
                        character1.setCurrency(character1.getCurrency() - (currentShipWorth - ShipType.GRASSHOPPER.getBasePrice()));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.GRASSHOPPER));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if(shipType.equals("TERMITE 4500c")) {
                if (character1.getCurrency() >= ShipType.TERMITE.getBasePrice()) {
                    if (currentShipWorth - ShipType.TERMITE.getBasePrice() > 0) {
                        character1.setCurrency(character1.getCurrency() - (currentShipWorth - ShipType.TERMITE.getBasePrice()));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.TERMITE));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if(shipType.equals("WASP 6000c")) {
                if (character1.getCurrency() >= ShipType.WASP.getBasePrice()) {
                    if (currentShipWorth - ShipType.WASP.getBasePrice() > 0) {
                        character1.setCurrency(character1.getCurrency() - (currentShipWorth - ShipType.WASP.getBasePrice()));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.WASP));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }
            } else {

            }


        });

        previewButton.setOnClickListener((View v) -> {

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ship_names, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            Intent intent = new Intent(this, PreviewActivity.class);
            intent.putExtra(shipType, adapter.getItem(position));
            startActivityForResult(intent, 1);

        });


        
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
