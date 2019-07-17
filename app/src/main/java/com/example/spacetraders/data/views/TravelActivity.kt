package com.example.spacetraders.data.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import com.example.spacetraders.R
import com.example.spacetraders.data.models.Interactor
import com.example.spacetraders.data.entity.Character
import com.example.spacetraders.data.entity.SolarSystem
import com.example.spacetraders.data.entity.Universe

import java.util.Arrays
import java.util.Comparator

class TravelActivity : AppCompatActivity() {

    private var travelButtons: Array<Button?>? = null

    internal var universe: Universe? = null
    internal var systems: Array<SolarSystem?>? = null
    internal var currentSystem: SolarSystem? = null
    private var fuelLeft: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel)

        val character = Interactor.interactor.character

        fuelLeft = findViewById(R.id.label_fuel)
        fuelLeft!!.text = "Fuel left: " + (character as Character).ship!!.getFuel()

        universe = Interactor.interactor.universe
        systems = universe!!.systems
        currentSystem = character!!.currentSolarSystem
        //Arrays.sort(systems, SystemComparator()) //sort in order of distance

        travelButtons = arrayOfNulls(systems!!.size - 1) //ignore current System

        val linLayout = findViewById<View>(R.id.linLayout) as LinearLayout
        for (i in travelButtons!!.indices) {
            try {
                val button = Button(this)
                button.id = View.generateViewId()
                button.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

                //todo: add fuel efficiency formula
                val nextSystem = systems!![i + 1]
                val fuelCost = nextSystem!!.getDistance(currentSystem!!)
                button.text = "\nSolar System: " + nextSystem.name + "\nFuel Cost: " + fuelCost + "\n"
                button.setOnClickListener { view ->
                    if (character!!.ship!!.getFuel() < fuelCost) {
                        Toast.makeText(applicationContext, "Not enough fuel", Toast.LENGTH_LONG).show()
                    } else {
                        character!!.ship!!.setFuel(character!!.ship!!.getFuel() - fuelCost)
                        character!!.currentSolarSystem = nextSystem
                        Toast.makeText(applicationContext, "Used $fuelCost fuel", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@TravelActivity, MainGameActivity::class.java))
                        finish()
                    }
                }

                linLayout.addView(button)
                travelButtons!![i] = button
            } catch (e: Exception) {
                System.err.println(e)
                System.err.println("error creating button in TravelActivity")
            }

        }


    }

    private inner class SystemComparator : Comparator<SolarSystem> {
        override fun compare(a: SolarSystem, b: SolarSystem): Int {
            return currentSystem!!.getDistance(a) - currentSystem!!.getDistance(b)
        }
    }
}


