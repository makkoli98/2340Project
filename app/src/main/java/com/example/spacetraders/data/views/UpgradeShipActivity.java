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

        System.out.println("Your ship worth" + shipWorth);

        if (shipType.equals("FLEA")) {
            shipPrice.setText("Price: " + (ShipType.FLEA.getBasePrice() - shipWorth ) +"c");
        } else if (shipType.equals("GNAT")){
            shipPrice.setText("Price: " + (ShipType.GNAT.getBasePrice()- shipWorth) +"c");
        } else if (shipType.equals("FIREFLY")) {
            shipPrice.setText("Price: " + (ShipType.FIREFLY.getBasePrice() - shipWorth) +"c");
        } else if(shipType.equals("MOSQUITO")) {
            shipPrice.setText("Price: " + (ShipType.MOSQUITO.getBasePrice() - shipWorth) +"c");
        } else if(shipType.equals("BUMBLEBEE")) {
            shipPrice.setText("Price: " + (ShipType.BUMBLEBEE.getBasePrice() - shipWorth) +"c");
        } else if(shipType.equals("BEETLE")) {
            shipPrice.setText("Price: " + (ShipType.BEETLE.getBasePrice() - shipWorth) +"c");
        } else if(shipType.equals("HORNET")) {
            shipPrice.setText("Price: " + (ShipType.HORNET.getBasePrice() - shipWorth) +"c");
        } else if(shipType.equals("GRASSHOPPER")) {
            shipPrice.setText("Price: " + (ShipType.GRASSHOPPER.getBasePrice() - shipWorth) +"c");
        } else if(shipType.equals("TERMITE")) {
            shipPrice.setText("Price: " + (ShipType.TERMITE.getBasePrice() - shipWorth) +"c");
        } else if(shipType.equals("WASP")) {
            shipPrice.setText("Price: " + (ShipType.WASP.getBasePrice() - shipWorth) +"c");
        }



        purchaseButton.setOnClickListener((View v) -> {
            int currentShipWorth = character1.currShipWorth();


            int newAdjustedPrice = 0;

            if (shipType.equals("FLEA")) {
                if (ShipType.FLEA.getBasePrice() - currentShipWorth <= 0) {
                    character1.setCurrency(character1.getCurrency() - (ShipType.FLEA.getBasePrice() - currentShipWorth));
                    viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                    character1.setShip(new Spaceship(ShipType.FLEA));
                    sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
                    plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
                    mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());
                } else if (ShipType.FLEA.getBasePrice() - currentShipWorth > 0) {
                    if (character1.getCurrency() > (ShipType.FLEA.getBasePrice() - currentShipWorth)) {
                        character1.setCurrency(character1.getCurrency() - (ShipType.FLEA.getBasePrice() - currentShipWorth));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.FLEA));
                        sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
                        plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
                        mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());

                    } else {
                        Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                    }
                }

            } else if (shipType.equals("GNAT")){
                if (ShipType.GNAT.getBasePrice() - currentShipWorth <= 0) {
                    character1.setCurrency(character1.getCurrency() - (ShipType.GNAT.getBasePrice() - currentShipWorth));
                    viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                    character1.setShip(new Spaceship(ShipType.GNAT));
                    sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
                    plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
                    mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());
                } else if (ShipType.GNAT.getBasePrice() - currentShipWorth > 0) {
                    if (character1.getCurrency() > (ShipType.GNAT.getBasePrice() - currentShipWorth)) {
                        character1.setCurrency(character1.getCurrency() - (ShipType.GNAT.getBasePrice() - currentShipWorth));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.GNAT));
                        sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
                        plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
                        mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());

                    } else {
                        Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                    }
                }

            } else if (shipType.equals("FIREFLY")) {
                if (ShipType.FIREFLY.getBasePrice() - currentShipWorth <= 0) {
                    character1.setCurrency(character1.getCurrency() - (ShipType.FIREFLY.getBasePrice() - currentShipWorth));
                    viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                    character1.setShip(new Spaceship(ShipType.FIREFLY));
                    sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
                    plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
                    mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());
                } else if (ShipType.FIREFLY.getBasePrice() - currentShipWorth > 0) {
                    if (character1.getCurrency() > (ShipType.FIREFLY.getBasePrice() - currentShipWorth)) {
                        character1.setCurrency(character1.getCurrency() - (ShipType.FIREFLY.getBasePrice() - currentShipWorth));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.FIREFLY));
                        sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
                        plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
                        mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());

                    } else {
                        Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                    }
                }

            } else if(shipType.equals("MOSQUITO")) {
                if (ShipType.MOSQUITO.getBasePrice() - currentShipWorth <= 0) {
                    character1.setCurrency(character1.getCurrency() - (ShipType.MOSQUITO.getBasePrice() - currentShipWorth));
                    viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                    character1.setShip(new Spaceship(ShipType.MOSQUITO));
                    sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
                    plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
                    mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());
                } else if (ShipType.MOSQUITO.getBasePrice() - currentShipWorth > 0) {
                    if (character1.getCurrency() > (ShipType.MOSQUITO.getBasePrice() - currentShipWorth)) {
                        character1.setCurrency(character1.getCurrency() - (ShipType.MOSQUITO.getBasePrice() - currentShipWorth));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.MOSQUITO));
                        sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
                        plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
                        mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());

                    } else {
                        Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                    }
                }

            } else if(shipType.equals("BUMBLEBEE")) {
                if (ShipType.BUMBLEBEE.getBasePrice() - currentShipWorth <= 0) {
                    character1.setCurrency(character1.getCurrency() - (ShipType.BUMBLEBEE.getBasePrice() - currentShipWorth));
                    viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                    character1.setShip(new Spaceship(ShipType.BUMBLEBEE));
                    sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
                    plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
                    mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());
                } else if (ShipType.BUMBLEBEE.getBasePrice() - currentShipWorth > 0) {
                    if (character1.getCurrency() > (ShipType.BUMBLEBEE.getBasePrice() - currentShipWorth)) {
                        character1.setCurrency(character1.getCurrency() - (ShipType.BUMBLEBEE.getBasePrice() - currentShipWorth));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.BUMBLEBEE));
                        sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
                        plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
                        mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());

                    } else {
                        Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                    }
                }

            } else if(shipType.equals("BEETLE")) {
                if (ShipType.BEETLE.getBasePrice() - currentShipWorth <= 0) {
                    character1.setCurrency(character1.getCurrency() - (ShipType.BEETLE.getBasePrice() - currentShipWorth));
                    viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                    character1.setShip(new Spaceship(ShipType.BEETLE));
                    sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
                    plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
                    mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());
                } else if (ShipType.BEETLE.getBasePrice() - currentShipWorth > 0) {
                    if (character1.getCurrency() > (ShipType.BEETLE.getBasePrice() - currentShipWorth)) {
                        character1.setCurrency(character1.getCurrency() - (ShipType.BEETLE.getBasePrice() - currentShipWorth));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.BEETLE));
                        sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
                        plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
                        mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());

                    } else {
                        Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                    }
                }

            } else if(shipType.equals("HORNET")) {
                if (ShipType.HORNET.getBasePrice() - currentShipWorth <= 0) {
                    character1.setCurrency(character1.getCurrency() - (ShipType.HORNET.getBasePrice() - currentShipWorth));
                    viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                    character1.setShip(new Spaceship(ShipType.HORNET));
                    sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
                    plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
                    mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());
                } else if (ShipType.HORNET.getBasePrice() - currentShipWorth > 0) {
                    if (character1.getCurrency() > (ShipType.HORNET.getBasePrice() - currentShipWorth)) {
                        character1.setCurrency(character1.getCurrency() - (ShipType.HORNET.getBasePrice() - currentShipWorth));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.HORNET));
                        sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
                        plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
                        mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());

                    } else {
                        Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                    }
                }

            } else if(shipType.equals("GRASSHOPPER")) {
                if (ShipType.GRASSHOPPER.getBasePrice() - currentShipWorth <= 0) {
                    character1.setCurrency(character1.getCurrency() - (ShipType.GRASSHOPPER.getBasePrice() - currentShipWorth));
                    viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                    character1.setShip(new Spaceship(ShipType.GRASSHOPPER));
                    sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
                    plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
                    mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());
                } else if (ShipType.GRASSHOPPER.getBasePrice() - currentShipWorth > 0) {
                    if (character1.getCurrency() > (ShipType.GRASSHOPPER.getBasePrice() - currentShipWorth)) {
                        character1.setCurrency(character1.getCurrency() - (ShipType.GRASSHOPPER.getBasePrice() - currentShipWorth));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.GRASSHOPPER));
                        sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
                        plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
                        mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());

                    } else {
                        Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                    }
                }

            } else if(shipType.equals("TERMITE")) {
                if (ShipType.TERMITE.getBasePrice() - currentShipWorth <= 0) {
                    character1.setCurrency(character1.getCurrency() - (ShipType.TERMITE.getBasePrice() - currentShipWorth));
                    viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                    character1.setShip(new Spaceship(ShipType.TERMITE));
                    sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
                    plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
                    mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());
                } else if (ShipType.TERMITE.getBasePrice() - currentShipWorth > 0) {
                    if (character1.getCurrency() > (ShipType.TERMITE.getBasePrice() - currentShipWorth)) {
                        character1.setCurrency(character1.getCurrency() - (ShipType.TERMITE.getBasePrice() - currentShipWorth));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.TERMITE));
                        sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
                        plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
                        mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());

                    } else {
                        Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                    }
                }

            } else if(shipType.equals("WASP")) {
                if (ShipType.WASP.getBasePrice() - currentShipWorth <= 0) {
                    character1.setCurrency(character1.getCurrency() - (ShipType.WASP.getBasePrice() - currentShipWorth));
                    viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                    character1.setShip(new Spaceship(ShipType.WASP));
                    sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
                    plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
                    mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());
                } else if (ShipType.WASP.getBasePrice() - currentShipWorth > 0) {
                    if (character1.getCurrency() > (ShipType.WASP.getBasePrice() - currentShipWorth)) {
                        character1.setCurrency(character1.getCurrency() - (ShipType.WASP.getBasePrice() - currentShipWorth));
                        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");
                        character1.setShip(new Spaceship(ShipType.WASP));
                        sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
                        plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
                        mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());

                    } else {
                        Toast.makeText(getApplicationContext(), new String("Insufficient funds"), Toast.LENGTH_SHORT).show();
                    }
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

    @Override
    public void onResume() {
        super.onResume();
        character1 = Interactor.getInteractor().getCharacter();
        sonicLaser_num = findViewById(R.id.displaySonicNum);
        plasmaLaser_num = findViewById(R.id.displayPlasmaNum);
        mesonPhaser_num = findViewById(R.id.displayMesonNum);
        sonicLaser_num.setText("Sonic Ray: " + character1.getShip().getSonicRayAmount());
        plasmaLaser_num.setText("Plasma Ray: " + character1.getShip().getPlasmaRayAmount());
        mesonPhaser_num.setText("Meson Phaser: " + character1.getShip().getMesonPhaserAmount());


    }

}
