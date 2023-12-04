package com.example.matchthetilesgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val editText : EditText = findViewById(R.id.editTextPersonName)
        val radioEasy : RadioButton = findViewById(R.id.easyRadioButton)
        val radioMedium : RadioButton = findViewById(R.id.mediumRadioButton)
        val radioHard : RadioButton = findViewById(R.id.hardRadioButton)
        val button : Button = findViewById(R.id.buttonStartGame)

        button.setOnClickListener {
            val name = editText.text.toString()
            if(radioEasy.isChecked){
                val diff = "Easy"
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("name", name )
                intent.putExtra("difficulty", diff)
                startActivity(intent)

            } else if(radioMedium.isChecked){
                val diff = "Medium"
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("name", name )
                intent.putExtra("difficulty", diff)
                startActivity(intent)

            } else if(radioHard.isChecked) {
                val diff = "Hard"
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("name", name )
                intent.putExtra("difficulty", diff)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Please select difficulty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}