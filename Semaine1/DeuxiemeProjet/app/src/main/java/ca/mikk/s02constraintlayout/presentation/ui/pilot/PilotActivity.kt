package ca.mikk.s02constraintlayout.presentation.ui.pilot

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import ca.mikk.s02constraintlayout.R
import ca.mikk.s02constraintlayout.databinding.ActivityPilotBinding
import ca.mikk.s02constraintlayout.domain.models.Pilot
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class PilotActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPilotBinding
    private val viewModel: PilotViewModel by viewModels()

    // private val _pilot = Pilot("Bee Zoon",10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Transformer et charge le xml en memoire dans le binding
        binding = ActivityPilotBinding.inflate(layoutInflater)
        // Cree l'interface a partir du binding
        setContentView(binding.root)

        viewModel.pilot.observe(this) {
            initInfos(it)
        }

            binding.spin?.setOnClickListener {
//                if (_pilot.canFly()) {
//                    it.isEnabled = false
//                    _pilot.fly(binding.sldRevolution.value.toInt(), binding.isTraining.isChecked)
//
//                    var layoutParamsAnim = binding.imgRocket.layoutParams as ConstraintLayout.LayoutParams
//                    val startAngle = layoutParamsAnim.circleAngle
//                    val endAngle = startAngle - 360
//                    val animation = ValueAnimator.ofFloat(startAngle,endAngle)
//
//                    animation.repeatCount = binding.sldRevolution.value.toInt() - 1
//                    animation.duration = Random.nextLong(1500,5000)
//                    animation.interpolator = LinearInterpolator()
//
//                    animation.addUpdateListener {
//                        val animatedValue = it.animatedValue as Float
//
//                        layoutParamsAnim.circleAngle = animatedValue
//                        binding.imgRocket.layoutParams = layoutParamsAnim
//                        binding.imgRocket.rotation = (animatedValue % 360 - 90) * 3
//                    }
//                    animation.doOnEnd {
//                        initInfos()
//                        binding.spin?.isEnabled = true
//                    }
//
//                    animation.start()
//                } else {
//                    Snackbar.make(binding.root,getString(R.string.alertCantFly),Snackbar.LENGTH_INDEFINITE)
//                        .setAction(getString(R.string.rechargePlayer)){
//                            _pilot.recharge()
//                            initInfos()
//                        }
//                        .show()
//                }
            }

    }

    private fun initInfos(_pilot : Pilot){
        with(binding)
        {
            gamertag.text = _pilot.name
            level.text = getString(R.string.playerLevel,_pilot.level)

            lifeText.text = _pilot.life.toString()
            ShieldText.text = _pilot.shield.toString()
            EnergyText.text = _pilot.energy.toString()
            CubeText.text = _pilot.cube.toString()
        }
    }
}