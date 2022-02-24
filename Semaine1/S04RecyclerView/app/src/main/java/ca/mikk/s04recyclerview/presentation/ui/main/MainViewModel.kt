package ca.mikk.s04recyclerview.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.mikk.s04recyclerview.data.repositories.PlanetRepository
import ca.mikk.s04recyclerview.domain.models.Planet

class MainViewModel : ViewModel() {

    private val planetRepo = PlanetRepository()
    private val _planets = MutableLiveData<List<Planet>>()

    val planets : LiveData<List<Planet>> get() = _planets

    init {
        _planets.value = planetRepo.retrievePlanets()
    }

}