package com.willowtreeapps.mintweather.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Atmosphere(
    val humidity: Int,
    val visibility: Double,
    val pressure: Double
)