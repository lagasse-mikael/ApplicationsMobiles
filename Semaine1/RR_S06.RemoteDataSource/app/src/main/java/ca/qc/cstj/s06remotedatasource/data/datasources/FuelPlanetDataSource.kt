package ca.qc.cstj.s06remotedatasource.data.datasources

import ca.qc.cstj.s06remotedatasource.core.Constants
import ca.qc.cstj.s06remotedatasource.domain.models.Planet
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class FuelPlanetDataSource {

    suspend fun retrieveAll() : List<Planet> {

        return withContext(Dispatchers.IO) {
            val (req, res, result) = Constants.PLANET_API_URL.httpGet().responseJson()
            val planets = mutableListOf<Planet>()

            when(result) {
                //Code dans la famille 200
                is Result.Success -> {
                    val planetsJson = result.value.array()
                    for (i in 0 until  planetsJson.length()) {
                        planets.add(deserializePlanet(planetsJson.getJSONObject(i)))
                    }
                    //planets //Ceci est le return

                    return@withContext planets
                }
                //Code dans les familles 400 ou 500
                is Result.Failure -> {
                    throw result.error.exception
                }
            }

        }
    }

    private fun deserializePlanet(planetJson: JSONObject): Planet {

        val name = planetJson.getString("name")
        val image = planetJson.getString("icon")
        val temperature = planetJson.getDouble("temperature")

        return Planet(name, image, temperature)

    }

}