package com.example.spacetraders.data.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.spacetraders.R;
import com.example.spacetraders.data.models.Interactor;
import com.example.spacetraders.data.models.SaveDatabase;
import com.example.spacetraders.data.entity.Character;

public class OldCharacterActivity extends AppCompatActivity {
    Button[] characterButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_character);

        SaveDatabase saves = new SaveDatabase(getApplicationContext());

        characterButtons = new Button[saves.getNumSaves()];

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);

        int[] ids = new int[characterButtons.length];
        for(int i = 0; i < ids.length; i++) {
            ids[i] = i;
        }
        for(int id : ids) {
            try {
                Character character = saves.getCharacter(id);

                Button button = new Button(this);
                button.setId(View.generateViewId());
                button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                button.setText("\nCharacter: " + character.getName() + "\n");
                button.setOnClickListener((view) -> {
                    Interactor.getInteractor().setSave(id, saves);
                    startActivity(new Intent(OldCharacterActivity.this, MainGameActivity.class));
                    finish();
                });

                linLayout.addView(button);
                characterButtons[id] = button;
            } catch(Exception e) {
                System.err.println(e);
                System.err.println("error creating button in OldCharacterActivity");
            }
        }
        /*
        for(int i = 0; i < characterButtons.length; i++) {
            try {
                Character character = saves.getCharacter(i);

                Button button = new Button(this);
                button.setId(View.generateViewId());
                button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                button.setText("\nCharacter: " + character.getName() + "\n");
                button.setOnClickListener((view) -> {
                    //Interactor.getInteractor().setSave(id, saves);
                    System.out.println(i);
                    startActivity(new Intent(OldCharacterActivity.this, MainGameActivity.class));
                    finish();
                });

                linLayout.addView(button);
                characterButtons[i] = button;
            } catch(Exception e) {
                System.err.println(e);
                System.err.println("error creating button in OldCharacterActivity");
            }
        }
        */
    }
}
