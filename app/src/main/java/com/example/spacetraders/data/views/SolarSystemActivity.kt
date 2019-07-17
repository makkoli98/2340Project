package com.example.spacetraders.data.views

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Button
import android.widget.Toast

import com.example.spacetraders.R
import com.example.spacetraders.data.entity.Planet
import com.example.spacetraders.data.entity.SolarSystem
import com.example.spacetraders.data.viewmodels.SolarSystemViewModel

class SolarSystemActivity : AppCompatActivity() {

    private var name: TextView? = null
    private var resourceLevel: TextView? = null
    private var techLevel: TextView? = null
    private var politicalSystem: TextView? = null
    private var currentPlanetName: TextView? = null
    private val currentSolarSystem: SolarSystem? = null
    private var linearLayout: LinearLayout? = null
    private val planetButtons: Array<Button>? = null
    private var planets: Array<Planet?>? = null

    private var viewModel: SolarSystemViewModel? = null

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        setContentView(R.layout.activity_solar_system)

        viewModel = ViewModelProviders.of(this).get(SolarSystemViewModel::class.java!!)

        name = findViewById(R.id.solarSystemName)
        resourceLevel = findViewById(R.id.solarSystemResource)
        techLevel = findViewById(R.id.solarSystemTech)
        politicalSystem = findViewById(R.id.solarSystemPolitics)
        currentPlanetName = findViewById(R.id.planetName)

        planets = viewModel!!.planets

        name!!.text = viewModel!!.name
        resourceLevel!!.text = viewModel!!.resourceLevel!!.toString()
        techLevel!!.text = viewModel!!.techLevel!!.toString()
        politicalSystem!!.text = viewModel!!.politicalSystem!!.toString()
        currentPlanetName!!.text = viewModel!!.currentPlanet!!.name

        linearLayout = findViewById<View>(R.id.listPlanets) as LinearLayout
        println(viewModel!!.numPlanets)
        for (i in 0 until viewModel!!.numPlanets) {
            val planet = planets!![i]
            val button = Button(this)
            button.text = viewModel!!.planets!![i]!!.name
            button.setOnClickListener { v: View ->
                if (viewModel!!.character!!.currentSolarSystem!!.currentPlanet === planet) {
                    Toast.makeText(applicationContext, "Already located on planet", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(applicationContext, "You have travelled to " + planet!!.name!!, Toast.LENGTH_LONG).show()
                    viewModel!!.character!!.currentSolarSystem!!.currentPlanet = planet
                    currentPlanetName!!.text = planet!!.name
                }
            }
            linearLayout!!.addView(button)
        }

    }
}
