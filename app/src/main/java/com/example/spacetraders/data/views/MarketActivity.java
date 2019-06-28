package com.example.spacetraders.data.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.spacetraders.R;
import com.example.spacetraders.data.Interactor;

public class MarketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);

        System.out.println("In market: " + Interactor.getInteractor().getCharacter().getName());
    }
}
