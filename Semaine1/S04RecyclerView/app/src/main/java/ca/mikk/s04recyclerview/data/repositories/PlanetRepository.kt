package ca.mikk.s04recyclerview.data.repositories

import ca.mikk.s04recyclerview.domain.models.Planet
import kotlin.random.Random

class PlanetRepository {
    fun retrievePlanets() : List<Planet> {
        val numberToGenerate = Random.nextInt(5,21)
        val planets = mutableListOf<Planet>()

        for(i in 0..numberToGenerate){
            planets.add(newPlanet())
        }

    }

    private fun newPlanet() : Planet {
        val dogNumber = Random.nextInt(1,51)
        val dogImage = Random.nextInt(1,14)
        val temperature = Random.nextDouble(0.0,150.0)
        return Planet("chien #$dogNumber",dogImage.toString(),temperature)
    }
}