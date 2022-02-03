package ca.mikk.firstapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val headerDevinette = findViewById<TextView>(R.id.headerDevinette)
        headerDevinette.text = "${getString(R.string.greetingsTemplate)} ${intent.getStringExtra("GAME_USERNAME")} !"

        var messageToShow = ""
        val randomNumber = (0..100).random()
        val numberPicker = findViewById<NumberPicker>(R.id.numberPicker)
        numberPicker.minValue = 0
        numberPicker.maxValue = 100
        val buttonGuess = findViewById<View>(R.id.buttonGuess)

        buttonGuess.setOnClickListener{
            val chosenNumber = numberPicker.value
            val isTheRightAnswer = this.verifyAnswerAnswer(chosenNumber,randomNumber)

            if(isTheRightAnswer)
                messageToShow = getString(R.string.goodAnswer)
            else
                messageToShow = if(chosenNumber > randomNumber) getString(R.string.answerTooHigh) else getString(R.string.answerTooLow)

            Toast.makeText(this,messageToShow,Toast.LENGTH_SHORT).show()
        }
    }

    // Partie Static de la class.
    companion object {
        fun newIntent(context: Context,username: String): Intent{
            val intent = Intent(context,SecondActivity::class.java)
            intent.putExtra("GAME_USERNAME",username)
            return intent
        }
    }

    fun verifyAnswerAnswer(selectedNumber: Number,sessionRandomNumber: Number): Boolean{
        return selectedNumber == sessionRandomNumber
    }
}