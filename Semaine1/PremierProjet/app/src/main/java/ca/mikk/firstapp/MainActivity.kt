package ca.mikk.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstButton = findViewById<Button>(R.id.buttonID)

        firstButton.setOnClickListener {
            findViewById<TextView>(R.id.appHeader).text = findViewById<EditText>(R.id.inputField).text
            Toast.makeText(this,"header changer!",Toast.LENGTH_SHORT).show()
        }
    }

}