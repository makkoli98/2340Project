package com.example.spacetraders.data.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetraders.R;
import com.example.spacetraders.data.entity.Character;
import com.example.spacetraders.data.entity.Resources;
import com.example.spacetraders.data.models.Interactor;

import java.util.Random;

public class TraderEncounterActivity extends AppCompatActivity {

    TextView itemBox;
    TextView totalBox;
    Button dontTradeButton;
    Button tradeButton;
    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trader_encounter);

        itemBox = findViewById(R.id.trade_item_box);
        totalBox = findViewById(R.id.trader_total_box);
        rand = new Random();

        Character character = Interactor.getInteractor().getCharacter();
        Resources traderItem = Resources.values()[rand.nextInt(Resources.values().length)];
        int itemAmount = rand.nextInt(5) + 5;

        int currentPlayerTotalResources = 0;
        for (int resourceAmount: character.getShip().getCurrentResources()) {
            currentPlayerTotalResources += resourceAmount;
        }
        int remainingCargoSpace = character.getShip().getCargoSize() - currentPlayerTotalResources;
        int adjustedItemAmount = (itemAmount > remainingCargoSpace) ? remainingCargoSpace : itemAmount;
        int adjustedPrice = ((int) (traderItem.getBasePrice() * 0.5)) * adjustedItemAmount;

        itemBox.setText(traderItem.getName() + ": " + Integer.toString(adjustedItemAmount));
        totalBox.setText("Total price: " + Integer.toString(adjustedPrice));

        tradeButton = findViewById(R.id.trade_button);
        tradeButton.setOnClickListener((view) -> {
            if (character.getCurrency() < adjustedPrice) {
                Toast.makeText(getApplicationContext(), "Not enough currency", Toast.LENGTH_SHORT).show();
                return;
            }

            character.setCurrency(character.getCurrency() - adjustedPrice);
            int[] newResources = new int[Resources.values().length];
            newResources[traderItem.ordinal()] = adjustedItemAmount;
            character.getShip().setResource(newResources, true);
            Toast.makeText(getApplicationContext(), "Thanks for the trade!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(TraderEncounterActivity.this, MainGameActivity.class));
            finish();
        });

        dontTradeButton = findViewById(R.id.dont_trade_button);
        dontTradeButton.setOnClickListener((view) -> {
            startActivity(new Intent(TraderEncounterActivity.this, MainGameActivity.class));
            finish();
        });
    }
}
