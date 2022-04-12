package ca.qc.cstj.s08bottomnavigation.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Meteo(
    val city:String,
    val temperature: Double,
    val country: String,
    val weather: String,
    val latitude: Double,
    val longitude: Double,
    val timestamp: Int,
    val timeZone: Int
)