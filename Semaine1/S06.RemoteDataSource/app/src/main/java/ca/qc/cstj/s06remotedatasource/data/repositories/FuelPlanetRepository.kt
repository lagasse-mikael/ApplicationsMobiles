package ca.qc.cstj.s06remotedatasource.data.repositories

import ca.qc.cstj.s06remotedatasource.core.Constants
import ca.qc.cstj.s06remotedatasource.core.Resource
import ca.qc.cstj.s06remotedatasource.data.datasource.FuelPlanetDataSource
import ca.qc.cstj.s06remotedatasource.domain.models.Planet
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FuelPlanetRepository {

    private val planetDataSource = FuelPlanetDataSource()

    // Retrouve les planetes sans mise a jour!
    suspend fun retrieveAll(): Resource<List<Planet>> {
        // TODO call le datasource
        return try {
            Resource.Success(planetDataSource.retrieveAll())
        } catch (ex: Throwable) {
            Resource.Error(ex, "Erreur serveur")
        }
    }

    //
    suspend fun retrieveAllWithUpdates(): Flow<Resource<List<Planet>>> {
        return flow {
            while (true) {
                try {
                    emit(Resource.Success(planetDataSource.retrieveAll()))
                } catch (ex: Throwable) {
                    emit(Resource.Error(ex, "Erreur serveur"))
                }
                delay(Constants.TRENTE_SECONDES_EN_MILLISECONDES)
            }
        }
    }
}