package com.example.spacetraders.data.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

import com.example.spacetraders.R
import com.example.spacetraders.data.entity.GameDifficulty
import com.example.spacetraders.data.entity.Resources
import com.example.spacetraders.data.entity.SolarSystem
import com.example.spacetraders.data.entity.Universe
import com.example.spacetraders.data.entity.Character
import com.example.spacetraders.data.models.Interactor

class MainGameActivity : AppCompatActivity() {
    private var solarSystem: Button? = null
    private var planet: Button? = null
    private var market: Button? = null
    private var player: Button? = null
    private var travel: Button? = null
    private var cargo: Button? = null
    private var shipyard: Button? = null
    internal var systemName: TextView? = null
    internal var character: Character? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_game)
        systemName = findViewById(R.id.solar_system_name)
        character = Interactor.interactor.character
        systemName!!.text = character!!.currentSolarSystem!!.name

        /*
         * Disables actionbar back button
         */
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            supportActionBar!!.setHomeButtonEnabled(false)
        }

        solarSystem = findViewById(R.id.button_solarSystem)
        planet = findViewById(R.id.button_planet)
        market = findViewById(R.id.button_market)
        player = findViewById(R.id.button_player)
        travel = findViewById(R.id.button_travel)
        cargo = findViewById(R.id.button_cargo)
        shipyard = findViewById(R.id.button_shipyard)


        solarSystem!!.setOnClickListener { v: View ->
            val intent = Intent(this@MainGameActivity, SolarSystemActivity::class.java)
            startActivity(intent)
        }


        market!!.setOnClickListener { v: View ->
            val intent = Intent(this@MainGameActivity, MarketActivity::class.java)
            startActivity(intent)
        }

        player!!.setOnClickListener { v: View ->
            val intent = Intent(this@MainGameActivity, PlayerActivity::class.java)
            startActivity(intent)
        }


        travel!!.setOnClickListener { v: View ->
            val intent = Intent(this@MainGameActivity, TravelActivity::class.java)
            startActivity(intent)
        }

        shipyard!!.setOnClickListener { v: View ->
            val intent = Intent(this@MainGameActivity, RefuelActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        Interactor.interactor.saveGame()
    }
}
