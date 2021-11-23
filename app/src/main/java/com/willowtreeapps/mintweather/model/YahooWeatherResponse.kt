package com.willowtreeapps.mintweather.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class YahooWeatherResponse(
    val location: Location,

    @Json(name = "current_observation")
    val currentObservation: CurrentObservation,
    val forecasts: List<DailyForecast>
)