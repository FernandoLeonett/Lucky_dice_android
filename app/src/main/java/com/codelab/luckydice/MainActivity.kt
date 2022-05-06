package com.codelab.luckydice

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textResult: TextView = findViewById(R.id.textResult)
        val diceImage1: ImageView = findViewById(R.id.imageView)
        val diceImage2: ImageView = findViewById(R.id.imageView2)
        val rollButton: Button = findViewById(R.id.button)
        textResult.text = "Welcome !!"

        var diceRoll1 = 0
        var diceRoll2 = 0
        var count = 0;
//        var msg = when (count) {
//            0 -> "Welcome"
//            else -> "this is yor $count attempt Try Again 😣"
//        }


        fun getImageResource(diceRoll: Int): Int {
            val drawableResource = when (diceRoll) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
            return drawableResource
        }

        fun rollDice() {


            count++
            val dice = Dice(6)
            diceRoll1 = 0;
            diceRoll2 = 0;

            while (diceRoll1 == diceRoll2) {

                diceRoll1 = dice.roll() // roll the dice
                diceRoll2 = dice.roll()
            }
            val drawableResource1 = getImageResource(diceRoll1)
            val drawableResource2 = getImageResource(diceRoll2)
            Toast.makeText(this,"entre : $diceRoll1 y: $diceRoll2 ",Toast.LENGTH_LONG).show()

            diceImage1.setImageResource(drawableResource1)
            diceImage2.setImageResource(drawableResource2)


            diceImage1.contentDescription = diceRoll1.toString()
            diceImage2.contentDescription = diceRoll2.toString()


        }
        if (count == 0) {
            rollDice()
        }
        fun play() {


            val userInput =
                findViewById<EditText>(R.id.editTextNumber)

            val userNumber = userInput.text.toString().toInt()
            var msg = "";

            if (!userInput.text.isNullOrBlank()) {
                if (userNumber in 1..6 && userNumber in diceRoll1..diceRoll2 || userNumber in diceRoll2..diceRoll1) {

                    msg = "Haz acertado 👏👏"
                    rollDice()
                } else if (userNumber < 1 || userNumber > 6) {
                    msg = "Te notifico con un dado solo tiene numeros del 1 al 6🤨"
                } else {
                    msg = "Buen intento 😅";
                }
                textResult.text = msg


            } else {
                Toast.makeText(this,"Escribe algo sopenco 🤨",Toast.LENGTH_LONG).show()
            }


            // Do a dice roll when the app starts

        }

        rollButton.setOnClickListener {

            play()
        }


//


    }
}