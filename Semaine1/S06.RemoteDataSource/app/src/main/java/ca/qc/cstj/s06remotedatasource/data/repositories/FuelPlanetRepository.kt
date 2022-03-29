package ca.qc.cstj.s06remotedatasource.data.repositories

import ca.qc.cstj.s06remotedatasource.core.Resource
import ca.qc.cstj.s06remotedatasource.data.datasource.FuelPlanetDataSource
import ca.qc.cstj.s06remotedatasource.domain.models.Planet

class FuelPlanetRepository {

    private val planetDataSource = FuelPlanetDataSource()

    suspend fun retrieveAll(): Resource<List<Planet>> {
        // TODO call le datasource
        try {
            return Resource.Success(planetDataSource.retrieveAll())
        } catch(ex: Throwable) {
            return Resource.Error(ex,"Erreur serveur")
        }
    }
}