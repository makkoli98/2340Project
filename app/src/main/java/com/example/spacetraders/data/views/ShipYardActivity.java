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
import com.example.spacetraders.data.models.Interactor;
import com.example.spacetraders.data.entity.Character;

public class ShipYardActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Character character1;
    private TextView viewCurrency;
    private Button previewButton;
    private Button purchaseButton;
    private Spinner shipSpinner;
    public static String shipType;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_yard);

        character1 = Interactor.getInteractor().getCharacter();
        viewCurrency = findViewById(R.id.playerCurrency);

        purchaseButton = findViewById(R.id.purchaseButtn);
        shipSpinner = findViewById(R.id.shipSpinner);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ship_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shipSpinner.setAdapter(adapter);
        shipSpinner.setOnItemSelectedListener(this);



        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        shipType = parent.getItemAtPosition(position).toString();
        previewButton = findViewById(R.id.previewButton);

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
                        character1.setCurrency(currentShipWorth - ShipType.FLEA.getBasePrice());
                        character1.setShip(new Spaceship(ShipType.FLEA.getName(), ShipType.FLEA.getCargoSize(),
                                ShipType.FLEA.getMaximumHealth(), ShipType.FLEA.getMaxWeaponsAmount(),
                                ShipType.FLEA.getFuelEfficiency(), ShipType.FLEA.getBasePrice()));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if (shipType.equals("GNAT 1000c")){
                if (character1.getCurrency() >= ShipType.GNAT.getBasePrice()) {
                    if (currentShipWorth - ShipType.GNAT.getBasePrice() > 0) {
                        character1.setCurrency(currentShipWorth - ShipType.GNAT.getBasePrice());
                        character1.setShip(new Spaceship(ShipType.GNAT.getName(), ShipType.GNAT.getCargoSize(),
                                ShipType.GNAT.getMaximumHealth(), ShipType.GNAT.getMaxWeaponsAmount(),
                                ShipType.GNAT.getFuelEfficiency(), ShipType.GNAT.getBasePrice()));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if (shipType.equals("FIREFLY 750c")) {
                if (character1.getCurrency() >= ShipType.FIREFLY.getBasePrice()) {
                    if (currentShipWorth - ShipType.FIREFLY.getBasePrice() > 0) {
                        character1.setCurrency(currentShipWorth - ShipType.FIREFLY.getBasePrice());
                        character1.setShip(new Spaceship(ShipType.FIREFLY.getName(), ShipType.FIREFLY.getCargoSize(),
                                ShipType.FIREFLY.getMaximumHealth(), ShipType.FIREFLY.getMaxWeaponsAmount(),
                                ShipType.FIREFLY.getFuelEfficiency(), ShipType.FIREFLY.getBasePrice()));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if(shipType.equals("MOSQUITO 1500c")) {
                if (character1.getCurrency() >= ShipType.MOSQUITO.getBasePrice()) {
                    if (currentShipWorth - ShipType.MOSQUITO.getBasePrice() > 0) {
                        character1.setCurrency(currentShipWorth - ShipType.MOSQUITO.getBasePrice());
                        character1.setShip(new Spaceship(ShipType.MOSQUITO.getName(), ShipType.MOSQUITO.getCargoSize(),
                                ShipType.MOSQUITO.getMaximumHealth(), ShipType.MOSQUITO.getMaxWeaponsAmount(),
                                ShipType.MOSQUITO.getFuelEfficiency(), ShipType.MOSQUITO.getBasePrice()));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if(shipType.equals("BUMBLEBEE 2000c")) {
                if (character1.getCurrency() >= ShipType.BUMBLEBEE.getBasePrice()) {
                    if (currentShipWorth - ShipType.BUMBLEBEE.getBasePrice() > 0) {
                        character1.setCurrency(currentShipWorth - ShipType.BUMBLEBEE.getBasePrice());
                        character1.setShip(new Spaceship(ShipType.BUMBLEBEE.getName(), ShipType.BUMBLEBEE.getCargoSize(),
                                ShipType.BUMBLEBEE.getMaximumHealth(), ShipType.BUMBLEBEE.getMaxWeaponsAmount(),
                                ShipType.BUMBLEBEE.getFuelEfficiency(), ShipType.BUMBLEBEE.getBasePrice()));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if(shipType.equals("BEETLE 3500c")) {
                if (character1.getCurrency() >= ShipType.BEETLE.getBasePrice()) {
                    if (currentShipWorth - ShipType.BEETLE.getBasePrice() > 0) {
                        character1.setCurrency(currentShipWorth - ShipType.BEETLE.getBasePrice());
                        character1.setShip(new Spaceship(ShipType.BEETLE.getName(), ShipType.BEETLE.getCargoSize(),
                                ShipType.BEETLE.getMaximumHealth(), ShipType.BEETLE.getMaxWeaponsAmount(),
                                ShipType.BEETLE.getFuelEfficiency(), ShipType.BEETLE.getBasePrice()));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if(shipType.equals("HORNET 3000c")) {
                if (character1.getCurrency() >= ShipType.HORNET.getBasePrice()) {
                    if (currentShipWorth - ShipType.HORNET.getBasePrice() > 0) {
                        character1.setCurrency(currentShipWorth - ShipType.HORNET.getBasePrice());
                        character1.setShip(new Spaceship(ShipType.HORNET.getName(), ShipType.HORNET.getCargoSize(),
                                ShipType.HORNET.getMaximumHealth(), ShipType.HORNET.getMaxWeaponsAmount(),
                                ShipType.HORNET.getFuelEfficiency(), ShipType.HORNET.getBasePrice()));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if(shipType.equals("GRASSHOPPER 2500c")) {
                if (character1.getCurrency() >= ShipType.GRASSHOPPER.getBasePrice()) {
                    if (currentShipWorth - ShipType.GRASSHOPPER.getBasePrice() > 0) {
                        character1.setCurrency(currentShipWorth - ShipType.GRASSHOPPER.getBasePrice());
                        character1.setShip(new Spaceship(ShipType.GRASSHOPPER.getName(), ShipType.GRASSHOPPER.getCargoSize(),
                                ShipType.GRASSHOPPER.getMaximumHealth(), ShipType.GRASSHOPPER.getMaxWeaponsAmount(),
                                ShipType.GRASSHOPPER.getFuelEfficiency(), ShipType.GRASSHOPPER.getBasePrice()));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if(shipType.equals("TERMITE 4500c")) {
                if (character1.getCurrency() >= ShipType.TERMITE.getBasePrice()) {
                    if (currentShipWorth - ShipType.TERMITE.getBasePrice() > 0) {
                        character1.setCurrency(currentShipWorth - ShipType.TERMITE.getBasePrice());
                        character1.setShip(new Spaceship(ShipType.TERMITE.getName(), ShipType.TERMITE.getCargoSize(),
                                ShipType.TERMITE.getMaximumHealth(), ShipType.TERMITE.getMaxWeaponsAmount(),
                                ShipType.TERMITE.getFuelEfficiency(), ShipType.TERMITE.getBasePrice()));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if(shipType.equals("WASP 6000c")) {
                if (character1.getCurrency() >= ShipType.WASP.getBasePrice()) {
                    if (currentShipWorth - ShipType.WASP.getBasePrice() > 0) {
                        character1.setCurrency(currentShipWorth - ShipType.WASP.getBasePrice());
                        character1.setShip(new Spaceship(ShipType.WASP.getName(), ShipType.WASP.getCargoSize(),
                                ShipType.WASP.getMaximumHealth(), ShipType.WASP.getMaxWeaponsAmount(),
                                ShipType.WASP.getFuelEfficiency(), ShipType.WASP.getBasePrice()));
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

        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
