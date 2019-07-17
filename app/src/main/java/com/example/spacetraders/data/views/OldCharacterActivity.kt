package com.example.spacetraders.data.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout

import com.example.spacetraders.R
import com.example.spacetraders.data.models.Interactor
import com.example.spacetraders.data.models.SaveDatabase
import com.example.spacetraders.data.entity.Character

class OldCharacterActivity : AppCompatActivity() {
    private var characterButtons: Array<Button?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_old_character)

        val saves = SaveDatabase(applicationContext)

        characterButtons = arrayOfNulls(saves.numSaves)

        val linLayout = findViewById<View>(R.id.linLayout) as LinearLayout

        val ids = IntArray(characterButtons!!.size)
        for (i in ids.indices) {
            ids[i] = i
        }
        for (id in ids) {
            try {
                val character = saves.getCharacter(id)

                val button = Button(this)
                button.id = View.generateViewId()
                button.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                button.text = "\nCharacter: " + character!!.name + "\n"
                button.setOnClickListener { view ->
                    Interactor.interactor.setSave(id, saves)
                    startActivity(Intent(this@OldCharacterActivity, MainGameActivity::class.java))
                    finish()
                }

                linLayout.addView(button)
                characterButtons!![id] = button
            } catch (e: Exception) {
                System.err.println(e)
                System.err.println("error creating button in OldCharacterActivity")
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
