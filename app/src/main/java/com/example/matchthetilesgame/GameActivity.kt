package com.example.matchthetilesgame

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class GameActivity : AppCompatActivity() {

    val tilesList = ArrayList<ImageView>()
    val imageArray = ArrayList<Int>()
    val isOpen = ArrayList<Boolean>()
    var selected = -1
    var matchedCount = 0
    var movesLeft = -1
    var name=""
    var diff=""
    private lateinit var tvMovesLeft : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val tvName: TextView = findViewById(R.id.nameTextView)
        val tvDifficulty : TextView = findViewById(R.id.difficultyTextView)
        tvMovesLeft = findViewById(R.id.movesLeftTextView)

        name = intent.getStringExtra("name")!!
        diff = intent.getStringExtra("difficulty")!!
        tvName.text = "Name: $name"
        tvDifficulty.text = "difficulty: $diff"
        movesLeft = when(diff){
            "Easy" -> 30
            "Medium" -> 20
            "Hard" -> 15
            else -> 0
        }
        tvMovesLeft.text = "Moves left: $movesLeft"

        for( i in 0..11) isOpen.add(false)

        tilesList.add(findViewById(R.id.Tile1))
        tilesList.add(findViewById(R.id.Tile2))
        tilesList.add(findViewById(R.id.Tile3))
        tilesList.add(findViewById(R.id.Tile4))
        tilesList.add(findViewById(R.id.Tile5))
        tilesList.add(findViewById(R.id.Tile6))
        tilesList.add(findViewById(R.id.Tile7))
        tilesList.add(findViewById(R.id.Tile8))
        tilesList.add(findViewById(R.id.Tile9))
        tilesList.add(findViewById(R.id.Tile10))
        tilesList.add(findViewById(R.id.Tile11))
        tilesList.add(findViewById(R.id.Tile12))


        imageArray.add(R.drawable.img1)
        imageArray.add(R.drawable.img2)
        imageArray.add(R.drawable.img3)
        imageArray.add(R.drawable.img4)
        imageArray.add(R.drawable.img5)
        imageArray.add(R.drawable.img6)
        imageArray.add(R.drawable.img1)
        imageArray.add(R.drawable.img2)
        imageArray.add(R.drawable.img3)
        imageArray.add(R.drawable.img4)
        imageArray.add(R.drawable.img5)
        imageArray.add(R.drawable.img6)

        imageArray.shuffle()


        for( i in 0..11) tilesList[i].setImageResource(R.drawable.tile)

        for(i in 0..11){
            tilesList[i].setOnClickListener {
                onClick(i)
            }
        }





    }

    private fun onClick(i: Int) {
        if(isOpen[i]==true) return

        tilesList[i].setImageResource(imageArray[i])
        isOpen[i] = true

        if(selected==-1){

            selected = i

        } else {
            val j=selected
            if(imageArray[i]==imageArray[j]){
                matchedCount+=2
            } else {
                val handler = Handler()
                handler.postDelayed(
                    Runnable {
                        tilesList[i].setImageResource(R.drawable.tile)
                        isOpen[i]= false
                        tilesList[j].setImageResource(R.drawable.tile)
                        isOpen[j] = false
                    }, 500
                )
            }


            selected = -1
            movesLeft--
            tvMovesLeft.text = "Moves left: $movesLeft"
        }



        if(matchedCount>=12) gameResult(true)
        else if(movesLeft<=0) gameResult(false)



    }

    private fun gameResult(b: Boolean) {

        val builder = AlertDialog.Builder(this)


        if(b==true){
            builder.setTitle("YOU WON!!")
        }

        if(b==false){
            builder.setTitle("YOU LOST!!")
        }

        builder.setMessage("do you want to play again?")
        builder.setPositiveButton("Yes", DialogInterface.OnClickListener{ dialog, which->
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("name", name )
            intent.putExtra("difficulty", diff)
            startActivity(intent)
            finish()
        })
        builder.setNegativeButton("Exit", DialogInterface.OnClickListener{ dialog, which->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        })
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()

    }
}