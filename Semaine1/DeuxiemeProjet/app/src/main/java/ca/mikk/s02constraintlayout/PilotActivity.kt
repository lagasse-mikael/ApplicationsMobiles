package ca.mikk.s02constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import ca.mikk.s02constraintlayout.databinding.ActivityPilotBinding
import ca.mikk.s02constraintlayout.domain.models.Pilot

class PilotActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPilotBinding

    private val _pilot = Pilot("Bee Zoon",10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Transformer et charge le xml en memoire dans le binding
        binding = ActivityPilotBinding.inflate(layoutInflater)
        // Cree l'interface a partir du binding
        setContentView(binding.root)

        initInfos(_pilot)
            binding.spin.setOnClickListener {
                if (_pilot.canFly()) {
                    _pilot.fly(binding.sldRevolution.value.toInt(), binding.isTraining.isChecked)
                    initInfos(_pilot)
                }
            }

    }

    private fun initInfos(pilot :Pilot){
        with(binding)
        {
            gamertag.text = _pilot.name
            level.text = _pilot.level.toString()

            lifeText.text = _pilot.life.toString()
            ShieldText.text = _pilot.shield.toString()
            EnergyText.text = _pilot.energy.toString()
            CubeText.text = _pilot.cube.toString()
        }
    }
}