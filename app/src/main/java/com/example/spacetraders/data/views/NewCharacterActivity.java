package com.example.spacetraders.data.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;

import android.widget.Toast;

import java.io.File;


import com.example.spacetraders.R;
import com.example.spacetraders.data.Interactor;
import com.example.spacetraders.data.entity.Character;
import com.example.spacetraders.data.entity.GameDifficulty;
import com.example.spacetraders.data.entity.Resources;
import com.example.spacetraders.data.entity.SaveDatabase;
import com.example.spacetraders.data.entity.ShipType;
import com.example.spacetraders.data.entity.Skill;
import com.example.spacetraders.data.entity.SolarSystem;
import com.example.spacetraders.data.entity.Spaceship;
import com.example.spacetraders.data.entity.Universe;

public class NewCharacterActivity extends AppCompatActivity {
    private Spinner difficulty;
    private TextInputLayout characterName;
    private TextView[] skillDisplays;
    private Button[] skillButtons;

    private Character character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_character);

        character = new Character();

        characterName = findViewById(R.id.textInputLayout);
        difficulty = findViewById(R.id.difficultySpinner);

        skillDisplays = new TextView[Skill.values().length - 1];
        skillButtons = new Button[2 * (Skill.values().length - 1)];

        android.content.res.Resources resources = getResources();
        for(Skill skill : Skill.values()) {
            if(skill == Skill.UNALLOCATED) {
                continue;
            }
            int id = resources.getIdentifier(skill.name() + "Pts", "id", getPackageName());
            skillDisplays[skill.ordinal()] = (TextView) findViewById(id);

            id = resources.getIdentifier(skill.name() + "IncButton", "id", getPackageName());
            skillButtons[skill.ordinal() * 2] = (Button) findViewById(id);
            skillButtons[skill.ordinal() * 2].setOnClickListener((view) -> {
                setPoints(skillDisplays[skill.ordinal()], skill, true);
            });

            id = resources.getIdentifier(skill.name() + "DecButton", "id", getPackageName());
            skillButtons[skill.ordinal() * 2 + 1] = (Button) findViewById(id);
            skillButtons[skill.ordinal() * 2 + 1].setOnClickListener((view) -> {
                setPoints(skillDisplays[skill.ordinal()], skill, false);
            });
        }

        ArrayAdapter<GameDifficulty> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, GameDifficulty.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulty.setAdapter(adapter);

        Button done = findViewById(R.id.doneButton);
        done.setOnClickListener((view) -> {
                int totalSkills = 0;
                for(int skill : character.getSkills()) {
                    totalSkills += skill;
                }

                if (totalSkills != 16) {
                    Toast.makeText(getApplicationContext(), "Points Distribution is not valid", Toast.LENGTH_LONG).show();
                    return;
                }

                character.setName(characterName.getEditText().getText().toString());
                character.setDifficulty((GameDifficulty) difficulty.getSelectedItem());
                Universe universe = new Universe(character.getDifficulty());

                Interactor.getInteractor().setCharacter(character);
                Interactor.getInteractor().setUniverse(universe);
                Interactor.getInteractor().getCharacter().setCurrentSolarSystem(universe.getSystems()[0]);

                //Log Character and Universe Information
                System.out.println(character);
                System.out.println(universe);

                startActivity(new Intent(NewCharacterActivity.this, MainGameActivity.class));
        });
    }

    private void setPoints(TextView pointCounter, Skill skill, boolean adding) {
        if(!character.addSkill(skill, adding ? 1 : -1)) {
            Toast.makeText(getApplicationContext(), "Points Distribution is not valid", Toast.LENGTH_LONG).show();
            return;
        }
        pointCounter.setText(Integer.toString(character.getSkill(skill)));
    }
}