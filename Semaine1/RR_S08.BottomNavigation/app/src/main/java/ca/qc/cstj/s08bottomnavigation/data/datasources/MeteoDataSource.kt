package ca.qc.cstj.s08bottomnavigation.data.datasources

import ca.qc.cstj.s08bottomnavigation.core.Constants
import ca.qc.cstj.s08bottomnavigation.data.dto.MeteoDTO
import ca.qc.cstj.s08bottomnavigation.domain.models.Meteo
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MeteoDataSource {

    // Si jamais il manque des cles ou qu'il y en a des nouvelles , on les ignores.
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun retrieve(cityName: String) : Meteo {

        return withContext(Dispatchers.IO) {
           val serviceURL = Constants.WEATHER_URL.format(cityName)
           val (req, res, result) = serviceURL.httpGet().responseJson()

            when(result) {
                is Result.Success -> {
                    //Deserialiser ma réponse JSON
                    val meteoDTO = json.decodeFromString<MeteoDTO>(result.value.content)
                    return@withContext Meteo(meteoDTO.name,
                            meteoDTO.main.temp,
                            meteoDTO.sys.country,
                            meteoDTO.weather[0].main,
                            meteoDTO.coord.lat,
                            meteoDTO.coord.lon,
                            meteoDTO.dt)

                }
                is Result.Failure -> {
                    throw result.error.exception
                }
            }

        }

    }

}