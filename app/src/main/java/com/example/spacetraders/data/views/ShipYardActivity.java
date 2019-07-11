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
        previewButton = findViewById(R.id.previewButton);
        purchaseButton = findViewById(R.id.purchaseButtn);
        shipSpinner = findViewById(R.id.shipSpinner);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ship_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shipSpinner.setAdapter(adapter);
        shipSpinner.setOnItemSelectedListener(this);


        viewCurrency.setText("Credits: " + character1.getCurrency() + "c");

        previewButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(ShipYardActivity.this, PreviewActivity.class);
            startActivity(intent);
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        shipType = parent.getItemAtPosition(position).toString();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ship_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Intent intent = new Intent(this, PreviewActivity.class);
        intent.putExtra(shipType, adapter.getItem(position));
        startActivityForResult(intent, 1);
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
