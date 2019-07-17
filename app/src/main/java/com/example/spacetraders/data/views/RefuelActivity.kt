package com.example.spacetraders.data.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import com.example.spacetraders.R
import com.example.spacetraders.data.models.Interactor
import com.example.spacetraders.data.entity.Character


class RefuelActivity : AppCompatActivity() {

    private var character: Character? = null
    private var shipType: TextView? = null
    private var cargoSize: TextView? = null
    private var health: TextView? = null
    private var weaponsSize: TextView? = null
    private var fuelEfficiency: TextView? = null
    private var basePrice: TextView? = null
    private var fuelLeft: TextView? = null
    private var currency: TextView? = null
    private var refuelButton: Button? = null
    private var upgradeButton: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refuel)

        character = Interactor.interactor.character


        shipType = findViewById(R.id.displayType)
        cargoSize = findViewById(R.id.displayCargoSize)
        health = findViewById(R.id.displayCurrHealth)
        weaponsSize = findViewById(R.id.displayWeaponsNum)
        fuelEfficiency = findViewById(R.id.displayFuelEfficiency)
        basePrice = findViewById(R.id.displayBasePrice)
        fuelLeft = findViewById(R.id.displayFuelLeft)
        currency = findViewById(R.id.displayCurrency)
        refuelButton = findViewById(R.id.buttonRefuel)
        upgradeButton = findViewById(R.id.buttonUpgrade)

        shipType!!.text = character!!.ship!!.name
        cargoSize!!.text = "" + character!!.ship!!.cargoSize
        health!!.text = "" + character!!.ship!!.getCurrentHealth()
        weaponsSize!!.text = "" + character!!.ship!!.maxWeaponsAmount
        fuelEfficiency!!.text = "" + character!!.ship!!.fuelEfficiency
        basePrice!!.text = "" + character!!.ship!!.basePrice
        fuelLeft!!.text = "" + character!!.ship!!.getFuel()
        currency!!.text = "" + character!!.currency + "c"

        //int amountForRefuel = character.getShip().getFuelEfficiency() - character.getShip().getFuel();
        //1 fuel =  1 credit
        refuelButton!!.text = "1 Fuel = 5c"
        refuelButton!!.setOnClickListener { v: View ->
            if (character!!.ship!!.getFuel() == character!!.ship!!.maxFuel) {
                Toast.makeText(applicationContext, "Tank Full", Toast.LENGTH_LONG).show()
            } else if (character!!.currency < 5) {
                Toast.makeText(applicationContext, "Insufficient", Toast.LENGTH_LONG).show()
            } else {
                character!!.currency = character!!.currency - 5
                currency!!.text = "" + character!!.currency + "c"
                character!!.ship!!.setFuel(character!!.ship!!.getFuel() + 1)
                fuelLeft!!.text = "" + character!!.ship!!.getFuel()
            }
        }

        upgradeButton!!.setOnClickListener { v: View ->
            val intent = Intent(this@RefuelActivity, ShipYardActivity::class.java)
            startActivity(intent)
        }


    }
}
