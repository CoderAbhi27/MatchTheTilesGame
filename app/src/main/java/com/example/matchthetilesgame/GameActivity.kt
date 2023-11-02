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

    private var matchedCount = 0
    private var selected = -1
    private val isOpen = ArrayList<Boolean>()
    private val tilesList = ArrayList<ImageView>()
    private var imageArray = ArrayList<Int>()
    private var movesLeft = 0
    private var diff = ""
    private var name = ""
    private lateinit var tvMoves: TextView
    private lateinit var tvName: TextView
    private lateinit var tvDifficulty: TextView
    private var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        name = intent.getStringExtra("name")!!
        diff = intent.getStringExtra("difficulty")!!
        movesLeft = when (diff) {
            "Easy" -> 30
            "Medium" -> 20
            "Hard" -> 15
            else -> 0
        }

        tvName = findViewById(R.id.nameTextView)
        tvName.text = "Name : $name"
        tvMoves = findViewById(R.id.movesLeftTextView)
        tvMoves.text = "Moves left: $movesLeft"
        tvDifficulty = findViewById(R.id.difficultyTextView)
        tvDifficulty.text = "difficulty: $diff"


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

        for (i in 0..11) isOpen.add(false)

        for (i in 0..11) tilesList[i].setImageResource(R.drawable.tile)
        for (i in 0..11) {
            tilesList[i].setOnClickListener {
                onTileClicked(i)
            }
        }

    }


    private fun onTileClicked(i: Int) {
        if (isOpen[i] || flag) return

        isOpen[i] = true
        tilesList[i].setImageResource(imageArray[i])

        if (selected == -1) {

            selected = i

        } else {
            val j = selected
            if (imageArray[i] == imageArray[j]) {

                matchedCount += 2

            } else {
                val handler = Handler()
                handler.postDelayed(
                    Runnable {
                        tilesList[i].setImageResource(R.drawable.tile)
                        isOpen[i] = false
                        tilesList[j].setImageResource(R.drawable.tile)
                        isOpen[j] = false
                    }, 500
                )

            }
            selected = -1
            movesLeft--
            tvMoves.text = "Moves left: $movesLeft"
        }

        if (matchedCount == 12) gameResult(true)
        else if (movesLeft == 0) gameResult(false)
    }


    private fun gameResult(result: Boolean) {
        if (flag) return
        flag = true
        if (result) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("YOU WON!")
            builder.setMessage("Play Again?")
            builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("name", name)
                intent.putExtra("difficulty", diff)
                startActivity(intent)
                finish()
            })
            builder.setNegativeButton("Exit", DialogInterface.OnClickListener { dialog, which ->
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            })
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()

        } else {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("YOU LOST!")
            builder.setMessage("Play Again?")
            builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("name", name)
                intent.putExtra("difficulty", diff)
                startActivity(intent)
                finish()
            })
            builder.setNegativeButton("Exit", DialogInterface.OnClickListener { dialog, which ->
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            })
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }
    }

}