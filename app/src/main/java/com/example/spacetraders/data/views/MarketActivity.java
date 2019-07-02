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
import com.example.spacetraders.data.entity.Spaceship;

import java.util.Arrays;

public class MarketActivity extends AppCompatActivity {
    private TextView currencyDisplay;
    private TextInputLayout[] inputs;
    private TextView[] amounts;
    private TextView[] prices;
    private Button confirm;
    private Spinner BuySell;
    private Character character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);

        character = Interactor.getInteractor().getCharacter();

        inputs = new TextInputLayout[Resources.values().length];

        currencyDisplay = findViewById(R.id.label_currency);
        currencyDisplay.setText(new Integer(character.getCredits()).toString());

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

        amounts = new TextView[Resources.values().length];
        amounts[Resources.WATER.ordinal()] = findViewById(R.id.label_amountWater);
        amounts[Resources.FURS.ordinal()] = findViewById(R.id.label_amountFur);
        amounts[Resources.FOOD.ordinal()] = findViewById(R.id.label_amountFood);
        amounts[Resources.ORE.ordinal()] = findViewById(R.id.label_amountOre);
        amounts[Resources.GAMES.ordinal()] = findViewById(R.id.label_amountGames);
        amounts[Resources.FIREARMS.ordinal()] = findViewById(R.id.label_amountFirearms);
        amounts[Resources.MEDICINE.ordinal()] = findViewById(R.id.label_amountMedicine);
        amounts[Resources.MACHINES.ordinal()] = findViewById(R.id.label_amountMachines);
        amounts[Resources.NARCOTICS.ordinal()] = findViewById(R.id.label_amountNarcotics);
        amounts[Resources.ROBOTS.ordinal()] = findViewById(R.id.label_amountRobots);

        prices = new TextView[Resources.values().length];
        prices[Resources.WATER.ordinal()] = findViewById(R.id.label_water);
        prices[Resources.FURS.ordinal()] = findViewById(R.id.label_fur);
        prices[Resources.FOOD.ordinal()] = findViewById(R.id.label_food);
        prices[Resources.ORE.ordinal()] = findViewById(R.id.label_ore);
        prices[Resources.GAMES.ordinal()] = findViewById(R.id.label_games);
        prices[Resources.FIREARMS.ordinal()] = findViewById(R.id.label_firearms);
        prices[Resources.MEDICINE.ordinal()] = findViewById(R.id.label_medicine);
        prices[Resources.MACHINES.ordinal()] = findViewById(R.id.label_machines);
        prices[Resources.NARCOTICS.ordinal()] = findViewById(R.id.label_narcotics);
        prices[Resources.ROBOTS.ordinal()] = findViewById(R.id.label_robots);

        int[] resourcePrices = character.getCurrentSolarSystem().getPlanets()[0].getMarket().getResourcePrices();
        int[] resourceAmounts = character.getCurrentSolarSystem().getPlanets()[0].getMarket().getResourceAmount();
        for (Resources r: Resources.values()) {
            String s = r.toString() + "- " + resourcePrices[r.ordinal()] + "c";
            prices[r.ordinal()].setText(s);
            amounts[r.ordinal()].setText(Integer.toString(resourceAmounts[r.ordinal()]));
        }
        System.out.println(Arrays.toString(resourcePrices));
        System.out.println(Arrays.toString(resourceAmounts));

        confirm = findViewById(R.id.button_confirm);
        BuySell = findViewById(R.id.spinner_buysell);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[] {"BUY", "SELL"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BuySell.setAdapter(adapter);

        confirm.setOnClickListener((view) -> {
            int[] productQuantities = new int[Resources.values().length];
            int sumUserAmount = 0;
            int cost = 0;
            for (int i = 0; i < productQuantities.length; i++) {
                productQuantities[i] = getAmount(inputs[i]);
                cost += (productQuantities[i] * resourcePrices[i]);
                sumUserAmount += productQuantities[i];
            }

            String choice = (String) BuySell.getSelectedItem();
            //player's credits
            int playerCurrency =  character.getCredits();
            if (choice.equals("BUY")) {
                // check if purchase is valid
                Boolean purchaseValid = true;
                for (int i = 0; i < productQuantities.length; i++) {
                    if (productQuantities[i] > resourceAmounts[i]) {
                        purchaseValid = false;
                    }
                }
                if (character.getShip().getCargoSize() - Arrays.stream(character.getShip().getCurrentResources()).sum() >= sumUserAmount && cost <= playerCurrency && purchaseValid) {
                    character.getShip().setResource(productQuantities, true);
                    System.out.println(Arrays.toString(character.getShip().getCurrentResources()));
                    character.setCredits(character.getCredits() - cost);
                    currencyDisplay.setText(Integer.toString(character.getCredits()));
                    int[] newAmounts = new int[resourceAmounts.length];
                    for (int i = 0; i < amounts.length; i++) {
                        newAmounts[i] = resourceAmounts[i];
                        int currentAmount = Integer.parseInt(amounts[i].getText().toString());
                        int correctAmount = currentAmount - productQuantities[i];
                        amounts[i].setText(Integer.toString(correctAmount));
                    }
                    character.getCurrentSolarSystem().getPlanets()[0].getMarket().setResourceAmount(newAmounts);
                } else {
                    Toast.makeText(getApplicationContext(), "Insufficient cargo space or currency or amount for purchase", Toast.LENGTH_LONG).show();
                }

            } else if (choice.equals("SELL")) {
                //check if purchase is valid
                //check if each cargo slot is valid
                Boolean cargoValid = true;
                for (int i = 0; i < productQuantities.length; i++) {
                    if (productQuantities[i] > character.getShip().getCurrentResources()[i]) {
                        // if any resource amount is invalid, entire purchase is invalid
                        cargoValid = false;
                    }
                }
                if (cargoValid) {
                    character.getShip().setResource(productQuantities, false);
                    System.out.println(Arrays.toString(character.getShip().getCurrentResources()));
                    character.setCredits(character.getCredits() + cost);
                    currencyDisplay.setText(Integer.toString(character.getCredits()));
                    int[] newAmounts = new int[resourceAmounts.length];
                    for (int i = 0; i < amounts.length; i++) {
                        newAmounts[i] = resourceAmounts[i];
                        int currentAmount = Integer.parseInt(amounts[i].getText().toString());
                        int correctAmount = currentAmount + productQuantities[i];
                        amounts[i].setText(Integer.toString(correctAmount));
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid product amount", Toast.LENGTH_LONG).show();
                }
            }
        });

        System.out.println("In market: " + character.getName());
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
        super.onPause();
        Interactor.getInteractor().setCharacter(character);
    }

}
