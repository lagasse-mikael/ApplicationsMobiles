package ca.qc.cstj.s06remotedatasource.data.repositories

import ca.qc.cstj.s06remotedatasource.core.Constants
import ca.qc.cstj.s06remotedatasource.core.LoadingResource
import ca.qc.cstj.s06remotedatasource.core.Resource
import ca.qc.cstj.s06remotedatasource.data.datasources.FuelPlanetDataSource
import ca.qc.cstj.s06remotedatasource.domain.models.Planet
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FuelPlanetRepository {

    private val planetDataSource = FuelPlanetDataSource()

    // Retrouver les planètes sans mise à jour régulière
    suspend fun retrieveAll(): Resource<List<Planet>> {
        return try {
            Resource.Success(planetDataSource.retrieveAll())
        } catch (ex: Exception) {
            Resource.Error(ex, "Erreur du serveur")
        }
    }

    //Retrouver les données des planètes avec mise à jour
    suspend fun retrieveAllWithUpdate() : Flow<Resource<List<Planet>>> {
        return flow { //Retourne le lien de communication (autoroute, rivière)
            while(true) {
                try {
                    emit(Resource.Success(planetDataSource.retrieveAll())) //Mettre un kayak
                } catch (ex: Exception) {
                    emit(Resource.Error(ex, "Erreur du serveur"))
                }
                delay(Constants.RefreshRates.PLANET_REFRESH_RATE)
            }
        }
    }

    //Retrouver les données des planètes avec mise à jour et chargement (loading)
    suspend fun retrieveAllWithLoading() : Flow<LoadingResource<List<Planet>>> {
        return flow { //Retourne le lien de communication (autoroute, rivière)
            while(true) {
                try {
                    emit(LoadingResource.Loading())
                    delay(1500) // Pour des tests, ne pas garder en production
                    emit(LoadingResource.Success(planetDataSource.retrieveAll())) //Mettre un kayak
                } catch (ex: Exception) {
                    emit(LoadingResource.Error(ex, "Erreur du serveur"))
                }
                delay(Constants.RefreshRates.PLANET_REFRESH_RATE)
            }
        }
    }

}