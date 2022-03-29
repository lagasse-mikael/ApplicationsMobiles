package ca.qc.cstj.s06remotedatasource.data.datasource

import ca.qc.cstj.s06remotedatasource.core.Constants
import ca.qc.cstj.s06remotedatasource.domain.models.Planet
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.github.kittinunf.result.Result
import org.json.JSONObject

class FuelPlanetDataSource {

    suspend fun retrieveAll(): List<Planet> {
        // On se place sur le thread des entrees / sorties.
        // "la derniere ligne du block est retourner" , c'est pourquoi on retourne le 'withContext'
        return withContext(Dispatchers.IO) {
            val (req, res, result) = Constants.PLANET_API_URL.httpGet().responseJson()

            // Success = ~200 , Failure = ~400
            when (result) {
                is Result.Success -> {
                    val planets = mutableListOf<Planet>()
                    val planetJson = result.value.array()

                    for(i in 0 until planetJson.length()){
                        planets.add(deserializePlanet(planetJson.getJSONObject(i)))
                    }
                    planets
                }
                is Result.Failure -> {
                    throw result.error.exception
                }
            }
        }
    }

    private fun deserializePlanet(planetJson: JSONObject) : Planet{
        val name = planetJson.getString("name")
        val image = planetJson.getString("icon")
        val temperature = planetJson.getDouble("temperature")

        return Planet(name, image ,temperature)
    }
}