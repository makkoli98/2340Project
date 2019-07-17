package com.example.spacetraders.data.views

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.view.View
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Button
import android.widget.Toast

import com.example.spacetraders.R
import com.example.spacetraders.data.models.Interactor
import com.example.spacetraders.data.entity.Resources
import com.example.spacetraders.data.entity.Character
import com.example.spacetraders.data.viewmodels.MarketViewModel

import java.util.Arrays

class MarketActivity : AppCompatActivity() {
    private var currencyDisplay: TextView? = null
    private var inputs = arrayOfNulls<TextInputLayout>(Resources.values().size)
    private var amounts = arrayOfNulls<TextView>(Resources.values().size)
    private var prices = arrayOfNulls<TextView>(Resources.values().size)
    private var confirm: Button? = null
    private var BuySell: Spinner? = null
    private var character: Character? = null
    private var viewModel: MarketViewModel? = null
    private var cargoCap: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marketplace)

        viewModel = ViewModelProviders.of(this).get(MarketViewModel::class.java)
        character = Interactor.interactor.character
        cargoCap = findViewById(R.id.label_cargoCap)
        val resourceSum = Arrays.stream(Interactor.interactor.character!!.ship!!.getCurrentResources()).sum()
        val cargoMaxSize = Interactor.interactor.character!!.ship!!.cargoSize
        val cargo = "Cargo: $resourceSum/$cargoMaxSize"
        cargoCap!!.text = cargo

        currencyDisplay = findViewById(R.id.label_currency)
        currencyDisplay!!.text = character!!.currency.toString()

        inputs[Resources.WATER.ordinal] = findViewById(R.id.input_water)
        inputs[Resources.FURS.ordinal] = findViewById(R.id.input_fur)
        inputs[Resources.FOOD.ordinal] = findViewById(R.id.input_food)
        inputs[Resources.ORE.ordinal] = findViewById(R.id.input_ore)
        inputs[Resources.GAMES.ordinal] = findViewById(R.id.input_games)
        inputs[Resources.FIREARMS.ordinal] = findViewById(R.id.input_firearms)
        inputs[Resources.MEDICINE.ordinal] = findViewById(R.id.input_medicine)
        inputs[Resources.MACHINES.ordinal] = findViewById(R.id.input_machines)
        inputs[Resources.NARCOTICS.ordinal] = findViewById(R.id.input_narcotics)
        inputs[Resources.ROBOTS.ordinal] = findViewById(R.id.input_robots)

        amounts = arrayOfNulls(Resources.values().size)
        amounts[Resources.WATER.ordinal] = findViewById(R.id.label_amountWater)
        amounts[Resources.FURS.ordinal] = findViewById(R.id.label_amountFur)
        amounts[Resources.FOOD.ordinal] = findViewById(R.id.label_amountFood)
        amounts[Resources.ORE.ordinal] = findViewById(R.id.label_amountOre)
        amounts[Resources.GAMES.ordinal] = findViewById(R.id.label_amountGames)
        amounts[Resources.FIREARMS.ordinal] = findViewById(R.id.label_amountFirearms)
        amounts[Resources.MEDICINE.ordinal] = findViewById(R.id.label_amountMedicine)
        amounts[Resources.MACHINES.ordinal] = findViewById(R.id.label_amountMachines)
        amounts[Resources.NARCOTICS.ordinal] = findViewById(R.id.label_amountNarcotics)
        amounts[Resources.ROBOTS.ordinal] = findViewById(R.id.label_amountRobots)

        prices = arrayOfNulls(Resources.values().size)
        prices[Resources.WATER.ordinal] = findViewById(R.id.label_water)
        prices[Resources.FURS.ordinal] = findViewById(R.id.label_fur)
        prices[Resources.FOOD.ordinal] = findViewById(R.id.label_food)
        prices[Resources.ORE.ordinal] = findViewById(R.id.label_ore)
        prices[Resources.GAMES.ordinal] = findViewById(R.id.label_games)
        prices[Resources.FIREARMS.ordinal] = findViewById(R.id.label_firearms)
        prices[Resources.MEDICINE.ordinal] = findViewById(R.id.label_medicine)
        prices[Resources.MACHINES.ordinal] = findViewById(R.id.label_machines)
        prices[Resources.NARCOTICS.ordinal] = findViewById(R.id.label_narcotics)
        prices[Resources.ROBOTS.ordinal] = findViewById(R.id.label_robots)

        val resourcePrices = viewModel!!.resourcePrices
        val resourceAmounts = viewModel!!.resourceAmounts
        for (r in Resources.values()) {
            var s: String? = r.toString() + "- " + resourcePrices[r.ordinal] + "c"
            prices[r.ordinal]?.text = s
            amounts[r.ordinal]?.text = Integer.toString(resourceAmounts[r.ordinal])
        }

        confirm = findViewById(R.id.button_confirm)
        BuySell = findViewById(R.id.spinner_buysell)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayOf("BUY", "SELL"))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        BuySell!!.adapter = adapter
        BuySell!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val item = BuySell!!.selectedItem as String
                if (item == "BUY") {
                    for (r in Resources.values()) {
                        amounts!![r.ordinal]?.text = Integer.toString(viewModel!!.resourceAmounts[r.ordinal])
                        //amounts[r.ordinal()].setText(Integer.toString(viewModel.getResourceAmounts()[r.ordinal()]));
                    }
                } else if (item == "SELL") {
                    for (r in Resources.values()) {
                        amounts!![r.ordinal]?.text = Integer.toString(viewModel!!.currentResources!![r.ordinal])
                        //amounts[r.ordinal()].setText(Integer.toString(character.getShip().getCurrentResources()[r.ordinal()]));
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        confirm!!.setOnClickListener { view ->
            val productQuantities = IntArray(Resources.values().size)
            var sumUserAmount = 0
            var cost = 0
            for (i in productQuantities.indices) {
                productQuantities[i] = getAmount(inputs[i])
                cost += productQuantities[i] * resourcePrices[i]
                sumUserAmount += productQuantities[i]
            }

            val choice = BuySell!!.selectedItem as String
            //player's credits
            val playerCurrency = viewModel!!.character!!.currency
            if (choice == "BUY") {
                // check if purchase is valid
                var purchaseValid: Boolean? = true
                for (i in productQuantities.indices) {
                    if (productQuantities[i] > viewModel!!.resourceAmounts[i]) {
                        purchaseValid = false
                    }
                }
                if (viewModel!!.cargoSize - Arrays.stream(viewModel!!.currentResources).sum() >= sumUserAmount && cost <= playerCurrency && purchaseValid!!) {
                    viewModel!!.setResource(productQuantities, true)
                    //character.getShip().setResource(productQuantities, true);
                    println(Arrays.toString(character!!.ship!!.getCurrentResources()))
                    viewModel!!.updateCurrency(cost, true)
                    //character.setCredits(character.getCredits() - cost);
                    currencyDisplay!!.text = Integer.toString(viewModel!!.character!!.currency)
                    val newAmounts = IntArray(resourceAmounts.size)
                    for (i in amounts!!.indices) {
                        newAmounts[i] = viewModel!!.resourceAmounts[i] - productQuantities[i]
                        val currentAmount = Integer.parseInt(amounts!![i]!!.text.toString())
                        val correctAmount = currentAmount - productQuantities[i]
                        amounts!![i]!!.text = Integer.toString(correctAmount)
                    }
                    viewModel!!.resourceAmounts = newAmounts
                    //character.getCurrentSolarSystem().getPlanets()[0].getMarket().setResourceAmount(newAmounts);
                } else {
                    Toast.makeText(applicationContext, "Insufficient cargo space or currency or amount for purchase", Toast.LENGTH_LONG).show()
                }

            } else if (choice == "SELL") {
                //check if purchase is valid
                //check if each cargo slot is valid
                var cargoValid: Boolean? = true
                for (i in productQuantities.indices) {
                    if (productQuantities[i] > viewModel!!.currentResources!![i]) {
                        // if any resource amount is invalid, entire purchase is invalid
                        cargoValid = false
                    }
                }
                if (cargoValid!!) {
                    viewModel!!.setResource(productQuantities, false)
                    //character.getShip().setResource(productQuantities, false);
                    println(Arrays.toString(character!!.ship!!.getCurrentResources()))
                    //character.setCredits(character.getCredits() + cost);
                    viewModel!!.updateCurrency(cost, false)
                    currencyDisplay!!.text = Integer.toString(viewModel!!.character!!.currency)
                    val newAmounts = IntArray(resourceAmounts.size)
                    for (i in amounts!!.indices) {
                        newAmounts[i] = viewModel!!.resourceAmounts[i] + productQuantities[i]
                        val currentAmount = Integer.parseInt(amounts!![i]!!.text.toString())
                        val correctAmount = currentAmount - productQuantities[i]
                        amounts!![i]!!.text = Integer.toString(correctAmount)
                    }
                    viewModel!!.resourceAmounts = newAmounts
                    //character.getCurrentSolarSystem().getPlanets()[0].getMarket().setResourceAmount(newAmounts);
                } else {
                    Toast.makeText(applicationContext, "Invalid product amount", Toast.LENGTH_LONG).show()
                }
            }
            val newSum = Arrays.stream(Interactor.interactor.character!!.ship!!.getCurrentResources()).sum()
            val newCargo = "Cargo: $newSum/$cargoMaxSize"
            cargoCap!!.text = newCargo
        }

        println("In market: " + character!!.name!!)
    }

    private fun getAmount(inputText: TextInputLayout?): Int {
        if (inputText != null) {
            return if (inputText.editText!!.text.toString() == "") {
                0
            } else {
                Integer.parseInt(inputText.editText!!.text.toString())
            }
        } else {
            return 0
        }
    }

    override fun onPause() {
        super.onPause()
        Interactor.interactor.character = character
    }

}
