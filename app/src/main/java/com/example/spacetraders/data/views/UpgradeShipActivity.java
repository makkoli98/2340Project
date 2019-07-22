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
    private TextView shipPrice;

    private TextView sonicLaser_num;
    private TextView plasmaLaser_num;
    private TextView mesonPhaser_num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_ship);

        character1 = Interactor.getInteractor().getCharacter();
        viewCurrency = findViewById(R.id.viewPlayerCurrency);
        shipPrice = findViewById(R.id.displayPurchasePrice);

        sonicLaser_num = findViewById(R.id.displaySonicNum);
        plasmaLaser_num = findViewById(R.id.displayPlasmaNum);
        mesonPhaser_num = findViewById(R.id.displayMesonNum);
        sonicLaser_num.setText("Sonic Ray: " + 0);
        plasmaLaser_num.setText("Plasma Ray: " + 0);
        mesonPhaser_num.setText("Meson Phaser: " + 0);
        

        purchaseButton = findViewById(R.id.purchaseButtn);
        shipSpinner = findViewById(R.id.shipSpinner);

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
                //character1.getShip().setCurrWeaponsCount(character1.getShip().getCurrWeaponsCount() + 1);

                character1.getShip().addWeapon(Weapons.SONICRAY);
                sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
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
                //character1.getShip().setCurrWeaponsCount(character1.getShip().getCurrWeaponsCount() + 1);

                character1.getShip().addWeapon(Weapons.PLASMARAY);
                plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
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
                //character1.getShip().setCurrWeaponsCount(character1.getShip().getCurrWeaponsCount() + 1);

                character1.getShip().addWeapon(Weapons.MESONPHASER);
                mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        shipType = parent.getItemAtPosition(position).toString();
        previewButton = findViewById(R.id.previewButton);

        viewCurrency = findViewById(R.id.viewPlayerCurrency);
        shipPrice = findViewById(R.id.displayPurchasePrice);

        int shipWorth = character1.currShipWorth();

        int newPrice = 0;

        if (shipType.equals("FLEA")) {

                if (character1.currShipWorth() - ShipType.FLEA.getBasePrice() > 0) {
                    shipPrice.setText("Price: " + (shipWorth - ShipType.FLEA.getBasePrice()) +"c");

                } else if (shipWorth < ShipType.FLEA.getBasePrice()) {
                    newPrice = ShipType.FLEA.getBasePrice() - shipWorth;
                    shipPrice.setText("Price: " + newPrice +"c");
                }

        } else if (shipType.equals("GNAT")){

                if (shipWorth - ShipType.GNAT.getBasePrice() > 0) {
                    shipPrice.setText("Price: " + (shipWorth - ShipType.GNAT.getBasePrice()) +"c");
                }else if (shipWorth < ShipType.GNAT.getBasePrice()) {
                    newPrice = ShipType.GNAT.getBasePrice() - shipWorth;
                    shipPrice.setText("Price: " + newPrice +"c");
                }


        } else if (shipType.equals("FIREFLY")) {

                if (shipWorth - ShipType.FIREFLY.getBasePrice() > 0) {
                    shipPrice.setText("Price: " + (shipWorth - ShipType.FIREFLY.getBasePrice()) +"c");
                } else if (shipWorth < ShipType.FIREFLY.getBasePrice()) {
                    newPrice = ShipType.FIREFLY.getBasePrice() - shipWorth;
                    shipPrice.setText("Price: " + newPrice +"c");
                }


        } else if(shipType.equals("MOSQUITO")) {

                if (shipWorth - ShipType.MOSQUITO.getBasePrice() > 0) {
                    shipPrice.setText("Price: " + (shipWorth - ShipType.MOSQUITO.getBasePrice()) +"c");
                } else if (shipWorth < ShipType.MOSQUITO.getBasePrice()) {
                    newPrice = ShipType.MOSQUITO.getBasePrice() - shipWorth;
                    shipPrice.setText("Price: " + newPrice +"c");

            }

        } else if(shipType.equals("BUMBLEBEE")) {

                if (shipWorth - ShipType.BUMBLEBEE.getBasePrice() > 0) {
                    shipPrice.setText("Price: " + (shipWorth - ShipType.BUMBLEBEE.getBasePrice()) +"c");
                } else if (shipWorth < ShipType.BUMBLEBEE.getBasePrice()) {
                    newPrice = ShipType.BUMBLEBEE.getBasePrice() - shipWorth;
                    shipPrice.setText("Price: " + newPrice +"c");
                }


        } else if(shipType.equals("BEETLE")) {

                if (shipWorth - ShipType.BEETLE.getBasePrice() > 0) {
                    shipPrice.setText("Price: " + (shipWorth - ShipType.BEETLE.getBasePrice()) +"c");
                }  else if (shipWorth < ShipType.BEETLE.getBasePrice()) {
                    newPrice = ShipType.BEETLE.getBasePrice() - shipWorth;
                    shipPrice.setText("Price: " + newPrice +"c");
                }

        } else if(shipType.equals("HORNET")) {

                if (shipWorth - ShipType.HORNET.getBasePrice() > 0) {
                    shipPrice.setText("Price: " + (shipWorth - ShipType.HORNET.getBasePrice()) +"c");
                }  else if (shipWorth < ShipType.HORNET.getBasePrice()) {
                    newPrice = ShipType.HORNET.getBasePrice() - shipWorth;
                    shipPrice.setText("Price: " + newPrice +"c");
                }

        } else if(shipType.equals("GRASSHOPPER")) {

                if (shipWorth - ShipType.GRASSHOPPER.getBasePrice() > 0) {
                    shipPrice.setText("Price: " + (shipWorth - ShipType.GRASSHOPPER.getBasePrice()) +"c");
                }  else if (shipWorth < ShipType.GRASSHOPPER.getBasePrice()) {
                    newPrice = ShipType.GRASSHOPPER.getBasePrice() - shipWorth;
                    shipPrice.setText("Price: " + newPrice +"c");
                }

        } else if(shipType.equals("TERMITE")) {

                if (shipWorth - ShipType.TERMITE.getBasePrice() > 0) {
                    shipPrice.setText("Price: " + (shipWorth - ShipType.TERMITE.getBasePrice()) +"c");
                }  else if (shipWorth < ShipType.TERMITE.getBasePrice()) {
                    newPrice = ShipType.TERMITE.getBasePrice() - shipWorth;
                    shipPrice.setText("Price: " + newPrice +"c");
                }

        } else if(shipType.equals("WASP")) {
                if (shipWorth - ShipType.WASP.getBasePrice() > 0) {
                    shipPrice.setText("Price: " + (shipWorth - ShipType.WASP.getBasePrice()) +"c");
                } else if (shipWorth < ShipType.WASP.getBasePrice()) {
                    newPrice = ShipType.WASP.getBasePrice() - shipWorth;
                    shipPrice.setText("Price: " + newPrice +"c");
                }
        }





        purchaseButton.setOnClickListener((View v) -> {
            int currentShipWorth = character1.currShipWorth();

            int newAdjustedPrice = 0;

            if (shipType.equals("FLEA")) {
                if (character1.getCurrency() >= ShipType.FLEA.getBasePrice()) {
                    if (character1.currShipWorth() - ShipType.FLEA.getBasePrice() > 0) {
                        character1.setCurrency(character1.getCurrency() - (currentShipWorth - ShipType.FLEA.getBasePrice()));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.FLEA));
                    } else if (currentShipWorth < ShipType.FLEA.getBasePrice()) {
                        newAdjustedPrice = ShipType.FLEA.getBasePrice() - currentShipWorth;
                        character1.setCurrency(character1.getCurrency() - newAdjustedPrice);
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.FLEA));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if (shipType.equals("GNAT")){
                if (character1.getCurrency() >= ShipType.GNAT.getBasePrice()) {
                    if (currentShipWorth - ShipType.GNAT.getBasePrice() > 0) {

                        character1.setCurrency(character1.getCurrency() - (currentShipWorth - ShipType.GNAT.getBasePrice()));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.GNAT));
                    }else if (currentShipWorth < ShipType.GNAT.getBasePrice()) {
                        newAdjustedPrice = ShipType.GNAT.getBasePrice() - currentShipWorth;

                        character1.setCurrency(character1.getCurrency() - newAdjustedPrice);
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.GNAT));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if (shipType.equals("FIREFLY")) {
                if (character1.getCurrency() >= ShipType.FIREFLY.getBasePrice()) {
                    if (currentShipWorth - ShipType.FIREFLY.getBasePrice() > 0) {

                        character1.setCurrency(character1.getCurrency() - (currentShipWorth - ShipType.FIREFLY.getBasePrice()));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.FIREFLY));
                    } else if (currentShipWorth < ShipType.FIREFLY.getBasePrice()) {
                        newAdjustedPrice = ShipType.FIREFLY.getBasePrice() - currentShipWorth;

                        character1.setCurrency(character1.getCurrency() - newAdjustedPrice);
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.FIREFLY));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if(shipType.equals("MOSQUITO")) {
                if (character1.getCurrency() >= ShipType.MOSQUITO.getBasePrice()) {
                    if (currentShipWorth - ShipType.MOSQUITO.getBasePrice() > 0) {

                        character1.setCurrency(character1.getCurrency() - (currentShipWorth - ShipType.MOSQUITO.getBasePrice()));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.MOSQUITO));
                    } else if (currentShipWorth < ShipType.MOSQUITO.getBasePrice()) {
                        newAdjustedPrice = ShipType.MOSQUITO.getBasePrice() - currentShipWorth;

                        character1.setCurrency(character1.getCurrency() - newAdjustedPrice);
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.MOSQUITO));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if(shipType.equals("BUMBLEBEE")) {
                if (character1.getCurrency() >= ShipType.BUMBLEBEE.getBasePrice()) {
                    if (currentShipWorth - ShipType.BUMBLEBEE.getBasePrice() > 0) {

                        character1.setCurrency(character1.getCurrency() - (currentShipWorth - ShipType.BUMBLEBEE.getBasePrice()));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.BUMBLEBEE));
                    } else if (currentShipWorth < ShipType.BUMBLEBEE.getBasePrice()) {
                        newAdjustedPrice = ShipType.BUMBLEBEE.getBasePrice() - currentShipWorth;

                        character1.setCurrency(character1.getCurrency() - newAdjustedPrice);
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.BUMBLEBEE));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if(shipType.equals("BEETLE")) {
                if (character1.getCurrency() >= ShipType.BEETLE.getBasePrice()) {
                    if (currentShipWorth - ShipType.BEETLE.getBasePrice() > 0) {

                        character1.setCurrency(character1.getCurrency() - (currentShipWorth - ShipType.BEETLE.getBasePrice()));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.BEETLE));
                    }  else if (currentShipWorth < ShipType.BEETLE.getBasePrice()) {
                        newAdjustedPrice = ShipType.BEETLE.getBasePrice() - currentShipWorth;

                        character1.setCurrency(character1.getCurrency() - newAdjustedPrice);
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.BEETLE));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if(shipType.equals("HORNET")) {
                if (character1.getCurrency() >= ShipType.HORNET.getBasePrice()) {
                    if (currentShipWorth - ShipType.HORNET.getBasePrice() > 0) {

                        character1.setCurrency(character1.getCurrency() - (currentShipWorth - ShipType.HORNET.getBasePrice()));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.HORNET));
                    }  else if (currentShipWorth < ShipType.HORNET.getBasePrice()) {
                        newAdjustedPrice = ShipType.HORNET.getBasePrice() - currentShipWorth;

                        character1.setCurrency(character1.getCurrency() - newAdjustedPrice);
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.HORNET));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if(shipType.equals("GRASSHOPPER")) {
                if (character1.getCurrency() >= ShipType.GRASSHOPPER.getBasePrice()) {
                    if (currentShipWorth - ShipType.GRASSHOPPER.getBasePrice() > 0) {

                        character1.setCurrency(character1.getCurrency() - (currentShipWorth - ShipType.GRASSHOPPER.getBasePrice()));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.GRASSHOPPER));
                    }  else if (currentShipWorth < ShipType.GRASSHOPPER.getBasePrice()) {
                        newAdjustedPrice = ShipType.GRASSHOPPER.getBasePrice() - currentShipWorth;

                        character1.setCurrency(character1.getCurrency() - newAdjustedPrice);
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.GRASSHOPPER));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if(shipType.equals("TERMITE")) {
                if (character1.getCurrency() >= ShipType.TERMITE.getBasePrice()) {
                    if (currentShipWorth - ShipType.TERMITE.getBasePrice() > 0) {

                        character1.setCurrency(character1.getCurrency() - (currentShipWorth - ShipType.TERMITE.getBasePrice()));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.TERMITE));
                    }  else if (currentShipWorth < ShipType.TERMITE.getBasePrice()) {
                        newAdjustedPrice = ShipType.TERMITE.getBasePrice() - currentShipWorth;

                        character1.setCurrency(character1.getCurrency() - newAdjustedPrice);
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.TERMITE));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                }

            } else if(shipType.equals("WASP")) {
                if (character1.getCurrency() >= ShipType.WASP.getBasePrice()) {
                    if (currentShipWorth - ShipType.WASP.getBasePrice() > 0) {

                        character1.setCurrency(character1.getCurrency() - (currentShipWorth - ShipType.WASP.getBasePrice()));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.WASP));
                    } else if (currentShipWorth < ShipType.WASP.getBasePrice()) {
                        newAdjustedPrice = ShipType.WASP.getBasePrice() - currentShipWorth;

                        character1.setCurrency(character1.getCurrency() - newAdjustedPrice);
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
