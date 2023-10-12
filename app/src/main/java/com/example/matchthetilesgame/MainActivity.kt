package com.example.matchthetilesgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etname : TextView = findViewById(R.id.editTextPersonName)
        val radioEasy : RadioButton = findViewById(R.id.easyRadioButton)
        val radioMedium : RadioButton = findViewById(R.id.mediumRadioButton)
        val radioHard : RadioButton = findViewById(R.id.hardRadioButton)
        val buttonStartGame : Button = findViewById(R.id.buttonStartGame)

        buttonStartGame.setOnClickListener{
            if(radioEasy.isChecked){
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("name", etname.text.toString())
                intent.putExtra("difficulty", "Easy")
                startActivity(intent)
            } else if(radioMedium.isChecked){
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("name", etname.text.toString())
                intent.putExtra("difficulty", "Medium")
                startActivity(intent)
            } else if(radioHard.isChecked){
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("name", etname.text.toString())
                intent.putExtra("difficulty", "Hard")
                startActivity(intent)
            } else {
                Toast.makeText(this, "please select no. of tiles", Toast.LENGTH_SHORT).show()
            }
        }
    }
}