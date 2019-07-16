package com.example.spacetraders.data.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetraders.data.entity.Mercenary;
import com.example.spacetraders.data.entity.Skill;
import com.example.spacetraders.data.models.Interactor;
import com.example.spacetraders.data.entity.Planet;

import com.example.spacetraders.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MercenaryActivity extends AppCompatActivity {
    private LinearLayout availableMercenaries;
    private LinearLayout yourMercenaries;
    private TextView playerCurrency, pilotPoints, fighterPoints, traderPoints, engineerPoints;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_mercenary);

        playerCurrency = findViewById(R.id.playerCurrency);
        playerCurrency.setText(Integer.toString(Interactor.getInteractor().getCharacter().getCurrency()));
        availableMercenaries = findViewById(R.id.availableMercenaries);
        yourMercenaries = findViewById(R.id.yourMercenaries);
        pilotPoints = findViewById(R.id.pilotPts);
        fighterPoints = findViewById(R.id.fighterPts);
        traderPoints = findViewById(R.id.traderPts);
        engineerPoints = findViewById(R.id.engrPts);

        setSkillPoints();

        ArrayList<Mercenary> characterMercenaries = Interactor.getInteractor().getCharacter().getMercenaries();
        ArrayList<Mercenary> planetMercenaries = Interactor.getInteractor().getCharacter().getCurrentSolarSystem().getCurrentPlanet().getAvailableMercenaries();

        //Adds the mercenaries available for sale
        for (Mercenary mercenary: planetMercenaries) {
            Button button = new Button(this);
            button.setText(mercenary.toString());
            button.setOnClickListener((View v) -> {
                if (Interactor.getInteractor().getCharacter().getCurrency() >= mercenary.getPrice()) {
                    Interactor.getInteractor().getCharacter().getCurrentSolarSystem().getCurrentPlanet().removeMercenary(mercenary);
                    availableMercenaries.removeView(button);
                    Interactor.getInteractor().getCharacter().addMercenary(mercenary);
                    Button button1 = new Button(this);
                    button1.setText(mercenary.toString());
                    yourMercenaries.addView(button1);
                    Interactor.getInteractor().getCharacter().setCurrency(Interactor.getInteractor().getCharacter().getCurrency() - mercenary.getPrice());
                    playerCurrency.setText(Integer.toString(Interactor.getInteractor().getCharacter().getCurrency()));
                    Interactor.getInteractor().getCharacter().adjustPoints(mercenary.getSkills());
                    setSkillPoints();
                } else {
                    Toast.makeText(this, "Insufficient currency for purchase", Toast.LENGTH_LONG).show();
                }

            });
            availableMercenaries.addView(button);
        }
        //Adds the mercenaries in your possession
        for (Mercenary mercenary: characterMercenaries) {
            Button button = new Button(this);
            button.setText(mercenary.toString());
            yourMercenaries.addView(button);
        }
    }

    private void setSkillPoints() {
        pilotPoints.setText(Integer.toString(Interactor.getInteractor().getCharacter().getSkill(Skill.PILOT)));
        fighterPoints.setText(Integer.toString(Interactor.getInteractor().getCharacter().getSkill(Skill.FIGHTER)));
        traderPoints.setText(Integer.toString(Interactor.getInteractor().getCharacter().getSkill(Skill.TRADER)));
        engineerPoints.setText(Integer.toString(Interactor.getInteractor().getCharacter().getSkill(Skill.ENGINEER)));
    }
}
