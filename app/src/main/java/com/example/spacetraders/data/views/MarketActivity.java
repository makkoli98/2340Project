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
import com.example.spacetraders.data.entity.Resources;
import com.example.spacetraders.data.entity.Character;

public class MarketActivity extends AppCompatActivity {
    private TextInputLayout[] inputs;
    private TextView waterPrice, furPrice, foodPrice, orePrice, gamesPrice, firearmsPrice, medicinePrice,
            machinesPrice, narcoticsPrice, robotsPrice;
    private Button confirm;
    private Spinner BuySell;
    private Character character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);

        character = Interactor.getInteractor().getCharacter();

        inputs = new TextInputLayout[Resources.values().length];

        inputs[Resources.WATER.ordinal()] = findViewById(R.id.input_water);
        inputs[Resources.FURS.ordinal()] = findViewById(R.id.input_fur);
        inputs[Resources.FOOD.ordinal()] = findViewById(R.id.input_food);
        inputs[Resources.ORE.ordinal()] = findViewById(R.id.input_ore);
        inputs[Resources.GAMES.ordinal()] = findViewById(R.id.input_games);
        inputs[Resources.FIREARMS.ordinal()] = findViewById(R.id.input_firearms);
        inputs[Resources.MEDICINE.ordinal()] = findViewById(R.id.input_medicine);
        inputs[Resources.MACHINES.ordinal()] = findViewById(R.id.input_machines);
        inputs[Resources.NARCOTICS.ordinal()] = findViewById(R.id.input_narcotics);
        inputs[Resources.ROBOTS.ordinal()] = findViewById(R.id.input_robots);
        confirm = findViewById(R.id.button_confirm);
        BuySell = findViewById(R.id.spinner_buysell);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[] {"BUY", "SELL"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BuySell.setAdapter(adapter);

        confirm.setOnClickListener((view) -> {
            for(TextInputLayout r : inputs) {
                System.out.println(getAmount(r));
            }

            String choice = (String) BuySell.getSelectedItem();
            if (choice.equals("BUY")) {
                //check if purchase is valid
                System.out.println("Buying");

                //check if purchase is valid
                System.out.println("Buying");

                //sum the total amount of user's input for resources
                int sumUserAmount = 0;
                for(TextInputLayout r : inputs) {
                    sumUserAmount += getAmount(r);
                }

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

    private int getAmount(TextInputLayout inputText) {
        if(inputText.getEditText().getText().toString().equals("")) {
            return 0;
        } else {
            return Integer.parseInt(inputText.getEditText().getText().toString());
        }
    }

    @Override
    protected void onPause() {
        Interactor.getInteractor().setCharacter(character);
    }

}
