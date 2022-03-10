package ca.qc.cstj.s05localdatasource.presentation.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import ca.qc.cstj.s05localdatasource.R
import ca.qc.cstj.s05localdatasource.core.loadFromResource
import ca.qc.cstj.s05localdatasource.databinding.ActivitySplashBinding
import ca.qc.cstj.s05localdatasource.presentation.ui.main.MainActivity
import ca.qc.cstj.s05localdatasource.presentation.ui.main.MainViewModel

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val viewModel : SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.user.observe(this) {
            // TODO : Updater l'interface graphique

            // Haut : Nom + Nuage
            binding.txvUserName.text = it.fullName()
            // Bas 2 txts + switch
            binding.imgUserIsOnline.loadFromResource(if (it.isOnline) "status_online" else "status_offline" )
        }

        binding.btnOpen.setOnClickListener {
            startActivity(MainActivity.newIntent(this))
        }

        binding.btnSave.setOnClickListener {
            val firstName = binding.tilFirstName.editText?.text.toString()
            val lastName = binding.tilLastName.editText?.text.toString()
            val isOnline = binding.swtOnline.isChecked

            viewModel.save(firstName,lastName,isOnline)

        }
    }
}