package valenzuela.carlos.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var maxNumber = 100
    var minNumber = 0
    var num:Int = 0
    var won = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessings: TextView = findViewById(R.id.guessings)
        val generate: Button = findViewById(R.id.generate)
        val down: Button = findViewById(R.id.down)
        val up: Button = findViewById(R.id.up)
        val guessed: Button = findViewById(R.id.guessed)

        generate.setOnClickListener{
            num = Random.nextInt(minNumber, maxNumber)
            guessings.setText(num.toString())
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }

        up.setOnClickListener{
            minNumber = num
            if(checkingLimits()) {
                num = Random.nextInt(minNumber, maxNumber)
                guessings.setText(num.toString())
            }else{
                guessings.setText("I can't believe it... You win")
            }
        }

        down.setOnClickListener{
            maxNumber = num
            if(checkingLimits()) {
                num = Random.nextInt(minNumber, maxNumber)
                guessings.setText(num.toString())
            }else{
                guessings.setText("I can't believe it... You win")
            }
        }

        guessed.setOnClickListener{
            if(!won) {
                guessings.setText("Your number is $num")
                guessed.setText("Wanna play again??")
                won = true
            } else {
                generate.visibility = View.VISIBLE
                guessings.setText("Tap on generate to start")
                guessed.setText("Guessed")
                guessed.visibility =  View.GONE
                resetValues()
            }
        }
    }

    private fun resetValues(){
        minNumber = 0
        maxNumber = 100
        num = 0
        won = false
    }

    private fun checkingLimits(): Boolean{
        return minNumber != maxNumber
    }
}