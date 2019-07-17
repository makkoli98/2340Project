package com.example.spacetraders.data.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import com.example.spacetraders.R
import com.example.spacetraders.data.entity.Character
import com.example.spacetraders.data.entity.Skill
import com.example.spacetraders.data.models.Interactor

class PlayerActivity : AppCompatActivity() {
    internal var name: TextView? = null
    internal var difficulty: TextView? = null
    internal var currency: TextView? = null
    internal var pilotSkill: TextView? = null
    internal var fighterSkill: TextView? = null
    internal var traderSkill: TextView? = null
    internal var engrSkill: TextView? = null
    internal var character: Character? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        name = findViewById(R.id.name_box)
        difficulty = findViewById(R.id.difficulty_box)
        currency = findViewById(R.id.currency_box)
        pilotSkill = findViewById(R.id.pilot_box)
        fighterSkill = findViewById(R.id.fighter_box)
        traderSkill = findViewById(R.id.trader_box)
        engrSkill = findViewById(R.id.engr_box)

        character = Interactor.interactor.character

        val nameText = "Name: " + character!!.name!!
        val difficultyText = "Difficulty: " + character!!.difficulty!!.name
        val currencyText = "Currency: " + character!!.currency.toString()
        val pilotText = "Pilot: " + character!!.getSkill(Skill.PILOT).toString()
        val fighterText = "Fighter: " + character!!.getSkill(Skill.FIGHTER).toString()
        val traderText = "Trader: " + character!!.getSkill(Skill.TRADER).toString()
        val engrText = "Engineer: " + character!!.getSkill(Skill.ENGINEER).toString()

        name!!.text = nameText
        difficulty!!.text = difficultyText
        currency!!.text = currencyText
        pilotSkill!!.text = pilotText
        fighterSkill!!.text = fighterText
        traderSkill!!.text = traderText
        engrSkill!!.text = engrText
    }
}
