package com.example.spacetraders.data.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.spacetraders.R;
import com.example.spacetraders.data.Interactor;

public class MarketActivity extends AppCompatActivity {
    private TextInputLayout inputWater, inputFur, inputFood, inputOre, inputGames, inputFirearms,
            inputMedicine, inputMachines, inputNarcotics, inputRobots;
    private TextView waterPrice, furPrice, foodPrice, orePrice, gamesPrice, firearmsPrice, medicinePrice,
            machinesPrice, narcoticsPrice, robotsPrice;
    private Button confirm;
    private Spinner BuySell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);

        inputWater = findViewById(R.id.input_water);
        inputFur = findViewById(R.id.input_fur);
        inputFood = findViewById(R.id.input_food);
        inputOre = findViewById(R.id.input_ore);
        inputGames = findViewById(R.id.input_games);
        inputFirearms = findViewById(R.id.input_firearms);
        inputMedicine = findViewById(R.id.input_medicine);
        inputMachines = findViewById(R.id.input_machines);
        inputNarcotics = findViewById(R.id.input_narcotics);
        inputRobots = findViewById(R.id.input_robots);
        confirm = findViewById(R.id.button_confirm);
        BuySell = findViewById(R.id.spinner_buysell);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[] {"BUY", "SELL"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BuySell.setAdapter(adapter);

        confirm.setOnClickListener((view) -> {
            int waterAmount = (inputWater.getEditText().getText().toString().equals("")) ? (0) : (Integer.parseInt(inputWater.getEditText().getText().toString()));
            int furAmount = (inputFur.getEditText().getText().toString().equals("")) ? (0) : (Integer.parseInt(inputFur.getEditText().getText().toString()));
            int foodAmount = (inputFood.getEditText().getText().toString().equals("")) ? (0) : (Integer.parseInt(inputFood.getEditText().getText().toString()));
            int oreAmount = (inputOre.getEditText().getText().toString().equals("")) ? (0) : (Integer.parseInt(inputOre.getEditText().getText().toString()));
            int gamesAmount = (inputGames.getEditText().getText().toString().equals("")) ? (0) : (Integer.parseInt(inputGames.getEditText().getText().toString()));
            int firearmsAmount = (inputFirearms.getEditText().getText().toString().equals("")) ? (0) : (Integer.parseInt(inputFirearms.getEditText().getText().toString()));
            int medicineAmount = (inputMedicine.getEditText().getText().toString().equals("")) ? (0) : (Integer.parseInt(inputMedicine.getEditText().getText().toString()));
            int machinesAmount = (inputMachines.getEditText().getText().toString().equals("")) ? (0) : (Integer.parseInt(inputMachines.getEditText().getText().toString()));
            int narcoticsAmount = (inputNarcotics.getEditText().getText().toString().equals("")) ? (0) : (Integer.parseInt(inputNarcotics.getEditText().getText().toString()));
            int robotsAmount = (inputRobots.getEditText().getText().toString().equals("")) ? (0) : (Integer.parseInt(inputRobots.getEditText().getText().toString()));

            String choice = (String) BuySell.getSelectedItem();
            System.out.printf("%d, %d, %d, %d, %d, %d, %d, %d, %d, %d\n", waterAmount, furAmount,
                    foodAmount, oreAmount, gamesAmount, firearmsAmount, medicineAmount, machinesAmount, narcoticsAmount, robotsAmount);

            if (choice.equals("BUY")) {
                //check if purchase is valid
                System.out.println("Buying");

                //check if purchase is valid
                System.out.println("Buying");

                //sum the total amount of user's input for resources
                int sumUserAmount = waterAmount + furAmount + foodAmount + oreAmount + gamesAmount +
                        firearmsAmount + medicineAmount + machinesAmount + narcoticsAmount + robotsAmount;

                //player's credits
                int playerCurrency =  Interactor.getInteractor().getCharacter().getCredits();

                if (sumUserAmount > playerCurrency) {
                    Toast.makeText(getApplicationContext(), "Insufficient funds for purchase", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    //update player's credit
                    playerCurrency -= sumUserAmount;
                }

            } else if (choice.equals("SELL")) {
                //check if purchase is valid
                System.out.println("Selling");
            }
        });

        System.out.println("In market: " + Interactor.getInteractor().getCharacter().getName());
    }
}
