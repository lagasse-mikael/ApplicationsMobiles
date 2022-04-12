package ca.qc.cstj.s08bottomnavigation.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Wind(
    val deg: Int,
    val gust: Double? ?= 0.0,
    val speed: Double
)