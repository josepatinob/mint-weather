package com.willowtreeapps.mintweather.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DarkSkyWeatherResponse(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val currently: Currently,
    val hourly: Forecast,
    val daily: Forecast,
    val offset: Int
)