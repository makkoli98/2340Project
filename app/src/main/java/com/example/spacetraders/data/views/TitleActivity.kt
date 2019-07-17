package com.example.spacetraders.data.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button

import com.example.spacetraders.R


class TitleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title)

        /*
         * Disables actionbar back button
         */
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            supportActionBar!!.setHomeButtonEnabled(false)
        }

        val button1 = findViewById<Button>(R.id.button_newGame)

        button1.setOnClickListener {
            val intent = Intent(this@TitleActivity, NewCharacterActivity::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<Button>(R.id.button_continueGame)

        button2.setOnClickListener {
            val intent = Intent(this@TitleActivity, OldCharacterActivity::class.java)
            startActivity(intent)
        }
    }
}