package com.example.spacetraders.data.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

import com.example.spacetraders.R
import com.example.spacetraders.data.models.Interactor
import com.example.spacetraders.data.entity.Character

class ShipYardActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var character1: Character? = null
    private var viewCurrency: TextView? = null
    private var previewButton: Button? = null
    private var purchaseButton: Button? = null
    private var shipSpinner: Spinner? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ship_yard)

        character1 = Interactor.interactor.character
        viewCurrency = findViewById(R.id.playerCurrency)
        previewButton = findViewById(R.id.previewButton)
        purchaseButton = findViewById(R.id.purchaseButtn)
        shipSpinner = findViewById(R.id.shipSpinner)


        val adapter = ArrayAdapter.createFromResource(this, R.array.ship_names, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        shipSpinner!!.adapter = adapter
        shipSpinner!!.onItemSelectedListener = this


        viewCurrency!!.text = "Credits: " + character1!!.currency + "c"


    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val text = parent.getItemAtPosition(position).toString()
        Toast.makeText(parent.context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }
}
