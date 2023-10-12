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
    private var matchedCount=0
    private var selected = -1
    private val isOpen = ArrayList<Boolean>()
    private val TilesList = ArrayList<ImageView>()
    private var randomArray = ArrayList<Int>()
    private var size = 12
    private var flag = 0
    private var movesLeft = 0
    private var diff = ""
    private var name = ""
    private lateinit var tvMoves: TextView
    private lateinit var tvName: TextView
    private lateinit var tvDifficulty: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        name = intent.getStringExtra("name")!!
        diff = intent.getStringExtra("difficulty")!!
        movesLeft = when(diff){
            "Easy" -> 30
            "Medium" -> 20
            "Hard" -> 15
            else -> 30
        }

        tvName = findViewById(R.id.nameTextView)
        tvName.text = "Name : $name"
        tvMoves = findViewById(R.id.movesLeftTextView)
        tvMoves.text = "Moves left: $movesLeft"
        tvDifficulty = findViewById(R.id.difficultyTextView)
        tvDifficulty.text = "difficulty: $diff"

        for(i in 1..(size/2)){
            randomArray.add(i)
            randomArray.add(i)
        }
        randomArray.shuffle()

        for(i in 0..size) isOpen.add(false)

        TilesList.add(findViewById(R.id.Tile1))
        TilesList.add(findViewById(R.id.Tile2))
        TilesList.add(findViewById(R.id.Tile3))
        TilesList.add(findViewById(R.id.Tile4))
        TilesList.add(findViewById(R.id.Tile5))
        TilesList.add(findViewById(R.id.Tile6))
        TilesList.add(findViewById(R.id.Tile7))
        TilesList.add(findViewById(R.id.Tile8))
        TilesList.add(findViewById(R.id.Tile9))
        TilesList.add(findViewById(R.id.Tile10))
        TilesList.add(findViewById(R.id.Tile11))
        TilesList.add(findViewById(R.id.Tile12))

        for(i in 0 until size) TilesList[i].setImageResource(R.drawable.tile)
        for(i in 0 until size){
            TilesList[i].setOnClickListener{
                onTileClicked(i)
            }
        }

    }


    private fun onTileClicked(i: Int) {
        if(isOpen[i]) return

        when(randomArray[i]){
            1-> TilesList[i].setImageResource(R.drawable.img1)
            2-> TilesList[i].setImageResource(R.drawable.img2)
            3-> TilesList[i].setImageResource(R.drawable.img3)
            4-> TilesList[i].setImageResource(R.drawable.img4)
            5-> TilesList[i].setImageResource(R.drawable.img5)
            6-> TilesList[i].setImageResource(R.drawable.img6)
        }
        isOpen[i] = true
        if(selected==-1){

            selected = i

        }
        else {
            val j = selected
            if(randomArray[i]!=randomArray[j]){
                val handler = Handler()
                handler.postDelayed(
                    Runnable {
                        TilesList[i].setImageResource(R.drawable.tile)
                        isOpen[i]= false
                        TilesList[j].setImageResource(R.drawable.tile)
                        isOpen[j]= false
                    },
                    500
                )

            }
            else{
                matchedCount+=2
            }
            selected = -1
            movesLeft--
            tvMoves.text = "Moves left: $movesLeft"
        }


        if(matchedCount==size) GameResult(true)
        else if(movesLeft<=0) GameResult(false)
    }


    private fun GameResult(result: Boolean) {
        if(flag==1) return
        if(result){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("YOU WON!")
            builder.setMessage("Play Again?")
            builder.setPositiveButton("Yes", DialogInterface.OnClickListener{ dialog, which->
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("name", name)
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
            flag=1

        } else {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("YOU LOST!")
            builder.setMessage("Play Again?")
            builder.setPositiveButton("Yes", DialogInterface.OnClickListener{ dialog, which->
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("name", name)
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
            flag=1
        }
    }

}