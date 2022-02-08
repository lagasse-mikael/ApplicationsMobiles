package ca.mikk.firstapp

import android.content.Intent
import android.net.Uri
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
        val telephoneButton = findViewById<Button>(R.id.buttonTelephone)
        val smsButton = findViewById<Button>(R.id.buttonSMS)
        val activityButton = findViewById<Button>(R.id.buttonActivity)

        firstButton.setOnClickListener {
            findViewById<TextView>(R.id.appHeader).text = "Premier button actionne!"
            Toast.makeText(this,"header changer!",Toast.LENGTH_SHORT).show()
        }

        telephoneButton.setOnClickListener{
            val phoneIntent = Intent(Intent.ACTION_DIAL,Uri.parse("tel:5147149939"))
            startActivity(phoneIntent)
        }

        smsButton.setOnClickListener{
            val smsIntent = Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:5147149939"))
            smsIntent.putExtra("sms_body","Hello World!")
            startActivity(smsIntent)
        }

        activityButton.setOnClickListener{
            val username = findViewById<TextView>(R.id.usernameField).text
            val gameIntent = SecondActivity.newIntent(this,username.toString())
            startActivity(gameIntent)
        }
    }

}