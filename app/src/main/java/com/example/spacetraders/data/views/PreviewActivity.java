package com.example.spacetraders.data.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetraders.R;
import com.example.spacetraders.data.entity.ShipType;


public class PreviewActivity extends AppCompatActivity {
    //private ShipType type;
    private TextView cargoSize;
    private TextView shipName;
    private TextView maxHealth;
    private TextView weaponsCapacity;
    private TextView fuelEfficiency;
    private TextView basePrice;
    private TextView maxFuel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        shipName = findViewById(R.id.displayShipType);
        cargoSize = findViewById(R.id.displayCargoSize);
        maxHealth = findViewById(R.id.displayHealth);
        weaponsCapacity = findViewById(R.id.displayWeaponCap);
        fuelEfficiency = findViewById(R.id.displayEfficiency);
        basePrice = findViewById(R.id.displayPrice);
        //type = new ShipType();

        String text = "If you have cargo on board, you will lose that cargo without any compensation upon purchase";

        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width *.8), (int)(height*.6));

        String shipType = getIntent().getStringExtra(ShipYardActivity.shipType);

        if (shipType.equals("FLEA 500c")) {
            shipName.setText(ShipType.FLEA.getName());
            cargoSize.setText("" + ShipType.FLEA.getCargoSize());
            maxHealth.setText("" + ShipType.FLEA.getMaximumHealth());
            fuelEfficiency.setText("" + ShipType.FLEA.getFuelEfficiency());
            weaponsCapacity.setText("" + ShipType.FLEA.getMaxWeaponsAmount());
            basePrice.setText("" + ShipType.FLEA.getBasePrice());
        } else if (shipType.equals("GNAT 1000c")){
            shipName.setText(ShipType.GNAT.getName());
            cargoSize.setText("" + ShipType.GNAT.getCargoSize());
            maxHealth.setText("" + ShipType.GNAT.getMaximumHealth());
            fuelEfficiency.setText("" + ShipType.GNAT.getFuelEfficiency());
            weaponsCapacity.setText("" + ShipType.GNAT.getMaxWeaponsAmount());
            basePrice.setText("" + ShipType.GNAT.getBasePrice());
        } else if (shipType.equals("FIREFLY 750c")) {
            shipName.setText(ShipType.FIREFLY.getName());
            cargoSize.setText("" + ShipType.FIREFLY.getCargoSize());
            maxHealth.setText("" + ShipType.FIREFLY.getMaximumHealth());
            fuelEfficiency.setText("" + ShipType.FIREFLY.getFuelEfficiency());
            weaponsCapacity.setText("" + ShipType.FIREFLY.getMaxWeaponsAmount());
            basePrice.setText("" + ShipType.FIREFLY.getBasePrice());
        } else if(shipType.equals("MOSQUITO 1500c")) {
            shipName.setText(ShipType.MOSQUITO.getName());
            cargoSize.setText("" + ShipType.MOSQUITO.getCargoSize());
            maxHealth.setText("" + ShipType.MOSQUITO.getMaximumHealth());
            fuelEfficiency.setText("" + ShipType.MOSQUITO.getFuelEfficiency());
            weaponsCapacity.setText("" + ShipType.MOSQUITO.getMaxWeaponsAmount());
            basePrice.setText("" + ShipType.MOSQUITO.getBasePrice());
        } else if(shipType.equals("BUMBLEBEE 2000c")) {
            shipName.setText(ShipType.BUMBLEBEE.getName());
            cargoSize.setText("" + ShipType.BUMBLEBEE.getCargoSize());
            maxHealth.setText("" + ShipType.BUMBLEBEE.getMaximumHealth());
            fuelEfficiency.setText("" + ShipType.BUMBLEBEE.getFuelEfficiency());
            weaponsCapacity.setText("" + ShipType.BUMBLEBEE.getMaxWeaponsAmount());
            basePrice.setText("" + ShipType.BUMBLEBEE.getBasePrice());
        } else if(shipType.equals("BEETLE 3500c")) {
            shipName.setText(ShipType.BEETLE.getName());
            cargoSize.setText("" + ShipType.BEETLE.getCargoSize());
            maxHealth.setText("" + ShipType.BEETLE.getMaximumHealth());
            fuelEfficiency.setText("" + ShipType.BEETLE.getFuelEfficiency());
            weaponsCapacity.setText("" + ShipType.BEETLE.getMaxWeaponsAmount());
            basePrice.setText("" + ShipType.BEETLE.getBasePrice());
        } else if(shipType.equals("HORNET 3000c")) {
            shipName.setText(ShipType.HORNET.getName());
            cargoSize.setText("" + ShipType.HORNET.getCargoSize());
            maxHealth.setText("" + ShipType.HORNET.getMaximumHealth());
            fuelEfficiency.setText("" + ShipType.HORNET.getFuelEfficiency());
            weaponsCapacity.setText("" + ShipType.HORNET.getMaxWeaponsAmount());
            basePrice.setText("" + ShipType.HORNET.getBasePrice());
        } else if(shipType.equals("GRASSHOPPER 2500c")) {
            shipName.setText(ShipType.GRASSHOPPER.getName());
            cargoSize.setText("" + ShipType.GRASSHOPPER.getCargoSize());
            maxHealth.setText("" + ShipType.GRASSHOPPER.getMaximumHealth());
            fuelEfficiency.setText("" + ShipType.GRASSHOPPER.getFuelEfficiency());
            weaponsCapacity.setText("" + ShipType.GRASSHOPPER.getMaxWeaponsAmount());
            basePrice.setText("" + ShipType.GRASSHOPPER.getBasePrice());
        } else if(shipType.equals("TERMITE 4500c")) {
            shipName.setText(ShipType.TERMITE.getName());
            cargoSize.setText("" + ShipType.TERMITE.getCargoSize());
            maxHealth.setText("" + ShipType.TERMITE.getMaximumHealth());
            fuelEfficiency.setText("" + ShipType.TERMITE.getFuelEfficiency());
            weaponsCapacity.setText("" + ShipType.TERMITE.getMaxWeaponsAmount());
            basePrice.setText("" + ShipType.TERMITE.getBasePrice());
        } else if(shipType.equals("WASP 6000c")) {
            shipName.setText(ShipType.WASP.getName());
            cargoSize.setText("" + ShipType.WASP.getCargoSize());
            maxHealth.setText("" + ShipType.WASP.getMaximumHealth());
            weaponsCapacity.setText("" + ShipType.TERMITE.getMaxWeaponsAmount());
            basePrice.setText("" + ShipType.WASP.getBasePrice());
        } else {

        }

    }
}
