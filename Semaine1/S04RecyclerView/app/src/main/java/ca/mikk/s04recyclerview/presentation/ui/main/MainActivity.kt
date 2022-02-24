package ca.mikk.s04recyclerview.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ca.mikk.s04recyclerview.R
import ca.mikk.s04recyclerview.databinding.ActivityMainBinding
import ca.mikk.s04recyclerview.domain.models.Planet
import ca.mikk.s04recyclerview.presentation.adapters.PlanetRecyclerViewAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()

    private lateinit var planetRecyclerViewAdapter : PlanetRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        planetRecyclerViewAdapter = PlanetRecyclerViewAdapter(listOf())
        binding.recDogs.layoutManager = LinearLayoutManager(this)
        binding.recDogs.adapter = planetRecyclerViewAdapter

        viewModel.planets.observe(this) {
            planetRecyclerViewAdapter.planets = it
            planetRecyclerViewAdapter.notifyDataSetChanged()
        }

    }
}