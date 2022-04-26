package ca.qc.cstj.s09navigationdrawer.data.repositories

import android.util.Log
import ca.qc.cstj.s09navigationdrawer.core.Constants
import ca.qc.cstj.s09navigationdrawer.core.Resource
import ca.qc.cstj.s09navigationdrawer.domain.models.CheckIn
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CheckInRepository {

    suspend fun create(checkIn: CheckIn): Resource<CheckIn> {
        return withContext(Dispatchers.IO) {
            // Methode HTTP
            val body = Json.encodeToString(checkIn)

            // Faire appel
            val (_, _, result) = Constants.CHECKIN_URL.httpPost().jsonBody(body).responseJson()

            when (result) {
                is Result.Success -> {
                    return@withContext Resource.Success(Json.decodeFromString<CheckIn>(result.value.content))
                }
                is Result.Failure -> {
                    Log.e("OK",body)
                    return@withContext Resource.Error(result.error.exception)
                }
            }
        }
    }
}