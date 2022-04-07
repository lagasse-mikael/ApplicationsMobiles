package ca.qc.cstj.s08bottomnavigation.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Coord(
    val lat: Double,
    val lon: Double
)