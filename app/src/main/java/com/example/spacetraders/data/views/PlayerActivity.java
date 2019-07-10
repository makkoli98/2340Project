package com.example.spacetraders.data.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.spacetraders.R;
import com.example.spacetraders.data.entity.Character;
import com.example.spacetraders.data.entity.Skill;
import com.example.spacetraders.data.models.Interactor;

public class PlayerActivity extends AppCompatActivity {
    TextView name;
    TextView difficulty;
    TextView currency;
    TextView pilotSkill;
    TextView fighterSkill;
    TextView traderSkill;
    TextView engrSkill;
    Character character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        name = findViewById(R.id.name_box);
        difficulty = findViewById(R.id.difficulty_box);
        currency = findViewById(R.id.currency_box);
        pilotSkill = findViewById(R.id.pilot_box);
        fighterSkill = findViewById(R.id.fighter_box);
        traderSkill = findViewById(R.id.trader_box);
        engrSkill = findViewById(R.id.engr_box);

        character = Interactor.getInteractor().getCharacter();

        String nameText = "Name: " + character.getName();
        String difficultyText = "Difficulty: " + character.getDifficulty().name();
        String currencyText = "Currency: " + new Integer(character.getCurrency()).toString();
        String pilotText = "Pilot: " + new Integer(character.getSkill(Skill.PILOT)).toString();
        String fighterText = "Fighter: " + new Integer(character.getSkill(Skill.FIGHTER)).toString();
        String traderText = "Trader: " + new Integer(character.getSkill(Skill.TRADER)).toString();
        String engrText = "Engineer: " + new Integer(character.getSkill(Skill.ENGINEER)).toString();

        name.setText(nameText);
        difficulty.setText(difficultyText);
        currency.setText(currencyText);
        pilotSkill.setText(pilotText);
        fighterSkill.setText(fighterText);
        traderSkill.setText(traderText);
        engrSkill.setText(engrText);
    }
}
