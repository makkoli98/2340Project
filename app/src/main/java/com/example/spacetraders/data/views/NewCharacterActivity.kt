package com.example.spacetraders.data.views

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Button

import android.widget.Toast


import com.example.spacetraders.R
import com.example.spacetraders.data.models.Interactor
import com.example.spacetraders.data.entity.Character
import com.example.spacetraders.data.entity.GameDifficulty

import com.example.spacetraders.data.entity.Resources
import com.example.spacetraders.data.models.SaveDatabase
import com.example.spacetraders.data.entity.ShipType
import com.example.spacetraders.data.entity.Skill
import com.example.spacetraders.data.entity.SolarSystem
import com.example.spacetraders.data.entity.Spaceship
import com.example.spacetraders.data.entity.Universe
import com.example.spacetraders.data.viewmodels.NewCharacterViewModel

class NewCharacterActivity : AppCompatActivity() {
    private var difficulty: Spinner? = null
    private var characterName: TextInputLayout? = null
    private var skillDisplays: Array<TextView?>? = null
    private var skillButtons: Array<Button?>? = null

    private var viewModel: NewCharacterViewModel? = null

    private var character: Character? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)

        //SaveDatabase saveDatabase = new SaveDatabase(getApplicationContext());
        //saveDatabase.deleteAllFiles();

        character = Character()

        characterName = findViewById(R.id.textInputLayout)
        difficulty = findViewById(R.id.difficultySpinner)

        skillDisplays = arrayOfNulls(Skill.values().size - 1)
        skillButtons = arrayOfNulls(2 * (Skill.values().size - 1))

        val resources = resources
        for (skill in Skill.values()) {
            if (skill == Skill.UNALLOCATED) {
                continue
            }
            var id = resources.getIdentifier(skill.name + "Pts", "id", packageName)
            skillDisplays!![skill.ordinal] = findViewById<View>(id) as TextView

            id = resources.getIdentifier(skill.name + "IncButton", "id", packageName)
            skillButtons!![skill.ordinal * 2] = findViewById<View>(id) as Button
            skillButtons!![skill.ordinal * 2]!!.setOnClickListener { view -> setPoints(skillDisplays!![skill.ordinal]!!, skill, true) }

            id = resources.getIdentifier(skill.name + "DecButton", "id", packageName)
            skillButtons!![skill.ordinal * 2 + 1] = findViewById<View>(id) as Button
            skillButtons!![skill.ordinal * 2 + 1]!!.setOnClickListener { view -> setPoints(skillDisplays!![skill.ordinal]!!, skill, false) }
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, GameDifficulty.values())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        difficulty!!.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(NewCharacterViewModel::class.java!!)

        val done = findViewById<Button>(R.id.doneButton)
        done.setOnClickListener { view ->
            var totalSkills = 0
            for (skill in character!!.skills) {
                totalSkills += skill
            }
            if (totalSkills == 16) {
                val universe = Universe(character!!.difficulty!!)
                viewModel!!.createUniverse(universe)
                viewModel!!.createCharacter(character!!)
                viewModel!!.setDifficulty(difficulty!!.selectedItem as GameDifficulty)
                viewModel!!.setName(characterName!!.editText!!.text.toString())

                //Log Character and Universe Information
                println(character)
                println(universe)

                Interactor.interactor.createSave(applicationContext, character!!, universe)

                startActivity(Intent(this@NewCharacterActivity, MainGameActivity::class.java))
            }
            else {
                Toast.makeText(applicationContext, "Points Distribution is not valid", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setPoints(pointCounter: TextView, skill: Skill, adding: Boolean) {
        if (!character!!.addSkill(skill, if (adding) 1 else -1)) {
            Toast.makeText(applicationContext, "Points Distribution is not valid", Toast.LENGTH_LONG).show()
            return
        }
        pointCounter.text = Integer.toString(character!!.getSkill(skill))
    }
}